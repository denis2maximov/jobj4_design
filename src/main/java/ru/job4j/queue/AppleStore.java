package ru.job4j.queue;

import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        String rsl = null;
        if (queue.size() != 0) {
            for (int i = 0; i < count; i++) {
                if (queue.size() != 0) {
                    rsl = queue.poll().name();
                }
            }
        }
        return rsl;
    }

    public String getFirstUpsetCustomer() {
        String rsl = null;
        if (count + 1 < queue.size() &&  queue.size() != 0) {
            for (int i = 0; i < count + 1; i++) {
                rsl = queue.poll().name();
            }
        }
        return rsl;
    }
}