package dev.filipvde.springforge.autoconfigure.controlleradvice;

import dev.filipvde.springforge.libs.exceptions.ResourceConflictException;
import dev.filipvde.springforge.libs.exceptions.ResourceForbiddenException;
import dev.filipvde.springforge.libs.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CommonControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ProblemDetail handleNotFoundException(ResourceNotFoundException e) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(ResourceForbiddenException.class)
	protected ProblemDetail handleForbiddenException(ResourceForbiddenException e) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
	}

	@ExceptionHandler(ResourceConflictException.class)
	protected ProblemDetail handleConflictException(ResourceConflictException e) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
	}

}
