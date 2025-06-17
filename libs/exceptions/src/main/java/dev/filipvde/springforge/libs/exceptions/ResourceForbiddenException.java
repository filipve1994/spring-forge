package dev.filipvde.springforge.libs.exceptions;

import lombok.experimental.StandardException;

/**
 * Exception to throw when a REST resource cannot be accessed or deleted.
 */
@StandardException
public class ResourceForbiddenException extends RuntimeException {

}