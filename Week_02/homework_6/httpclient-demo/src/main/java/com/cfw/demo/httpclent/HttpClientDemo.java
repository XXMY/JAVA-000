package com.cfw.demo.httpclent;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpClientDemo {
    private static Logger logger = LoggerFactory.getLogger(HttpClientDemo.class);

    public static void main(String[] args) {

        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet httpGet = new HttpGet("http://localhost:8088/api/hello");
            try(CloseableHttpResponse httpResponse = httpClient.execute(httpGet)){
                HttpEntity httpEntity = httpResponse.getEntity();
                logger.info("Http Status: {}, Http Body: {}",httpResponse.getStatusLine().getStatusCode(),EntityUtils.toString(httpEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
