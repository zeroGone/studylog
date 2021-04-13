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
        Assert.assertNotNull(generator.generateKey());
        Assert.assertEquals(15, generator.generateKey().length());
    }
}