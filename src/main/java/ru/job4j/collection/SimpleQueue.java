package ru.job4j.collection;

import ru.job4j.collection.list.SimpleStack;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int sizeIn;
    int sizeOut;

    public T poll() {
        if (sizeOut == 0) {
            while (sizeIn != 0) {
                out.push(in.pop());
                sizeOut++;
                sizeIn--;
            }
        }
        T pop = out.pop();
        sizeOut--;
        return pop;
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}