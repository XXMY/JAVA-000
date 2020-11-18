package io.github.kimmking.gateway.configuration;

import io.github.kimmking.gateway.inbound.HttpInboundServer;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class NettyServerInitialization implements InitializingBean, EnvironmentAware {
    private final Logger logger = LoggerFactory.getLogger(NettyServerInitialization.class);
    private final static String GATEWAY_NAME = "NIOGateway";
    private final static String GATEWAY_VERSION = "1.0.0";

    private Environment environment;
    @Setter
    private HttpInboundServer httpInboundServer;


    @Override
    public void afterPropertiesSet() throws Exception {
        String proxyPort = environment.getProperty("proxyPort","8888");
        int port = Integer.parseInt(proxyPort);
        logger.info("netty server {} {} start initialization, listening on port: {}",GATEWAY_NAME,GATEWAY_VERSION, port);
        httpInboundServer.run();
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
