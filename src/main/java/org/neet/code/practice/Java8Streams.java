package org.neet.code.practice;

import java.util.*;
import java.util.stream.Collectors;

public class Java8Streams {


    public static void main(String[] args){

        List<Integer> list = new ArrayList<>();

        list.add(2);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(3);
        list.add(1);

        System.out.println(list.stream().
                collect(Collectors.groupingBy(e->e,Collectors.counting())));

        System.out.println(list.stream().
                collect(Collectors.groupingBy(e->e,Collectors.counting())).
                entrySet().stream().sorted((a,b) -> Long.compare(b.getValue(),a.getValue())).toList());

        System.out.println(list.stream().
                collect(Collectors.groupingBy(e->e,Collectors.counting())).
                entrySet().stream().sorted((a,b) -> Long.compare(b.getValue(),a.getValue()))
                .limit(2).map(Map.Entry::getKey).toList());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Map<Integer,Long> map = list.stream().collect(Collectors.groupingBy(e->e, Collectors.counting()));
        System.out.println("map= "+map);

        for(var mp : map.keySet()){
            pq.offer(mp);
            if(pq.size()>2)
                pq.poll();
        }
        System.out.println("pq= " + pq);


        ///Input: ["a", "bb", "ccc", "dd"]

        List<String> list2 = new ArrayList<>();


        list2.add("a");
        list2.add("cc");
        list2.add("bb");
        list2.add("ccc");
        list2.add("dddd");
        list2.add("eeee");

        System.out.println(list2.stream().
                collect(Collectors.toMap(e->e, String::length)).entrySet().stream()
                .sorted(Comparator.comparingLong(Map.Entry::getValue)).toList());

        System.out.println(list2.stream()
                .collect(Collectors.groupingBy(String::length)));
    }
}
