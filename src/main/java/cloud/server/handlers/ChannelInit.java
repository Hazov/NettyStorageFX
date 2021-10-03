package cloud.server.handlers;

import cloud.ObjDec;
import cloud.ObjEnc;
import cloud.client.handlers.ObjectEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;

public class ChannelInit extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //(МНЕ ПРИХОДЯТ СВЕРХУ)              (Я ОТПРАВЛЯЮ НАВЕРХ)


//СТАРОЕ
//        pipeline.addLast("ObjectEncoder", new ObjEnc());              //OUT
//        pipeline.addLast("ObjectDecoder",new ObjDec( ClassResolvers.cacheDisabled(null)));             //IN
//        pipeline.addLast("FileInfoReciever",new FileInfoReceiverHandler());              //IN
//        pipeline.addLast("FileReciever",new FileReceiverHandler());             //IN

        //ПРОБУЮ
        pipeline.addLast(/*new ObjectEncoder()*/ new ObjEnc()); //out
        pipeline.addLast(/*new ObjectDecoder()*/ new ObjDec(ClassResolvers.cacheDisabled(null))); //in
        pipeline.addLast(new FileReceiverHandler());    //in
        pipeline.addLast(new FileInfoReceiverHandler());     //in (в ответ out)

    }
}

