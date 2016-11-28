package ru.sbt.javaschool;

/**
 * Created by TMW on 23.08.2016.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MultimapList <K,V>  implements Multimap <K,V> {
    private Map <K,Collection <V>> innerMap;

    @Override
    public Collection <V> get(K key) {
        return innerMap.get(key);
    }
    @Override
    public void put(K key, V value) {
        Collection <V> innerList = innerMap.get(key);
        if (innerList == null) {
            innerList = new ArrayList<>();
            innerMap.put(key,innerList);
        }
        innerList.add(value);
    }
    public MultimapList(){
    innerMap = new HashMap<>();
    }
}
