package com.zhenming.demo.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class HttpClientTest {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientTest.class);

    @Test
    void test() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.baidu.com"))
                    .GET()
                    .build();
            //同步请求
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            LOG.info("code:{}, body:{}", response.statusCode(), response.body());

            //异步请求
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(LOG::info)
                    .join();
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e);
        }
    }
}
