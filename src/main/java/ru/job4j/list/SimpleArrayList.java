package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        arrayIncrease();
        container[size] = value;
        size++;
    }

    private void arrayIncrease() {
        if (size == container.length || container.length == 0) {
            container = Arrays.copyOf(container, container.length * 2  + 1);
            modCount++;
        }
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        modCount++;
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T removeT = get(index);
        modCount++;
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - 1 - index
        );

        if (container.length - 1 == size) {
            container[container.length - 1] = null;
            modCount++;
        }
            size--;
        return removeT;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

           private final int expectedModCount = modCount;
           private int indx;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            return indx < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[indx++];
            }
        };
    }
}
