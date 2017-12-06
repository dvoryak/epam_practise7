package com.company.task3;

public interface IRedBlackTreeMap<K extends Comparable<K> ,V> {

    void put(K key,V value);
    V get(K key);
    boolean remove(K key);
    int size();
    boolean containsKey(K key);

}
