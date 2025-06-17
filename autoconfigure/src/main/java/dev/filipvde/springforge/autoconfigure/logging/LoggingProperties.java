package dev.filipvde.springforge.autoconfigure.logging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "springforge.logging")
@Validated
public class LoggingProperties {

	/**
	 * Set to true to enable JSON logging. This activates conditional logic found in the
	 * <a href=
	 * "https://github.com/filipve1994/spring-forge/blob/main/libs/logging/src/main/resources/logback-spring.xml">logback-spring.xml</a>
	 * which is in the logging library of the core project.
	 */
	private boolean jsonFormat;

}