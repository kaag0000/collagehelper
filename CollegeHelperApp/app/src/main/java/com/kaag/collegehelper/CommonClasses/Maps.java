package com.kaag.collegehelper.CommonClasses;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Maps {
    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public static <K, V> Map<K, V> newLinkedHashMap() {
        return new LinkedHashMap<K, V>();
    }

    public static <K, V> List<String> getSimpleFormatForDebugging(Map<K, V> map) {
        List<String> list = Lists.newArrayList();
        for (K key : map.keySet()) {
            list.add(key + ":" + map.get(key));
        }
        return list;
    }
}