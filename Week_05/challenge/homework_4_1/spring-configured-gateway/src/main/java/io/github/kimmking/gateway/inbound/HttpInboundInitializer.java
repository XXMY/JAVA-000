package io.github.kimmking.gateway.inbound;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

	private HttpRequestFilter httpRequestFilter;
	private HttpInboundHandler httpInboundHandler;
	
	@Override
	public void initChannel(SocketChannel ch) {
		ch.pipeline()
				.addLast(new HttpServerCodec())
				.addLast(new HttpObjectAggregator(1024 * 1024))
				.addLast(httpInboundHandler);
	}

	private List<HttpRequestFilter> filters(){
		List<HttpRequestFilter> filters = new ArrayList<>();
		filters.add(httpRequestFilter);
		return filters;
	}
}
