package app.leo.matching;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

import app.leo.matching.interceptors.TokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mappedInterceptor());
    }

    @Bean
    public MappedInterceptor mappedInterceptor() {
        return new MappedInterceptor(
                new String[] {"/**", ""},
                new String[] {"/actuator/**", "/error/**"},
                tokenInterceptor());
    }

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*");
    }


    @Bean
    public AmazonS3 amazonS3Client(@Value("${cloud.aws.credentials.accessKey}") String accessKey,
                                   @Value("${cloud.aws.credentials.secretKey}") String secretKey,
                                   @Value("${cloud.aws.region.static}")String region ){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.AP_SOUTHEAST_1)
                .build();

    }

}