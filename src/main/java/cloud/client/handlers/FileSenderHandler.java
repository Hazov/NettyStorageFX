package cloud.client.handlers;


import cloud.Chunk;
import cloud.files.*;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.handler.stream.ChunkedNioFile;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;


public class FileSenderHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if (msg instanceof FileInfo) {
//            long offset = 0;
//            ByteBuf buffer = ctx.alloc().buffer();
//            buffer.clear();
//            System.out.println("Отправляю файл!");
//            FileInfo fileInfo = (FileInfo) msg;
//            String path = fileInfo.getPathOnClient();
//            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
//            FileChannel channel = randomAccessFile.getChannel();
//            long countOfChunks = fileInfo.getCountOfChunks();
//
//            for (int i = 0; i < countOfChunks; i++) {
//                buffer.retain();
//                buffer.clear();
//                System.out.println("Часть " + i + " из " + countOfChunks);
//                buffer.writeBytes(channel, offset,fileInfo.getChunkSize());
//                offset+=fileInfo.getChunkSize();
//                System.out.println(" offset    " + offset);
//                System.out.println(buffer);
//
//                //buffer.writeBytes(bytes);
//                //chunk.setBytes(bytes);
//                //ctx.writeAndFlush(chunk);
//                ctx.writeAndFlush(buffer);
//            }randomAccessFile.close();
//
//        }


        if (msg instanceof FileInfo) {
            FileInfo fileInfo = (FileInfo) msg;
            ChunkedNioFile chunkedNioFile = new ChunkedNioFile(new File(fileInfo.getPathOnClient()), 1024);
            System.out.println("ЭЙ АЛЛО");
            ctx.writeAndFlush(chunkedNioFile);
        }
//
//        if (msg instanceof FileInfo) {
//            FileInfo fileInfo = (FileInfo) msg;
//            File file = new File(fileInfo.getPathOnClient());
//            long length = fileInfo.getLength();
//            DefaultFileRegion fileRegion = new DefaultFileRegion(file, 0, length);
//            ctx.newProgressivePromise().addListener(new GenericFutureListener<Future<? super Void>>() {
//                @Override
//                public void operationComplete(Future<? super Void> future) throws Exception {
//                    System.out.println("PrOMISE     " + fileRegion.transferred());
//                }
//            });
//            ctx.writeAndFlush(fileRegion);
//
//
//
//            System.out.println("Отправляю файл");
//
//        }


        /**
         * next ->
         *          @see ObjectEncoder
         */

    }}












        //СТАРОЕ
//        if (msg instanceof UploadResponseOK) {
//            //System.out.println("Отправляю файл кусками");
//            FileInfo fileInfo = ClientInfo.getFileInfo();
//            for (int i = 0; i < fileInfo.getCountOfChunks()-1; i++) {
//                if (fileInfo.getChunkNumber() < fileInfo.getCountOfChunks()) {
//                    if (ClientInfo.getRandomAccessFile() == null && fileInfo.getChunkNumber() == 0) {
//                        ClientInfo.setCurrentUpLoadFile(new File(fileInfo.getPathOnClient()));
//                    }
//                    fileInfo.setChunkNumber(fileInfo.getChunkNumber() + 1);
//
//                    // if(fileInfo.getChunkNumber()%100 == 0)
//                    System.out.println(fileInfo.getChunkNumber() + " / " + fileInfo.getCountOfChunks());
//
//                    RandomAccessFile randomAccessFile = ClientInfo.getRandomAccessFile();
//                    FileChannel fileChannel = randomAccessFile.getChannel();
//
//
//                    int chunkSize;
//                    if (fileInfo.getChunkNumber() == fileInfo.getCountOfChunks() - 1 && fileInfo.getLastChunkSize() != 0)
//                        chunkSize = fileInfo.getLastChunkSize();
//                    else
//                        chunkSize = fileInfo.getChunkSize();
//                    ChunkOfFile chunkOfFile = ClientInfo.getChunkOfFile();
//                    ctx.writeAndFlush(chunkOfFile);
//                }
//            }
//
//
//        }


