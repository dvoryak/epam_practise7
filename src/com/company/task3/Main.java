package com.company.task3;

public class Main {

    public static void main(String[] args) {
	// write your code here
        IRedBlackTreeMap<Integer,Integer> map = new MyRedBlackTreeMap<>();


        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        map.put(5,5);

        ((MyRedBlackTreeMap)map).printTree();

    }
}
