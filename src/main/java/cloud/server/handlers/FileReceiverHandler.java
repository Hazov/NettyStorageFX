package cloud.server.handlers;

import cloud.Chunk;
import cloud.files.FileInfo;
import cloud.server.state.State;
import cloud.server.utils.FileUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.stream.ChunkedNioFile;
import io.netty.util.ReferenceCountUtil;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

/**
 * Handles a server-side channel.
 */
public class FileReceiverHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("FFF");
        if(msg instanceof ByteBuf){
            System.out.println("chunk confirmed");
            ByteBuf chunkOfFile = (ByteBuf) msg;
            int clientHash = ctx.channel().hashCode();        //TODO должен быть клиентский хэш
            FileInfo fileInfo = FileUtils.getFileInfoByClient(clientHash);
            Path file = Paths.get(fileInfo.getPathOnServer());        //TODO здесь тоже поменять
            if(Files.exists(file)){                     //Если файл существует
                FileChannel fileChannel = FileChannel.open(file, StandardOpenOption.APPEND);
                int write = fileChannel.write(chunkOfFile.nioBuffer());
                System.out.println("increase " + write);
                if(fileInfo.getProgress() < fileInfo.getLength()){
                    System.out.println("get!!!!!!!!!!!!!!!1           " + fileInfo.getProgress() + " / " + fileInfo.getLength());
                    fileInfo.increaseProgress(write);
                    fileInfo.setChunkNumber(fileInfo.getChunkNumber()+1);
                    System.out.println(fileInfo.getChunkNumber());
                    //ctx.writeAndFlush(new UploadResponseOK()); //TODO взять из пула
                }
                else{
                    System.out.println("ФАЙЛ ПОЛНОСТЬЮ ЗАГРУЖЕН --- " + fileInfo.getName());
                    //FileUtils.changeCurrentStateForClient(clientHash, State.READ_COMMAND);    //TODO вынести в изменение состояния
                    FileUtils.removeFileInfoByClient(clientHash);
                }
            }
        }else{
            ctx.fireChannelRead(msg);
        }
        ReferenceCountUtil.release(msg);
    }
}