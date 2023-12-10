package com.zy.core_java.map;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

/**
 * guava中Multimap、HashMultimap使用详解
 */
public class MultimapTest {

    @Test
    public void testHashMultimap() {
        Multimap<Integer, Integer> map = HashMultimap.create();
        map.put(1, 2);
        map.put(1, 3);
        map.put(1, 2);
        map.put(2, 3);
        map.put(4, 2);
        map.put(4, 3);
        map.put(4, 2);
        map.put(4, 3);
        System.out.println(map.toString());
    }
}
