package io.zerogone.blog.service;

public class InvitationKeyGenerator {
    private static final String VALUES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static final int KEY_LENGTH = 15;
    private final StringBuilder builder = new StringBuilder();

    public String generateKey() {
        builder.delete(0, builder.length());
        for (int number = 1; number <= KEY_LENGTH; number += 1) {
            builder.append(getRandomCharacter());
        }
        return builder.toString();
    }

    private char getRandomCharacter() {
        int randomIndex = (int) (Math.random() * VALUES.length());
        return VALUES.charAt(randomIndex);
    }
}
