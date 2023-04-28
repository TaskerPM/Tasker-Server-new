package com.example.tasker.global.config.swagger;

import com.example.tasker.global.dto.ErrorResponse;
import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableSwagger2
public class SwaggerConfig {

    private final TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .host("dev.taskerpm.shop")
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class), typeResolver.resolve(Page.class)))
                .globalRequestParameters(Arrays.asList(
                                new RequestParameterBuilder()
                                        .name("X-ACCESS-TOKEN")
                                        .description("X-ACCESS-TOKEN")
                                        .in(ParameterType.HEADER)
                                        .required(false)
                                        .build(),
                                new RequestParameterBuilder()
                                        .name("X-REFRESH-TOKEN")
                                        .description("X-REFRESH-TOKEN")
                                        .in(ParameterType.HEADER)
                                        .required(false)
                                        .build()
                        ))
                .apiInfo(this.apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.tasker"))
                .paths(PathSelectors.any())
                .build()
                .additionalModels(
                        new TypeResolver().resolve(ErrorResponse.class)
                );


    }

    @Getter @Setter
    @ApiModel
    static class Page {
        @ApiModelProperty(value = "페이지 번호(0부터 시작)")
        private Integer page;

        @ApiModelProperty(value = "사이즈(한 페이지당 게시글 개수)", allowableValues="range[0, 10]")
        private Integer size;

        @ApiModelProperty(value = "작성 시간 정렬(사용법: 컬럼명,ASC|DESC / 사용예시: replyTime,DESC)")
        private List<String> sort;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Tasker API")
                .version("1.0")
                .build();
    }

}

