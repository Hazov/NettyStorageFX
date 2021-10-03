package cloud.server.handlers;

import cloud.files.FileInfo;
import cloud.files.UploadResponseOK;
import cloud.server.state.State;
import cloud.server.utils.FileUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.serialization.ObjectDecoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInfoReceiverHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("FILEINF");
        final String ROOT_PATH = "D:\\javaproj\\111\\server\\";   //TODO перенести в конфиг префикс
        if (msg instanceof FileInfo) {
            FileInfo fileInfo = (FileInfo) msg;
            System.out.println(fileInfo.getCountOfChunks());
            String fileName = fileInfo.getName();
            int folderName = ctx.channel().hashCode();  //TODO название папки по хэшу пользователя
            String rootClientDirectory = ROOT_PATH + folderName + "\\";
            Path rootClientPath = Paths.get(rootClientDirectory);
            Path currentFilePath = Paths.get(rootClientDirectory + fileName);
            fileInfo.setPathOnServer(currentFilePath.toAbsolutePath().toString());
            if (Files.notExists(rootClientPath)) Files.createDirectories(rootClientPath);
            if (Files.notExists(currentFilePath)) Files.createFile(currentFilePath);

            FileUtils.addCurrentFile(folderName, fileInfo);
           // ctx.pipeline().remove(ObjectDecoder.class);                         //TODO в логику изменения состояния
            FileUtils.changeCurrentStateForClient(folderName, State.READ_FILE);               //TODO !folderName
            System.out.println("Отправляю FileInfo обратно");

            ctx.pipeline().remove(ObjectDecoder.class);


            ctx.writeAndFlush(fileInfo);
        } else {
            ctx.fireChannelRead(msg);
        }
    }
}
