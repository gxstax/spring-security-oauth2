package me.ant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 受保护资源配置启动类
 * </P>
 *
 * @author Ant
 * @since 2022/12/02 4:04 下午
 **/
@SpringBootApplication
public class SourceServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SourceServerApplication.class, args);
    }
}
