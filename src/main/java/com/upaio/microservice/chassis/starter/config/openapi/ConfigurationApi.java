package com.upaio.microservice.chassis.starter.config.openapi;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ConfigurationApi {

    @Value("${api.title:default_value}")
    private String title;

    @Value("${api.version:default_value}")
    private String version;

    @Value("${api.description:default_value}")
    private String description;

    @Bean
    public OpenApiCustomiser OpenApiDocumentation(){
        return openApi -> openApi
                .info(new Info().title(title).description(description).version(version)).getPaths()
                .values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                    ApiResponses apiResponses = operation.getResponses();
                    ApiResponse apiResponseOk =
                            new ApiResponse().description("Successfull Request").content(new Content().addMediaType(
                                    org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
                    ApiResponse apiResponseBadRequest = new ApiResponse()
                            .description("Bad Request").content(new Content().addMediaType(
                                    org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
                    ApiResponse apiResponseUnauthorized =
                            new ApiResponse().description("Unauthorized")
                                    .content(new Content().addMediaType(
                                            org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
                    ApiResponse apiResponseForbidden = new ApiResponse()
                            .description("Forbidden").content(new Content().addMediaType(
                                    org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
                    ApiResponse apiResponseNotFound = new ApiResponse()
                            .description("Not Found").content(new Content().addMediaType(
                                    org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
                    ApiResponse apiResponseInternalServerError = new ApiResponse()
                            .description("Internal Server Error").content(new Content().addMediaType(
                                    org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
                    ApiResponse apiResponseServiceUnavailable = new ApiResponse()
                            .description("Service Unavailable").content(new Content().addMediaType(
                                    org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));

                    apiResponses.addApiResponse(String.valueOf(HttpStatus.OK.value()), apiResponseOk);
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                            apiResponseBadRequest);
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                            apiResponseUnauthorized);
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.FORBIDDEN.value()),
                            apiResponseForbidden);
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.NOT_FOUND.value()),
                            apiResponseNotFound);
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                            apiResponseInternalServerError);
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()),
                            apiResponseServiceUnavailable);
                }));
    }
}
