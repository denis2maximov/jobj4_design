package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }
    @Test
    void whenEmptyLines() {
        String path = "./data/pair_with_comment.properties_and_empty_lines";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev=1");
    }
    @Test
    void whenPatternViolationKey()  {
        String path = "./data/pair_with_pattern_violation";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("doesn't match the template!!");

    }

    @Test
    void whenPatternViolationVolue()  {
        String path = "./data/pair_with_pattern_violation_volume";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("doesn't match the template!!");

    }
}