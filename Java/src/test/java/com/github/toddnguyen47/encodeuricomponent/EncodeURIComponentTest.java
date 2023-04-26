package com.github.toddnguyen47.encodeuricomponent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class EncodeURIComponentTest {

    private static final String BASE_URL = "https://example.com";

    @Test
    public void test_GivenPathWithSpacesAndPluses_ThenEncodeCorrectly() {
        List<String> uriPaths = Arrays.asList("some-db", "id with spaces and +&");
        Map<String, String> queryParams = new HashMap<>();
        String encodedUri = EncodeURIComponent.encodeUri(BASE_URL, uriPaths, queryParams);

        String expected = "https://example.com/some-db/id%20with%20spaces%20and%20+&";
        Assert.assertEquals(expected, encodedUri);
    }

    @Test
    public void test_GivenQueryParams_ThenEncodeCorrectly() {
        List<String> uriPaths = Arrays.asList("some-db", "id with spaces and +&?");
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("secondaryId", "some secondary id with spaces   and +&");
        String encodedUri = EncodeURIComponent.encodeUri(BASE_URL, uriPaths, queryParams);

        String expected =
                "https://example.com/some-db/id%20with%20spaces%20and%20+&%3F?"
                        + "secondaryId=some%20secondary%20id%20with%20spaces%20%20%20and%20+%26";
        Assert.assertEquals(expected, encodedUri);
    }

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
