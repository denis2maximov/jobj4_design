package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void toArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test    void toList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
          assertThat(list).hasSize(5)
                .contains("five")
                .anySatisfy(e -> {
                    assertThat("first").isIn(list);
                    assertThat(list).asString().containsPattern(".ou.");
                    assertThat(list).last().isNotNull()
                            .isEqualTo("five");
                    })
                  .allSatisfy(e -> assertThat(e).isInstanceOf(String.class));
    }


    @Test
    void toSet() {
    }

    @Test
    void toMap() {
    }
}