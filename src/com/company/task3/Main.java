package com.company.task3;

public class Main {

    public static void main(String[] args) {
	// write your code here
        IRedBlackTreeMap<Integer,Integer> map = new MyRedBlackTreeMap<>();
        for(int i = 0; i < 1000; i++) {
            map.put(i,i);
        }

        System.out.println(map.get(1000));

    }
}
