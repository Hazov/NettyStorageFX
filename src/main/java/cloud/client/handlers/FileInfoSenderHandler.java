package cloud.client.handlers;

import io.netty.channel.*;

public class FileInfoSenderHandler extends ChannelOutboundHandlerAdapter {


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("Отправляю информацию о файле");
        ctx.writeAndFlush(msg);
        /** next ->
         * @see ObjectEncoder */


    }
}

