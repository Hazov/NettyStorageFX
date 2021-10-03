package cloud.server.handlers;

import cloud.files.FileInfo;
import cloud.files.UploadResponseOK;
import cloud.server.state.State;
import cloud.server.utils.FileUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ObjectDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("decode");
        byte[] bytes = new byte[1025];
        System.out.println(byteBuf);
        for (int i = 0; byteBuf.readerIndex() < byteBuf.writerIndex(); i++) {
            bytes[i] = byteBuf.readByte();
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectDecoderInputStream ois = new ObjectDecoderInputStream(bis);
        Object object = null;
        try {
            object = ois.readObject();
            channelHandlerContext.fireChannelRead(object);
        } catch (StreamCorruptedException e) {
            System.out.println("SLOVIL");
        }

    }
}
