package org.neet.code.practice;

import java.util.*;

public class longestSubStringNonRepeat {


    public static void main (String[] args)
    {

        System.out.print("Enter a String: ");

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int len =0;

        int l=0;
        char[] sChr = str.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for(int i=0; i<str.length();i++){
            while(set.contains(sChr[i]) )
            {
                set.remove(sChr[l]);
                l++;
            }
            set.add(sChr[i]);
            len = Math.max(set.size(), len);
            System.out.println(str.substring(l,i+1));
        }

        System.out.println(len);
    }
}
