package cloud.client.handlers;

import cloud.Chunk;
import cloud.files.FileInfo;
import cloud.files.UploadResponseOK;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class ObjectEncoder extends MessageToByteEncoder<Object> { //TODO переименовать
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object serializable, ByteBuf byteBuf) throws Exception {
        System.out.println("encode");
        if (serializable instanceof FileInfo  ) {
            System.out.println("EncodeAndWrite FileInfo");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectEncoderOutputStream encoderOutputStream = new ObjectEncoderOutputStream(bos);
            encoderOutputStream.writeObject(serializable);
            encoderOutputStream.flush();
            byte[] bytes = bos.toByteArray();
            byteBuf.writeBytes(bytes);
        }
        if (serializable instanceof Chunk) {
            System.out.println("EncodeAndWrite Chunk");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectEncoderOutputStream encoderOutputStream = new ObjectEncoderOutputStream(bos);
            encoderOutputStream.writeObject(serializable);
            encoderOutputStream.flush();
            byte[] bytes = bos.toByteArray();
            byteBuf.writeBytes(bytes);
            bos.close();
            encoderOutputStream.close();
        }
    }
}
