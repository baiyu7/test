import com.example.demo1.AppConTest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
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
public class GatewayController {


    @Test
    public void  esIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");

        RestHighLevelClient bean = (RestHighLevelClient)AppConTest.getBean("restHighLevelClient");
        CreateIndexResponse createIndexResponse = bean.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }



}
