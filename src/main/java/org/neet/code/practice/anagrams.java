package org.neet.code.practice;

import java.util.HashMap;
import java.util.Scanner;

public class anagrams {

//    Input: "listen", "silent"
//    Output: true

    public static void main(String[] args)
    {

        System.out.println("Enter two string");
        Scanner sc = new Scanner(System.in);

        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        char[] schr1 = s1.toCharArray();
        char[] schr2 = s2.toCharArray();

        boolean isAnagram = true;

        HashMap<Character, Integer> map = new HashMap<>();

        for(char ch1 : schr1){

            map.put(ch1, map.getOrDefault(ch1, 0)+1);
        }

        for(char ch2: schr2){

            if(!(map.containsKey(ch2)) || !(map.get(ch2)>=1))
            {
                isAnagram = false;
                break;
            }
            map.put(ch2, map.get(ch2)-1);
        }
        System.out.println(isAnagram);

    }
}
