package com.example.demo.hashMap;

import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        if (map.isEmpty()) {
            System.out.println("map is empty");
        }
        map.put(11, "小明");
        map.put(12, "小李");
        map.put(13, "小白");
        map.forEach((k,v) -> System.out.println("key:"+k+" value:"+v));

    }
}
