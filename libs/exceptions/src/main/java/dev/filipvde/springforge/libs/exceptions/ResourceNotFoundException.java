package dev.filipvde.springforge.libs.exceptions;

import lombok.experimental.StandardException;

/**
 * Exception to throw when a REST resource is cannot be found.
 */
@StandardException
public class ResourceNotFoundException extends RuntimeException {

}