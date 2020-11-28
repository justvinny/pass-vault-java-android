package com.example.pass_vault.utilities;

import java.util.ArrayList;
import java.util.Random;

public class PasswordGenerator {
    private static final int[] lowercaseRange = {'a', 'z'};
    private static final int[] uppercaseRange = {'A', 'Z'};
    private static final int[] numbersRange = {'0', '9'};
    private static final char[] specialCharacters = {'-', '_', '@', '#', '$', '%', '^', '&', '*'};
    private static final Random random = new Random();

    public static String generate(int nCharacters, boolean isUpper, boolean isLower, boolean isNumber, boolean isSpecialChar) {
        ArrayList<ArrayList<Character>> allowedCharacters = new ArrayList<>();

        if (isUpper) {
            allowedCharacters.add(addUppercaseLetters());
        }

        if (isLower) {
            allowedCharacters.add(addLowerCaseLetters());
        }

        if (isNumber) {
            allowedCharacters.add(addNumbers());
        }

        if (isSpecialChar) {
            allowedCharacters.add(addSpecialCharacters());
        }

        return passwordGenerated(nCharacters, allowedCharacters);
    }

    private static ArrayList<Character> addUppercaseLetters() {
        ArrayList<Character> upperCharacters = new ArrayList<>();

        for (int i = uppercaseRange[0]; i < uppercaseRange[1]; i++) {
            upperCharacters.add((char) i);
        }

        return upperCharacters;
    }

    private static ArrayList<Character> addLowerCaseLetters() {
        ArrayList<Character> lowerCharacters = new ArrayList<>();

        for (int i = lowercaseRange[0]; i < lowercaseRange[1]; i++) {
            lowerCharacters.add((char) i);
        }

        return lowerCharacters;
    }

    private static ArrayList<Character> addNumbers() {
        ArrayList<Character> numbers = new ArrayList<>();

        for (int i = numbersRange[0]; i < numbersRange[1]; i++) {
            numbers.add((char) i);
        }


        return numbers;
    }

    private static ArrayList<Character> addSpecialCharacters() {
        ArrayList<Character> specialCharactersList = new ArrayList<>();

        for (Character specialCharacter : specialCharacters) {
            specialCharactersList.add(specialCharacter);
        }
        return specialCharactersList;
    }

    private static String passwordGenerated(int nCharacters, ArrayList<ArrayList<Character>> allowedCharacters) {
        StringBuilder generatedPassword = new StringBuilder();

        if (!allowedCharacters.isEmpty()) {
            for (int i = 0; i < nCharacters; i++) {
                int randomIndex = random.nextInt(allowedCharacters.size());
                int randomArraySize = allowedCharacters.get(randomIndex).size();
                char randomCharacter = allowedCharacters.get(randomIndex).get(random.nextInt(randomArraySize));

                generatedPassword.append(randomCharacter);
            }
        }

        return generatedPassword.toString();
    }
}
