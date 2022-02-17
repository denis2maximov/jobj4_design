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
        if (size == container.length - 1) {
            container = Arrays.copyOf(container, container.length * 2);
            modCount++;
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = container[index];
        Objects.checkIndex(index, size);
        modCount++;
        container[index] = newValue;
        return oldValue;
    }

    /**
     *         System.arraycopy(
     *                 container, // откуда копируем
     *                 index + 1, // начиная с какого индекса
     *                 container, // куда копируем
     *                 index, // начиная с какого индекса
     *                 container.length - 1 - index // сколько элементов копируем
     *         );
     *         // на последнее место ставим null, чтобы не было учечки памяти (если удаляем последний элемент)
     *          container[container.length - 1] = null;
     */

    @Override
    public T remove(int index) {
        T removeT = container[index];
        Objects.checkIndex(index, size);
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
        modCount++;
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
