package com.util;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.junit.Assert;
import org.junit.Test;

public class ObjectReaderWriterFactoryTest {
    @Test
    public void test_GivenObjectReaderCreated_WhenCallingFactoryMethod_ThenInstanceIsOfObjectReaderClass() {
        final ObjectReader objectReader = ObjectReaderWriterFactory.createObjectReader();

        Assert.assertEquals("class com.fasterxml.jackson.databind.ObjectReader", objectReader.getClass().toString());
    }

    @Test
    public void test_GivenObjectWriterCreated_WhenCallingFactoryMethod_ThenInstanceIsOfObjectWriterClass() {
        final ObjectWriter objectWriter = ObjectReaderWriterFactory.createObjectWriter();

        Assert.assertEquals("class com.fasterxml.jackson.databind.ObjectWriter", objectWriter.getClass().toString());
    }
}
