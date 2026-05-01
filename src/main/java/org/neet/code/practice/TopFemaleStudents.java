package org.neet.code.practice;

import java.security.Provider;
import java.util.*;

import java.util.stream.Collectors;


class Student {

    private String name;

    private String gender;

    private double score;


    public Student(String name, String gender, double score) {

        this.name = name;

        this.gender = gender;

        this.score = score;

    }


    public String getName() {

        return name;

    }


    public String getGender() {

        return gender;

    }


    public double getScore() {

        return score;

    }


    @Override

    public String toString() {

        return "Student{name='" + name + "', gender='" + gender + "', score=" + score + "}";

    }

}


public class TopFemaleStudents {

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(

                new Student("Sita", "Female", 92.5),

                new Student("Raavan", "Male", 85.0),

                new Student("Anushka", "Female", 88.5),

                new Student("Siri", "Female", 95.0),

                new Student("Tharu", "Female", 78.0),

                new Student("Virat", "Male", 99.0),

                new Student("Grace", "Female", 91.0)

        );


        // Finding the top 3 female students

        List<String> top3Females = students.stream().filter(s->(s.getGender().equals("Female"))).
                sorted(Comparator.comparingDouble(Student::getScore).reversed()).limit(3)
                .map(Student::getName)
                        .toList();




        System.out.println("Top 3 Female Students:");

        top3Females.forEach(System.out::println);

    }

}