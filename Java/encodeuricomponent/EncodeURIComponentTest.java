package encodeuricomponentt;

import org.junit.Assert;
import org.junit.Test;

public class EncodeURIComponentTest {

  @Test
  public void test_GivenAmpersandAndSpace_ThenEncodeProperly() {
    // -- ARRANGE --
    String queryParamValue = "hello& world";
    // -- ACT --
    String encoded = EncodeURIComponent.encodeComponent(queryParamValue);
    // -- ASSERT --
    Assert.assertEquals("hello%26%20world", encoded);
  }

  @Test
  public void test_GivenAmpersandPlusAndSpace_ThenEncodeProperly() {
    // -- ARRANGE --
    String queryParamValue = "hello& world+ 42";
    // -- ACT --
    String encoded = EncodeURIComponent.encodeComponent(queryParamValue);
    // -- ASSERT --
    Assert.assertEquals("hello%26%20world%2B%2042", encoded);
  }

  @Test
  public void test_GivenWrongEncoder_ThenReturnValueBeingPassedIn() {
    // -- ARRANGE --
    String queryParamValue = "hello& world";
    // -- ACT --
    String encoded = EncodeURIComponent.encodeComponentCharset(queryParamValue, "asdf");
    // -- ASSERT --
    Assert.assertEquals(queryParamValue, encoded);
  }
}
