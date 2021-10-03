package cloud.client;

import cloud.commands.CommandsToServer;
import cloud.commands.UploadCommand;
import cloud.files.FileInfo;
import cloud.files.UploadResponseOK;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DateFormatter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;

public class Loop {
    private Scanner scanner = new Scanner(System.in);
    SocketChannel socketChannel;

    public Loop(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public String run() throws IOException {
        while (true){
            int i = scanner.nextInt();
            System.out.println("1 - скачать файл");
            if(i == 1){
                File file = new File("D:\\javaproj\\111\\client\\4.rar");
                FileInfo fileInfo = new FileInfo(file, 32000);
                System.out.println(fileInfo.getCountOfChunks());
                System.out.println("sss" + fileInfo);
                socketChannel.pipeline().channel().writeAndFlush(fileInfo);
            }
            if(i == 2){
                File file = new File("D:\\javaproj\\111\\client\\1.jpg");
                FileInfo fileInfo = new FileInfo(file, 1024);
                socketChannel.pipeline().channel().writeAndFlush(fileInfo);
            }
            if(i == 3){
                File file = new File("D:\\javaproj\\111\\client\\3.psd");
                FileInfo fileInfo = new FileInfo(file, 128000);
                socketChannel.pipeline().channel().writeAndFlush(fileInfo);
            }

        }



    }

}
