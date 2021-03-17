public class PrintFormatted {
  /**
   *  Ref: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#format-java.lang.String-java.lang.Object...-
   */
  private void printFmt(String format, Object... args) {
    System.out.println(String.format(format, args));
  }
}
