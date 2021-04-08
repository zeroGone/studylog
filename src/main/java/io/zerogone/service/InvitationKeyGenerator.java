package io.zerogone.service;

public class InvitationKeyGenerator {
    private static final String VALUES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    private final StringBuilder builder = new StringBuilder();

    public String generateKey(int length) {
        builder.delete(0, builder.length());
        for (int number = 1; number <= length; number += 1) {
            builder.append(getRandomCharacter());
        }
        return builder.toString();
    }

    private char getRandomCharacter() {
        int randomIndex = (int) (Math.random() * VALUES.length());
        return VALUES.charAt(randomIndex);
    }
}
