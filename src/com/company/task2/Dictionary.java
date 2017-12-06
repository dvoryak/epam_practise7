package com.company.task2;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

    private HashMap<String,String> dictionary;

    public Dictionary() {
    }

    public String translate(String word) {
        if(dictionary != null && dictionary.get(word) != null) {
            return dictionary.get(word);
        }
        throw new CantTranslateWordException();
    }

    public Dictionary(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    public Map<String, String> getDictionary() {
        return Collections.unmodifiableMap(dictionary);
    }

    public void setDictionary(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
    }
}
