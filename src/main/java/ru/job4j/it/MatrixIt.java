package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data[row].length == 0 || column == data[row].length) {
            if (row == data.length - 1) {
                return false;
            }
                row++;
                column = 0;
            }
        return row < data.length && !(data[row].length == 0 && data.length == 1);

    }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
        return data[row][column++];
        }
}

