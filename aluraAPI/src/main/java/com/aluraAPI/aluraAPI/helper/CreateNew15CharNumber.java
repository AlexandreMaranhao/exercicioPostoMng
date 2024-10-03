package com.aluraAPI.aluraAPI.helper;

import java.security.SecureRandom;

public class CreateNew15CharNumber {
    private static final String CHARACTERS = "0123456789";
    private static final int STRING_LENGTH = 15;
    private static final SecureRandom random = new SecureRandom();

    public static String generateInvoiceNumber() {
        StringBuilder invoiceNumber = new StringBuilder(STRING_LENGTH);
        for (int i = 0; i < STRING_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            invoiceNumber.append(CHARACTERS.charAt(index));
        }
        return invoiceNumber.toString();
    }

    public static String generateLoyaltyNumber() {
        StringBuilder LoyaltyNumber = new StringBuilder(STRING_LENGTH);
        for (int i = 0; i < STRING_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            LoyaltyNumber.append(CHARACTERS.charAt(index));
        }
        return LoyaltyNumber.toString();
    }

}
