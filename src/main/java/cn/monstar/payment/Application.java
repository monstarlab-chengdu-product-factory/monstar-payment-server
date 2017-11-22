package cn.monstar.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangshuai
 * @version 1.0
 * @description Application
 * @date 2017/11/22 16:09
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}