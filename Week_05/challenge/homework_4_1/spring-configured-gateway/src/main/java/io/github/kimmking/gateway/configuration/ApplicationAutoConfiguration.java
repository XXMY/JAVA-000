package io.github.kimmking.gateway.configuration;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnMissingBean(name = "nettyServerInitialization")
@Import(NettyServerConfiguration.class)
public class ApplicationAutoConfiguration {

}
