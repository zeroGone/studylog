package io.zerogone.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvitationKeyGeneratorTest {
    private InvitationKeyGenerator generator;

    @Before
    public void setUp() {
        generator = new InvitationKeyGenerator();
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