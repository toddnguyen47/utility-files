package com.github.toddnguyen47.printformatted;

public class PrintFormatted {
  /**
   *  Ref: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#format-java.lang.String-java.lang.Object...-
   */
  public void printfmtln(String format, Object... args) {
    System.out.println(String.format(format, args));
  }
}
