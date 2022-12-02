package me.ant.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;
import java.io.IOException;

/**
 * <p>
 * 资源服务配置
 * </P>
 *
 * @author Ant
 * @since 2022/12/02 4:26 下午
 **/
@Configuration
@EnableResourceServer //启用资源服务器
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法注解方式来进行权限控制
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * <p>
     * 声明了资源服务器的ID是userservice，声明了资源服务器的TokenStore是JWT
     * </p>
     *
     * @param resources
     * @return void
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("userservice").tokenStore(tokenStore());
    }

    /**
     * <p>
     * 配置TokenStore
     * </p>
     *
     * @param
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * <p>
     * 配置公钥
     * </p>
     *
     * @param
     * @return JwtAccessTokenConverter
     */
    protected JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("public.cert");
        String publicKey = null;

        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tokenConverter.setVerifierKey(publicKey);
        return tokenConverter;
    }

    /**
     * <p>
     * 配置了除了/user路径之外的请求可以匿名访问
     * </p>
     *
     * @param http
     * @return void
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .anyRequest().permitAll();
    }

}
