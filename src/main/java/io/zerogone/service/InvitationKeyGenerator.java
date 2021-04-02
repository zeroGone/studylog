package io.zerogone.service;

public class InvitationKeyGenerator {
    private static final int ASCII_START_CODE = 33;
    private static final int RANDOM_VALUE = 94;

    private final StringBuilder builder = new StringBuilder();

    public String generateKey(int length) {
        builder.delete(0, builder.length());
        for (int number = 1; number <= length; number += 1) {
            builder.append(getRandomCharacter());
        }
        return builder.toString();
    }

    private char getRandomCharacter() {
        int randomValue = (int) (Math.random() * RANDOM_VALUE);
        int randomAsciiCode = randomValue + ASCII_START_CODE;
        return (char) randomAsciiCode;
    }
}
