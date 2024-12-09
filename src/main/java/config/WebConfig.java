package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // "/uploads/**" 경로로 요청하면 "/tmp/uploads/" 디렉토리에서 파일을 찾도록 설정
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/tmp/uploads/")
                .setCachePeriod(3600); // 캐싱을 위한 옵션 (초 단위)
    }
}