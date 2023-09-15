package com.example.demo1;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author baiyu
 * @Date 2023/4/16 14:13
 * @Description
 */
@Configuration
public class RestHighLevelClient {

    @Bean
    public org.elasticsearch.client.RestHighLevelClient esClient(){
        return new org.elasticsearch.client.RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
    }
}
