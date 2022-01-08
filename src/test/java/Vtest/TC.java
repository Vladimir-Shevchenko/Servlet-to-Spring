package Vtest;

import com.V.HelloResource;
import com.V.HelloService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TC {


    @Bean
    public HelloService helloService() {
        return new HelloService();
    }

    @Bean
    public HelloResource helloResource() {
        return mock(HelloResource.class);
    }


    @Bean
    public ObjectMapper mckMvc() {
        return new ObjectMapper();
    }
}
