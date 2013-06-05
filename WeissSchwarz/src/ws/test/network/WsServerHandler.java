package ws.test.network;

import java.net.InetSocketAddress;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * Handles a server-side channel.
 */
public class WsServerHandler extends SimpleChannelHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		ChannelBuffer cb = (ChannelBuffer) e.getMessage();
		String buf = "";
		while (cb.readable()) {
			buf += cb.readChar();
		}
		System.out.println(buf);
		super.messageReceived(ctx, e);
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		InetSocketAddress address = (InetSocketAddress) ctx.getChannel()
				.getLocalAddress();
		System.out.println(address.getAddress().getHostAddress() + ":"
				+ address.getPort() + " Channel Disconnected");
		super.channelClosed(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		InetSocketAddress address = (InetSocketAddress) ctx.getChannel()
				.getLocalAddress();
		System.out.println(address.getAddress().getHostAddress() + ":"
				+ address.getPort() + " Channel Connected");
		super.channelConnected(ctx, e);
	}
}