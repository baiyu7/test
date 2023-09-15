package com.example.demo1;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author baiyu
 * @Date 2023/3/15 20:29
 * @Description
 */
@RestController
public class GatewayController {

    @Autowired
    private Environment environment;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @RequestMapping(value = "/gateway")
    public String gateway() {
        return "get properties value by ''@Value'' :" +
                //2、使用Environment读取
                System.getProperty("spring.application.name") +
                " , sex=" + environment.getProperty("spring.application.name") +
                " , address=" + environment.getProperty("spring.url");

    }

    @RequestMapping(value = "/es")
    public Boolean es() throws IOException {
        RestHighLevelClient esClient = (RestHighLevelClient) AppConTest.getBean("restHighLevelClient");

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");

        CreateIndexResponse createIndexResponse = esClient.esClient().indices().create(createIndexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        return acknowledged;
    }



}
