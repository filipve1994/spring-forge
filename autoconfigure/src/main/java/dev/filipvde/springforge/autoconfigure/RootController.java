package dev.filipvde.springforge.autoconfigure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNullElse;

@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "springforge.api-root-controller", name = "enabled", matchIfMissing = true)
// @EnableConfigurationProperties(CommonControllerAdviceProperties.class)
@RestController
@RequestMapping(value = "/api")
@Tag(name = "API")
@RequiredArgsConstructor
@Slf4j
public class RootController {

	private final Optional<BuildProperties> buildProperties;

	private final Optional<GitProperties> gitProperties;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(operationId = "getApiInfo",
			summary = "Returns information about PI, about logged user and some configuration parameters.")
	public Map<Object, Object> getApiInfo() {
		return Map.of("info",
				Map.of("name", buildProperties.map(BuildProperties::getName).orElse(""), "version",
						buildProperties.map(BuildProperties::getVersion).orElse(""), "description",
						buildProperties.map(props -> props.get("description")).orElse(""), "build-time",
						requireNonNullElse(buildProperties.map(BuildProperties::getTime).orElse(null), ""),
						"git-commit-id", gitProperties.map(GitProperties::getShortCommitId).orElse("")));
	}

}
