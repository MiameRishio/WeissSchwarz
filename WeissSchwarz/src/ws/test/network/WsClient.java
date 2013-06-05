package ws.test.network;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * Keeps sending random data to the specified address.
 */
public class WsClient {
	public WsClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() {
		// Configure the client.
		ClientBootstrap bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));

		// Set up the pipeline factory.
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new WsClientHandler());
			}
		});

		while (true) {
			// Start the connection attempt.
			ChannelFuture future = bootstrap.connect(new InetSocketAddress(host,
					port));
			// Wait until the connection is closed or the connection attempt fails.
			future.getChannel().getCloseFuture().awaitUninterruptibly();
		}

		// Shut down thread pools to exit.
		//bootstrap.releaseExternalResources();
	}

	public static void main(String[] args) throws Exception {
		new WsClient(HOST, PORT).run();
	}

	private final String host;
	private final int port;
	private final static String HOST = "127.0.0.1";
	private final static int PORT = 8888;
}