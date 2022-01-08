package Tests.hw8test;



import com.hw8testing.UserService;
import com.hw8testing.UserVal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public UserVal userValidator() {
        return new UserVal();
    }

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }
}
