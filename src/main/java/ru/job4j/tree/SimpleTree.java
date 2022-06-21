package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        Predicate<Node<E>> predBinary = s -> s.children.size() > 2;
        return findByPredicate(predBinary).isEmpty();
    }


    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
            }
            data.addAll(el.children);
            }
        return rsl;
    }


    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> out = findBy(parent);
        if (out.isPresent() && findBy(child).isEmpty()) {
            rsl  = out.get().children.add(new Node<>(child));
         }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predFindBy = s -> s.value.equals(value);
        return findByPredicate(predFindBy);
    }
}
