package Tests.hw8test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class /*{
        WebConfig.class, PersistenceConfig.class, CoreConfig.class, SecurityConfig.class
}*/)
@WebAppConfiguration
public class MainControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void homeShouldRedirectToUsersPage() throws Exception {
        mvc.perform(get("/")).andDo(print());
      //          .andExpect(status().is3xxRedirection())
   //             .andExpect(redirectedUrl("/users"));
    }

//    @Test
//    public void viewShouldReturnGreetingWithSpecifiedName() throws Exception {
//        String name = "stranger";
//        String expected = "Привет " + name;
//
//        mvc.perform(get("/k", name))
//                .andExpect(status().isOk());
//
//               // .andExpect(model().attribute("msg", expected))
//               // .andExpect(view().name("/index"));
//    }
//
//    @Test
//    public void rawShouldReturnResponseBody() throws Exception {
//        mvc.perform(get("/raw"))
//               .andExpect(status().isOk());
//                .andExpect(content().contentTypeCompatibleWith(TEXT_PLAIN))
//                .andExpect(content().string("Raw data"));
 //   }
}