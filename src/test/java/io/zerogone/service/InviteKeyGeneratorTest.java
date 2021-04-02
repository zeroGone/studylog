package io.zerogone.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InviteKeyGeneratorTest {
    private InviteKeyGenerator generator;

    @Before
    public void setUp() {
        generator = new InviteKeyGenerator();
    }

    @Test
    public void generateKey() {
        Assert.assertNotNull(generator.generateKey(100));
        Assert.assertEquals(10, generator.generateKey(10).length());
    }

    @Test
    public void generateKey_MinusNumber_returnEmptyString() {
        Assert.assertEquals("", generator.generateKey(-1));
    }
}