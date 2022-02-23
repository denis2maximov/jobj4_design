package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.util.Objects.checkIndex;

public class SimpleLinkedList<E> implements List<E> {

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;


        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index, size);
        for (int j = 0; j < index; j++) {
            first = first.next;
        }
        return first.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> nodeFirst = first;
            private Node<E> nodeNext;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return nodeNext != last;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                nodeNext = nodeFirst;
                nodeFirst = nodeNext.next;
                return nodeNext.item;
                }
        };
    }
}

