package ru.job4j.map;

import java.util.*;

public class  SimpleMap<K, V> implements Map<K, V> {

    private static final float loadFactor = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        float f = ((float) count / capacity);
        if (f > loadFactor) {
            System.out.println(f > loadFactor);
            expand();
        }
        int hashCode = hashCode(key);
        int hash = hash(hashCode);
        int index = indexFor(hash);

       if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            result = true;
            count++;
            modCount++;
            }
        return result;
    }

    private int hashCode(K key) {
        return (key == null) ? 0 : key.hashCode();

    }
    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode) ^ (hashCode << 16);
    }

    private int indexFor(int hash) {
        return    (capacity - 1) & hash;
    }

   private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] oldTable = table;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < (capacity / 2)  - 1; i++) {
            if (oldTable[i] != null) {
                int newHash = hashCode(oldTable[i].key);
                int hash = hash(newHash);
                int index = indexFor(hash);
                newTable[index] = oldTable[i];
            }
        }
            table = newTable;
        }

    @Override
    public V get(K key) {
        V volume = null;
        int hashCode = hashCode(key);
        int hash = hash(hashCode);
        int index = indexFor(hash);
        if  (table[index] != null && table[index].key.equals(key)) {
             volume =  table[index].value;
        }
         return volume;
        }

    @Override
    public boolean remove(K key) {
        boolean out = false;
            int hashCode = hashCode(key);
            int hash = hash(hashCode);
            int index = indexFor(hash);
                if (table[index] != null && table[index].key.equals(key)) {
                    table[index] = null;
                    out = true;
                    count--;
                    modCount++;
                }
           return out;
        }

    @Override
    public Iterator<K> iterator() {
        return  new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity - 1 && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return  table[index++].key;
            }
        };
     }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
        public final String toString() {
            return key + " : " + value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}