package com.tiger.tigeredu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by IntelliJ IDEA
 *
 * @author TIGER
 * @Package: com.tiger.tigeredu.eduservice
 * @ClassName: EduApplication
 * @date 2020/6/29 23:01
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tiger.tigeredu"})
public class EduApplication {

    public static void main(String[] args){
        SpringApplication.run(EduApplication.class,args);
    }
}
