package com.company.task2;

public class Main {

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary(new EnglishRussianMap());
        System.out.println(dictionary.translate("Hello"));
    }
}
