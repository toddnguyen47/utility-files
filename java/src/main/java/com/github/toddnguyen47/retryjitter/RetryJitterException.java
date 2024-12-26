package com.github.toddnguyen47.retryjitter;

public class RetryJitterException extends Exception {

  private static final long serialVersionUID = -2351682118160604042L;

  public RetryJitterException() {
    super();
  }

  public RetryJitterException(final String msg) {
    super(msg);
  }
}
