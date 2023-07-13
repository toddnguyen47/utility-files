package com.github.toddnguyen47.testfileshelpers;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TestJsonProcessingException extends JsonProcessingException {

  private static final long serialVersionUID = -4755014558309339313L;
  public static final String EXCEPTION_MESSAGE = "EXCEPTION_MESSAGE";

  public TestJsonProcessingException() {
    super(EXCEPTION_MESSAGE);
  }

  public TestJsonProcessingException(final String msg) {
    super(msg);
  }
}
