package cloud.server.handlers;

import cloud.files.FileError;
import cloud.server.state.State;
import cloud.server.utils.FileUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;

public class StateChecker extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("StateCheecker");
        int clientHash = ctx.channel().hashCode();
        State state = FileUtils.checkStateForClient(clientHash);
        if(state == State.READ_FILE){
            System.out.println(1);
            ctx.writeAndFlush(new FileError());
        }else{
            System.out.println(2);
            ctx.fireChannelRead(msg);
        }

    }
}
