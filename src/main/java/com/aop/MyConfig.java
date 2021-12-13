package com.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com")
@EnableAspectJAutoProxy
public class MyConfig {
    {
        System.out.println("inside aop config class");
//Test1.main(new String[]{});
    }
}
