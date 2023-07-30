package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() throws IOException {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }
    @Test
    void whenEmptyLines() throws IOException {
        String path = "./data/pair_with_comment.properties_and_empty_lines";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev=1");
    }
    @Test
    void whenPatternViolation()  {
        String path = "./data/pair_with_pattern_violation";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("doesn't match the template!!");

    }
}