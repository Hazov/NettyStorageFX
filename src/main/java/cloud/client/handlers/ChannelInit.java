package cloud.client.handlers;

import cloud.ObjDec;
import cloud.ObjEnc;
import io.netty.channel.ChannelInitializer;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ChannelInit extends ChannelInitializer<SocketChannel> {
   private SocketChannel socketChannel = null;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();


                            //(МНЕ ПРИХОДЯТ СВЕРХУ)              (Я ОТПРАВЛЯЮ НАВЕРХ)

        //СТАРОЕ
//        pipeline.addLast(new ObjEnc());                                                 //Out
//        pipeline.addLast(new ObjDec(ClassResolvers.cacheDisabled(null)));     //In
//        //pipeline.addLast(new FileErrReceiver());
//       // pipeline.addLast(new ChWr());                                                   //Duplex (Out) 2
//        pipeline.addLast(new FileSenderHandler());                                      //In (в ответ out (chunk))
//        pipeline.addLast(new FileInfoSenderHandler());

        //ПРОБУЮ ByteArrayOutputStream/ObjectEncoderOutputStream
        pipeline.addLast(new ChWr());
        pipeline.addLast(/*new ObjectDecoder()*/ new ObjDec(ClassResolvers.cacheDisabled(null))); //in
        pipeline.addLast(/*new ObjectEncoder()*/ new ObjEnc()); //out
        pipeline.addLast(new FileSenderHandler()); //in (в ответ out)
        pipeline.addLast(new FileInfoSenderHandler()); //out


        this.socketChannel = socketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
}
