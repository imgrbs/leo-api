package app.leo.matching;

import app.leo.matching.interceptors.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor());
    }

    @Bean
    public MappedInterceptor tokenInterceptor() {
        return new MappedInterceptor(
                new String[] {"/**", ""},
                new String[] {"/actuator/**", "/error/**"},
                new TokenInterceptor
                        (jwtSecret));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*");
    }
}