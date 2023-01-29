package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 7);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 7);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(3, 7);
        String name = box.whatsThis();
        assertThat(name).startsWith("Unknown");
    }
    @Test
    void thisCube() {
        Box box = new Box(8, 4);
        assertThat(box.whatsThis()).isEqualTo("Cube");
    }

    @Test
    void thisTetrahedron() {
        Box box = new Box(4, 4);
        assertThat(box.whatsThis()).isEqualTo("Tetrahedron");
    }

    @Test
    void thisSphere() {
        Box box = new Box(0, 40);
        assertThat(box.whatsThis()).isEqualTo("Sphere");
    }
    @Test
    void thisUnknown() {
        Box box = new Box(9, 1);
        assertThat(box.whatsThis()).isNotNull()
                .startsWith("Unknown")
                .contains("object")
                .isNotEqualTo("Tetrahedron")
                .isNotEqualTo("Cube")
                .isNotEqualTo("Sphere");
        }

    @Test
    void thisVertexSphere() {
        Box box = new Box(0, 10);
        assertThat(box.getNumberOfVertices())
                .isNotNegative()
                .isNotPositive()
                .isLessThan(1)
                .isEqualTo(0);
    }

    @Test
    void thisVertexCube() {
        Box box = new Box(8, 10);
        assertThat(box.getNumberOfVertices())
                .isNotNegative()
                .isLessThan(9)
                .isGreaterThan(7)
                .isEqualTo(8);
    }
    @Test
    void thisVertexTetrahedron() {
        Box box = new Box(4, 10);
        assertThat(box.getNumberOfVertices())
                .isNotNegative()
                .isLessThan(95)
                .isGreaterThan(3)
                .isEqualTo(4);
    }
    @Test
    void thisIsExist() {
        Box box = new Box(8, 10);
        assertThat(box.isExist()).isNotNull()
                .isTrue();
    }

    @Test
    void thisIsNotExist() {
        Box box = new Box(3, 10);
        assertThat(box.isExist()).isNotNull()
                .isFalse();
    }

}