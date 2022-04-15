package com.example.cursul1_advanced;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main_java_8 {
    public static void main(String[] args) {
        /*Optional<Integer> possible = Optional.empty();

        possible.ifPresent((value) -> System.out.println(value));

        int value = possible.orElse(5);

        System.out.println(value);

        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9);
*/
        /* System.out.println(Arrays.toString(stream.filter(x -> x % 2 == 0).toArray()));*/
/*
        stream
                .filter((x) -> x > 5)
                .map((x) -> x * 2)
                .forEach(System.out::println);*/
        /*List<Integer> list = stream.filter((x) -> x > 5)
                                    .map((x) -> x * 2)
                                    .collect(Collectors.toList());*/
/*
        System.out.println(stream.findFirst().get());
        System.out.println(stream.findFirst().get());
*/

        Stream<User> stream = Stream.generate(() -> User.createUser()).limit(3);
        /*List<User> users = stream
                .filter(x -> x.getAge() > 18)
                .collect(Collectors.toList());*/

        ArrayList<User> users = new ArrayList<>();
        users.add(User.createUser());
        users.add(User.createUser());
        users.add(User.createUser());

        System.out.println(users);

        Stream<User> stream1 = users.stream();
        /*List<Object> userList = stream1
                .filter(x -> x.getAge() > 5)
                .map((x) -> {
                    x.setAge(x.getAge() * 2);
                    return x;
                }).collect(Collectors.toList());
         */

        //System.out.println(stream1.reduce((first, second) -> second).get());

        //System.out.println(stream1.skip(users.size() - 1).findAny().get());

        List<Produce> products = new ArrayList<>();

        products.stream().map(Produce::getPrice).reduce(Integer::sum);
        
        System.out.println(stream1.sorted((o1, o2) -> o1.getAge() - o2.getAge()).findFirst().get());

    }
}
