package me.ant.interfaces.facade;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 无需授权即可访问接口
 * </P>
 *
 * @author Ant
 * @since 2022/12/02 4:14 下午
 **/
@RestController
public class UnAuthApi {

    @GetMapping("hello")
    public String hello() {
        return "Hello";
    }
}
