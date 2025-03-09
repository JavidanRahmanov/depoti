//package com.cavidanrahmanov.depoti.config;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.media.ArraySchema;
//import io.swagger.v3.oas.models.media.Content;
//import io.swagger.v3.oas.models.media.Schema;
//import io.swagger.v3.oas.models.parameters.RequestBody;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components()
//                        .addRequestBodies("imageUpload",
//                                new RequestBody()
//                                        .content(new Content().addMediaType(org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE,
//                                                new io.swagger.v3.oas.models.media.MediaType().schema(new ArraySchema()
//                                                        .items(new Schema<>().type("string")
//                                                                .format("binary")))))));
//    }
//}
