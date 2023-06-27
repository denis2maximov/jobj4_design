package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.*;


class ResultFileTest {

    @Test
    public void when2on2() throws IOException {
        ResultFile.multiple(2);
        int[][] ex = new int[2][2];
        try (FileInputStream input = new FileInputStream("data/dataresult.txt")) {
            for (int i = 0; i < ex.length; i++) {
                for (int j = 0; j < ex.length; j++) {
                    ex[i][j] = input.read();
                }
            }
        }
        int[][] expect = {
                {1, 2},
                {2, 4}
        };
        assertThat(expect).isEqualTo(ex);
    }

    @Test
    public void when3on3() throws IOException {
        ResultFile.multiple(3);
        int[][] ex = new int[3][3];
        try (FileInputStream input = new FileInputStream("data/dataresult.txt")) {
            for (int i = 0; i < ex.length; i++) {
                for (int j = 0; j < ex.length; j++) {
                    ex[i][j] = input.read();
                }
            }
        }
        int[][] expect = {
                {1, 2, 3},
                {2, 4, 6},
                {3, 6, 9}
        };
        assertThat(expect).isEqualTo(ex);
    }
}