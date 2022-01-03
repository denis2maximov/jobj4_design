package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AppZeroTest {

    @Test
    public void zero() {
            String s = "Hello World";
            String y = AppZero.zero();
        assertEquals(s, y);
        }
    }
