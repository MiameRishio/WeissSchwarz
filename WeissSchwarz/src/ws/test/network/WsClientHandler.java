package ws.test.network;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * Handles a client-side channel.
 */
public class WsClientHandler extends SimpleChannelHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		System.out.println((String) e.getMessage());
		super.messageReceived(ctx, e);
	}


	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		System.out.println("Server Disconnected");
		super.channelDisconnected(ctx, e);
	}


	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("Server Connected");
		Channel channel = e.getChannel();
		if (channel.isWritable()) {
			if (cb.writable()) {
				cb.writeChar('A');
				cb.writeChar('B');
				cb.writeChar('C');
			}
		}
		channel.write(cb);
		super.channelConnected(ctx, e);
	}
	
	private ChannelBuffer cb = ChannelBuffers.dynamicBuffer(1024 * 100);
}