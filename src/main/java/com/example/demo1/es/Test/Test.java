package com.example.demo1.es.Test;

import com.example.demo1.AppConTest;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @Author baiyu
 * @Date 2023/4/16 15:05
 * @Description
 */
public class Test {

    public static RestHighLevelClient restHighLevelClient;

    static {

        restHighLevelClient = new org.elasticsearch.client.RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

    }

    public static void main(String[] args) throws IOException {

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);

        GetIndexRequest getIndexRequest = new GetIndexRequest();

        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(getIndexResponse.indices());
        System.out.println(getIndexResponse.aliases());
        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());
    }
}
