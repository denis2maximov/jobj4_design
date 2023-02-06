package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("collection contains no data");
    }

    @Test
    void checkArgumentNameEmpty() {
        NameLoad nameLoad = new NameLoad();
           assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");

    }

    @Test
    void checkValidation() {
        NameLoad nameLoad = new NameLoad();
        String name = "key:value";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining(String.format("this name: %s does not contain the symbol \"=\"", name));
    }

    @Test
    void checkValidationKey() {
        NameLoad nameLoad = new NameLoad();
        String name = "=value";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining(String.format("this name: %s does not contain a key", name));
    }

    @Test
    void checkValidationVolume() {
        NameLoad nameLoad = new NameLoad();
        String name = "key=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining(String.format("this name: %s does not contain a value", name));
    }
}