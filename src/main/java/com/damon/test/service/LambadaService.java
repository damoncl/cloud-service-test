package com.damon.test.service;

import java.util.*;
import java.util.stream.Collectors;

public class LambadaService {

    private static List<User> createUser(int num, Random random) {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            User user = new User();
            user.setName("name" + i);
            user.setAge(random.nextInt(num));
            users.add(user);
        }
        return users;
    }



    public static void main(String[] args) {
        Random random = new Random();
        List<User> users = createUser(200000, random);
//        System.out.println("before begin--");
//        users.forEach(user -> {
//            System.out.print(user.getAge() + ",");
//        });
//        System.out.println();
        //lambada排序
//        lambadaSort(users);
        //quick排序
        collSort(users);


    }

    public static void lambadaSort(List<User> users) {
        long startTime = System.currentTimeMillis();
        List<User> sortUsers = users.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
        System.out.printf("lambada time: %d \n", (System.currentTimeMillis() - startTime));
//        sortUsers.forEach(user -> {
//            System.out.print(user.getAge() + ",");
//        });
    }

    public static void collSort(List<User> users) {
        long startTime = System.currentTimeMillis();
        Collections.sort(users);
        System.out.printf("coll time: %d \n", (System.currentTimeMillis() - startTime));
//        users.forEach(user -> {
//            System.out.print(user.getAge() + ",");
//        });
    }



    public static void forSort(List<User> users) {
        long startTime = System.currentTimeMillis();
        quickSort(0, users.size() - 1, users);
        System.out.printf("quick time: %d \n", (System.currentTimeMillis() - startTime));
        users.forEach(user -> {
            System.out.print(user.getAge() + ",");
        });
    }

    public static void quickSort(int start, int end, List<User> users) {
        if (start >= end) {
            return;
        }
        int first = start;
        int last = end;
        User temp = users.get(start);
        User userTemp;
        while (first < last) {
            while (first < last && users.get(last).compareTo(temp) >= 0) {
                last--;
            }
            while (first < last && users.get(first).compareTo(temp) <= 0) {
                first++;
            }
            if (first < last && users.get(first).compareTo(users.get(last)) > 0) {
                userTemp = users.get(first);
                users.set(first, users.get(last));
                users.set(last, userTemp);
            }
        }
        users.set(start, users.get(first));
        users.set(first, temp);
        quickSort(0, first - 1, users);
        quickSort(first + 1, end, users);
    }

    static class User implements Comparable<User>{
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public int compareTo(User o) {
            return this.getAge().compareTo(o.getAge());
        }
    }


}
