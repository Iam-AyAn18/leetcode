package org.neet.code.practice;

import java.util.HashMap;
import java.util.Scanner;

public class firstNonRepeatingCharacter {

    // input: aabbcdee
    // output: c

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a String: ");
        String str = sc.nextLine();

        char[] chStr = str.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();

        for (char value : chStr) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        for (char c : chStr) {
            if (map.get(c) == 1) {
                System.out.print(c);
                break;
            }
        }

    }
}
