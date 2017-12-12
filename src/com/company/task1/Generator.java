package com.company.task1;

import java.util.*;

public class Generator {

    public static List<Integer> generateList(int count, int from, int to) {
        checkOf(count, from, to);
        ArrayList<Integer> out = new ArrayList<>(count);
        for(int i = 0; i < count; i++) {
            out.add((new Random().nextInt(to - from) + from));
        }
        return out;
    }

    public static Set<Integer> generateSet(int count, int from, int to) {
        checkOf(count, from, to);
        Set<Integer> out = new HashSet<>(count);
        while(out.size() != count) {
            out.add((new Random().nextInt(to - from) + from));
        }
        return out;
    }

    private static void checkOf(int count, int from, int to) {
        if(count < 0 || to < from) throw new IllegalArgumentException();
    }


}
