package org.elsys.netprog.rest;

import java.util.*;
import java.util.stream.IntStream;

public class Game {
    private String gameId;
    private String secret;
    private int turnsCount = 0;
    private boolean success = false;

    public Game(Integer secret) {
        this.gameId = UUID.randomUUID().toString();
        this.secret = secret.toString();
    }

    public String getGameId() {
        return gameId;
    }


    public String getSecret() {
        if (isSuccess()) {
            return secret;
        } else {
            return "****";
        }
    }

    public int getTurnsCount() {
        return turnsCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public HashMap guess(Integer number) {
        HashMap<Integer, Integer> cowsAndBullsResult = new HashMap<>();
        Set<Integer> secretArr = numToSet(Integer.valueOf(secret));
        Set<Integer> numberArr = numToSet(number);
        int bulls = calculateBulls(secretArr, numberArr);
        int cows = calculateCows(secretArr, numberArr);

        cowsAndBullsResult.put(cows, bulls);
        turnsCount++;

        if (cows == 4 && bulls == 4) {
            this.success = true;
        }

        return cowsAndBullsResult;

    }

    private static Set<Integer> numToSet(int number) {
        Set<Integer> result = new LinkedHashSet<>();
        for (int i = 1000, j = 3; i > 0 ; i /= 10, j--) {
            result.add((number / i) % 10);
        }
        return result;
    }

    private int calculateBulls(Set<Integer> secret, Set<Integer> number) {
        int bulls = 0;
        int[] secretArr = secret.stream().mapToInt(Number::intValue).toArray();
        int[] numberArr = number.stream().mapToInt(Number::intValue).toArray();

        for (int i = 0; i < secretArr.length; i++) {
            if (secretArr[i] == numberArr[i]) {
                bulls++;
            }
        }

        return bulls;
    }

    private int calculateCows(Set<Integer> secret, Set<Integer> number) {
        int cows = 0;
        int[] secretArr = secret.stream().mapToInt(Number::intValue).toArray();
        int[] numberArr = number.stream().mapToInt(Number::intValue).toArray();

        for (int i = 0; i < secretArr.length; i++) {
            final int curr = secretArr[i];
            if (IntStream.of(numberArr).anyMatch(x -> x == curr)) {
                cows++;
            }
        }

        return cows;
    }

    public static boolean isValidNumber(Integer number) {
        return numToSet(number).size() == number.toString().length();
    }
}
