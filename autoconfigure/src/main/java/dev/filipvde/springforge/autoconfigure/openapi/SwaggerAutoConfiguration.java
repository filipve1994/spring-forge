package dev.filipvde.springforge.autoconfigure.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// Ensures the bean is only loaded in servlet-based web apps
@ConditionalOnWebApplication
// ensures the configuration is only activated if springdoc-openapi is on the classpath
@ConditionalOnClass(name = "org.springdoc.core.configuration.SpringDocConfiguration")
public class SwaggerAutoConfiguration {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Spring Forge OpenAPI Demo").version("v1"));
	}

}
