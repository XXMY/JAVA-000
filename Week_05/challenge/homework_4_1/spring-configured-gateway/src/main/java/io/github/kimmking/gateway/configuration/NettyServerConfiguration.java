package io.github.kimmking.gateway.configuration;

import io.github.kimmking.gateway.filter.AddHeaderFilter;
import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.inbound.HttpInboundHandler;
import io.github.kimmking.gateway.inbound.HttpInboundInitializer;
import io.github.kimmking.gateway.inbound.HttpInboundServer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class NettyServerConfiguration {
    @Bean
    public NettyServerInitialization nettyServerInitialization(Environment environment){
        NettyServerInitialization nettyServerInitialization = new NettyServerInitialization();
        nettyServerInitialization.setHttpInboundServer(httpInboundServer(environment));

        return nettyServerInitialization;
    }

    private HttpInboundServer httpInboundServer(Environment environment){
        String proxyServer = environment.getProperty("proxyServer","http://www.baidu.com");
        String proxyPort = environment.getProperty("proxyPort","8888");
        int port = Integer.parseInt(proxyPort);
        HttpInboundServer httpInboundServer = new HttpInboundServer();
        httpInboundServer.setPort(port);
        httpInboundServer.setProxyServer(proxyServer);
        httpInboundServer.setHttpInboundInitializer(httpInboundInitializer(proxyServer,filters()));

        return httpInboundServer;
    }

    private List<HttpRequestFilter> filters(){
        List<HttpRequestFilter> filters = new ArrayList<>();
        filters.add(new AddHeaderFilter());

        return filters;
    }

    private HttpInboundInitializer httpInboundInitializer(String proxyServer, List<HttpRequestFilter> filters){
        HttpInboundInitializer httpInboundInitializer = new HttpInboundInitializer();
        httpInboundInitializer.setHttpInboundHandler(new HttpInboundHandler(proxyServer,filters));

        return httpInboundInitializer;
    }
}
