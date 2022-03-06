package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }

        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        final Node<T> f = head;
        if (f == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
        f.next = null;
        T value = f.value;
        f.value = null;
        return value;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }


    public boolean revert() {
        Node<T> previous = null;
        Node<T> current = head;
        boolean result = false;

        if (head != null && head.next != null) {
            while (current != null) {
                Node<T> next = head.next;
                current.next = previous;
                previous = current;
                head = current;
                current = next;
            }
            result = true;
        }
        return result;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                   if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    T value = node.value;
                    node = node.next;
                    return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}