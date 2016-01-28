/**
 * Copyright 2015 the original author or authors.
 */
package io.vertx.workshop.map;

/**
 * Generic Map related exception.
 *
 * @author Paulo Lopes
 */
public final class MapException extends RuntimeException {

  public MapException(String message) {
    super(message);
  }

  public MapException(Throwable cause) {
    super(cause);
  }

  public MapException(String message, Throwable cause) {
    super(message, cause);
  }
}
