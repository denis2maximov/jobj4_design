package ru.job4j.map;

import java.util.*;

public class  SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    final float loadFactor;

    private int capacity = 8;
    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public SimpleMap() {
        this.loadFactor = LOAD_FACTOR;
    }

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        float f = ((float) count / capacity);
        if (f >= 0.75f) {
            expand();
        }
        int hashCode = hashCode(key);
        int hash = hash(hashCode);
        int index = indexFor(hash);
        count++;
        modCount++;

       if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            result = true;
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
        for (int i = 0; i < capacity / 2; i++) {
            if (oldTable[i] != null) {
                int newHash = hashCode(oldTable[i].key);
                int hash = hash(newHash);
                int index = indexFor(hash);
                MapEntry newEntry = new MapEntry(oldTable[i].key, oldTable[i].value);
                newTable[index] = newEntry;
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
       if  (table[index].key.equals(key)) {
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
        try {
            if (table[index].key.equals(key)) {
                table[index] = null;
                out = true;
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }
            count--;
            ++modCount;
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
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) table[index++];
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
