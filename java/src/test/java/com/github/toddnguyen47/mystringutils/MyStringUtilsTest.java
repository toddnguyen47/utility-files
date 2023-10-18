package com.github.toddnguyen47.mystringutils;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class MyStringUtilsTest {

  private static final String LOREM_IPSUM =
      "           Lorem    ipsum dolor sit amet,     consectetur adipiscing elit, sed do"
          + " eiusmod tempor incididunt ut labore et dolore magna aliqua. Consectetur"
          + " adipiscing elit duis tristique. Ridiculus mus mauris vitae ultricies leo. Urna"
          + " nec tincidunt praesent semper feugiat. Sit amet volutpat consequat mauris nunc."
          + " Tristique nulla aliquet enim tortor at. Metus aliquam eleifend mi in nulla. Non"
          + " blandit massa enim nec dui nunc mattis enim ut. Sit amet nulla facilisi morbi"
          + " tempus iaculis urna. Eget gravida cum sociis natoque penatibus et magnis dis."
          + " Nulla facilisi etiam dignissim diam. Erat nam at lectus urna duis convallis"
          + " convallis. Ultricies lacus sed turpis tincidunt id aliquet. A diam maecenas sed"
          + " enim. Felis eget velit aliquet sagittis id consectetur purus ut. Tellus"
          + " molestie nunc non blandit massa. In hendrerit gravida rutrum quisque. Rutrum"
          + " quisque non tellus orci ac. Viverra aliquet eget sit amet. Tristique et egestas"
          + " quis ipsum suspendisse.\n"
          + "\n"
          + "Elementum integer enim neque volutpat ac. Semper risus in hendrerit gravida"
          + " rutrum quisque. Non diam phasellus vestibulum lorem. Vitae nunc sed velit"
          + " dignissim sodales. Eros in cursus turpis massa tincidunt dui ut ornare lectus."
          + " Fames ac turpis egestas sed tempus urna et pharetra pharetra. Nisi quis"
          + " eleifend quam adipiscing. Vulputate odio ut enim blandit volutpat. Vivamus arcu"
          + " felis bibendum ut tristique. Lacus luctus accumsan tortor posuere. Nisl purus"
          + " in mollis nunc sed id semper risus. Iaculis at erat pellentesque adipiscing"
          + " commodo. Dolor sit amet consectetur adipiscing elit duis tristique"
          + " sollicitudin. Vulputate enim nulla aliquet porttitor lacus. Ultricies mi quis"
          + " hendrerit dolor magna eget. Consectetur lorem donec massa sapien faucibus."
          + " Neque egestas congue quisque egestas. Quis risus sed vulputate odio ut enim"
          + " blandit. Amet volutpat consequat mauris nunc congue nisi vitae suscipit"
          + " tellus.";

  @Test
  public void test_GivenNull_When_ThenNoWhitespaceIsReturned() {
    final String actualString = MyStringUtils.getAbbrevStringNoWhitespace(null);

    Assert.assertNull(actualString);
  }

  @Test
  public void test_GivenWhitespaceStringLessThanMaxWidth_When_ThenNoWhitespaceIsReturned() {
    final String inputStr = LOREM_IPSUM.substring(0, MyStringUtils.DEFAULT_MAX_WIDTH - 3);
    final String actualString = MyStringUtils.getAbbrevStringNoWhitespace(inputStr);

    final String expectedString =
        "Loremipsumdolorsitamet,consecteturadipiscingelit,seddoeiusmodtemporincididuntutlaboreetdoloremagnaaliqua.Consecteturadipiscingelitduistristique.Ridiculusmusmaurisvitaeultriciesleo.Urnanectinciduntpraesentsemperfeugiat.Sitametvolutpatconsequatmaurisnunc.Tristiquenullaaliquetenimtortorat.Metusaliquameleifendmiinnulla.Nonblanditmassaenimnecduinuncmattisenimut.Sitametnullafacilisimorbitempusiaculisurna.Eget";
    MatcherAssert.assertThat(actualString, Matchers.equalTo(expectedString));
  }

  @Test
  public void test_GivenWhitespaceStringGreaterThanMaxWidth_When_ThenNoWhitespaceIsReturned() {
    final String inputStr = LOREM_IPSUM.substring(0, MyStringUtils.DEFAULT_MAX_WIDTH * 2);
    final String actualString = MyStringUtils.getAbbrevStringNoWhitespace(inputStr);

    final String expectedString =
        "Loremipsumdolorsitamet,consecteturadipiscingelit,seddoeiusmodtemporincididuntutlaboreetdoloremagnaaliqua.Consecteturadipiscingelitduistristique.Ridiculusmusmaurisvitaeultriciesleo.Urnanectinciduntpraesentsemperfeugiat.Sitametvolutpatconsequatmaurisnunc.Tristiquenullaaliquetenimtortorat.Metusaliquameleifendmiinnulla.Nonblanditmassaenimnecduinuncmattisenimut.Sitametnullafacilisimorbitempusiaculisurna.Egetgravidacumsociisnatoquepenatibusetmagnisdis.Nullafacilisietiamdignissimdiam.Eratnamatlectus...";
    MatcherAssert.assertThat(actualString, Matchers.equalTo(expectedString));
  }

  @Test
  public void test_GivenEmptyString_WhenReplacingWhitespace_ThenReturnEmptyString() {

    final String actualString = MyStringUtils.removeAllWhitespace("");

    Assert.assertEquals("", actualString);
  }

  @Test
  public void test_GivenNullString_WhenReplacingWhitespace_ThenReturnNull() {

    final String actualString = MyStringUtils.removeAllWhitespace(null);

    Assert.assertEquals("", actualString);
  }

  @Test
  public void test_GivenNoWhitespace_WhenReplacingWhitespace_ThenReturnThatString() {
    final String input = "helloworld";

    final String actualString = MyStringUtils.removeAllWhitespace(input);

    Assert.assertEquals(input, actualString);
  }

  @Test
  public void tset_GivenWhitespace_WhenReplacingWhitespace_ThenReturnWhitespaceOmitted() {
    final String input =
        "{"
            + "	\"item\": \"lich bane\","
            + "	\"categories\": [\"ap\", \"movement speed\"],"
            + "	\"stats\": {"
            + "		\"ap\": 80,"
            + "		\"movement speed\": 15,"
            + "		\"effect\": \"spellblade\""
            + "	}"
            + "}";

    final String actualString = MyStringUtils.removeAllWhitespace(input);

    final String expectedString =
        "{\"item\":\"lichbane\",\"categories\":[\"ap\",\"movementspeed\"],\"stats\":{\"ap\":80,\"movementspeed\":15,\"effect\":\"spellblade\"}}";
    Assert.assertEquals(expectedString, actualString);
  }
}
