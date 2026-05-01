package org.neet.code.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class hashMap {

    public static void main (String[] args) throws InterruptedException {

        Map<String, Integer> map = new HashMap<>();

        map.put("A", 1);



        Thread t2 = new Thread(() -> map.put("C", 3));
        Thread t1 = new Thread(() -> map.put("B", 2));

        t2.start();
        t1.start();

        t2.join();
        t1.join();

        System.out.println(map);


        Map<String, Integer> map1 = new ConcurrentHashMap<>();

        map1.put("A", 1);

        Thread t3 = new Thread(() -> map1.put("B", 2));
        Thread t4 = new Thread(() -> map1.put("C", 3));

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        System.out.println(map1);
    }
}
