package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);

    @Override
    public boolean add(T value) {
        boolean out = contains(value);
         if (!out) {
             set.add(value);
         }
        return !out;

    }

    @Override
    public boolean contains(T value) {
        boolean out = false;
        for (T x : set) {
            if (Objects.equals(value, x)) {
                out = true;
                break;
            }
        }
            return out;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
