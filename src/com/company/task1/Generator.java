package com.company.task1;

import java.util.*;

public class Generator {

    public static List<Integer> generateList(int count, int from, int to) {
        ArrayList<Integer> out = new ArrayList<>(count);
        generate(out,count,from,to);
        return out;
    }

    public static Set<Integer> generateSet(int count, int from, int to) {
        Set<Integer> out = new HashSet<>(count);
        generate(out,count,from,to);
        return out;
    }

    private static void generate(Collection<Integer> collection, int count, int from, int to) {
        for(int i = 0; i < count; i++) {
            collection.add((new Random().nextInt(to - from) + from));
        }
    }
}
