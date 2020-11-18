package io.github.kimmking.gateway.outbound.httpclient4;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static io.netty.handler.codec.rtsp.RtspHeaderNames.CONNECTION;
import static io.netty.handler.codec.rtsp.RtspHeaderValues.KEEP_ALIVE;

/**
 * The code for homework 1
 * Use Httpclient to request backend.
 */
public class HomeworkHttpOutboundHandler extends HttpOutboundHandler{
    private static Logger logger = LoggerFactory.getLogger(HomeworkHttpOutboundHandler.class);

    public HomeworkHttpOutboundHandler(String backendUrl) {
        super(backendUrl);
    }


    @Override
    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        byte[] httpResponseContentBytes;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet httpGet = new HttpGet(backendUrl);
//            httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
            // 透传 headers
            enhanceHttpRequest(fullRequest,httpGet);
            try(CloseableHttpResponse httpResponse = httpClient.execute(httpGet)){
                HttpEntity httpEntity = httpResponse.getEntity();
                String data = EntityUtils.toString(httpEntity, CharsetUtil.UTF_8);
                logger.info("Http Status: {}, Http Body: {}",httpResponse.getStatusLine().getStatusCode(), data);

                httpResponseContentBytes = data.getBytes();
                response = new DefaultFullHttpResponse(
                        HttpVersion.HTTP_1_1,
                        new HttpResponseStatus(httpResponse.getStatusLine().getStatusCode(),
                                httpResponse.getStatusLine().getReasonPhrase()),

                        Unpooled.wrappedBuffer(httpResponseContentBytes)
                );

                enhanceHttpResponse(response,httpResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    new HttpResponseStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR.code(), e.getMessage()),
                    Unpooled.wrappedBuffer(e.getMessage().getBytes())
            );
        }finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    // 用于对请求头的增强，将客户端的请求头透传给服务端
    private void enhanceHttpRequest(FullHttpRequest fullRequest, HttpRequestBase httpRequest){
        System.out.println("request proxy header: ");
        fullRequest.headers().forEach(header -> {
            System.out.println(header.getKey() + ": " + header.getValue());
            if (!header.getKey().equalsIgnoreCase("host"))
                httpRequest.addHeader(header.getKey(),header.getValue());
        });
        System.out.println(System.lineSeparator());
    }
    // 用于对响应头的增强，将服务端的响应头透传给客户端
    private void enhanceHttpResponse(HttpResponse httpResponse, org.apache.http.HttpResponse httpClientResponse){
        System.out.println("response proxy header: ");
        Stream.of(httpClientResponse.getAllHeaders()).forEach(
                header -> {
                    System.out.println(header.getName() + ": " + header.getValue());
                    httpResponse.headers().add(header.getName(),header.getValue());
                }
        );

    }
}
