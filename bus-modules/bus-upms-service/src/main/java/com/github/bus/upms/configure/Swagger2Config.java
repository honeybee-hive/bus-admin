package com.github.bus.upms.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 接口测试配置
 *
 * @author zcq
 * @date 2018/10/21 14:03
 */
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 配置扫描的包的路径
                .apis(RequestHandlerSelectors.basePackage("com.github.bus.upms.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2接口文档")
                .description("可以直接在改文档中进行http请求的测试，如果异常请优先使用该工具测试接口")
                .version("1.0")
                .build();
    }

}
