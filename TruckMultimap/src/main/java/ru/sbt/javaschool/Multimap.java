package ru.sbt.javaschool;
/**
 * Created by TMW on 23.08.2016.
 */
import java.util.Collection;

public interface Multimap<K, V> {
    Collection <V> get(K key);
    void put(K key, V value);

}
