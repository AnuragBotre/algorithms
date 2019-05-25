package com.trendcore.offheap.cache;

import com.graphaware.offheap.map.Key;
import com.graphaware.offheap.map.OffHeapMap;
import com.graphaware.offheap.map.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class OffHeapMapAdapter<V>{

    private final OffHeapMap offHeapMap;

    public OffHeapMapAdapter(OffHeapMap o) {
        this.offHeapMap = o;
    }


    public int size() {
        return offHeapMap.size();
    }


    public boolean isEmpty() {
        return offHeapMap.isEmpty();
    }


    public boolean containsKey(String key) {
        return offHeapMap.containsKey(key);
    }


    public boolean containsValue(Object value) {
        return offHeapMap.containsValue(value);
    }


    public Object get(Object key) {
        return offHeapMap.get(key);
    }


    public Object put(String key, Object value) {
        return offHeapMap.put(new Key(key),new Value(value));
    }


    public Object remove(Object key) {
        return offHeapMap.remove(key);
    }


    public void putAll(@NotNull Map m) {
        offHeapMap.putAll(m);
    }


    public void clear() {
        offHeapMap.clear();
    }

    @NotNull

    public Set keySet() {
        return offHeapMap.keySet();
    }

    @NotNull

    public Collection values() {
        return offHeapMap.values();
    }

    @NotNull
    public Set entrySet() {
        return offHeapMap.entrySet();
    }
}
