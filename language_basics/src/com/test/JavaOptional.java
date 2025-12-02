package com.test;

import java.util.Optional;

public class JavaOptional {
    public static void main(String arg[]) {
        Optional<String> optional1 = Optional.of("Hello");       // must be non-null
        Optional<String> optional2 = Optional.ofNullable(null);  // can be null
        Optional<String> empty = Optional.empty();

        //Optional<String> nameOpt = Optional.ofNullable(null);
        //Optional<String> nameOpt = Optional.ofNullable("Hello");
        Optional<String> nameOpt = Optional.empty();

        // 1. ifPresent  -- if value present do the action othwise do nothing.
        nameOpt.ifPresent(name -> System.out.println("Name: " + name));

        // 2. orElse
        String name = nameOpt.orElse("Unknown"); // returns default if empty
        System.out.println("name :" + name);

        // 3. map
        int length = nameOpt.map(String::length).orElse(0);
        System.out.println("length :" + length);

        // 4. orElseThrow
        String mustExist = nameOpt.orElseThrow(() -> new RuntimeException("Name missing"));
    }

}
