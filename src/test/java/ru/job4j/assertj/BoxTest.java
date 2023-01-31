package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

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
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void thisIsNotExist() {
        Box box = new Box(3, 10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void thisGetAreaSphere() {
        Box box = new Box(0, 10);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(1256.64d, withPrecision(0.006d))
                .isCloseTo(1256.64d, withPrecision(0.01d))
                .isCloseTo(1256.64d, Percentage.withPercentage(1.0d))
                .isGreaterThan(19.0d)
                .isLessThan(2022.26d);
    }
    @Test
    void thisGetAreaCube() {
        Box box = new Box(4, 10);
        System.out.println(box.getArea());
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(173.21d, withPrecision(0.006d))
                .isCloseTo(173.21d, withPrecision(0.01d))
                .isCloseTo(173.21d, Percentage.withPercentage(1.0d))
                .isGreaterThan(19.0d)
                .isLessThan(175.00d);
    }

    @Test
    void thisGetAreaTetrahedron() {
        Box box = new Box(8, 10);
        System.out.println(box.getArea());
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(600.00d, withPrecision(0.006d))
                .isCloseTo(600.00d, withPrecision(0.01d))
                .isCloseTo(600.00d, Percentage.withPercentage(1.0d))
                .isGreaterThan(551.2d)
                .isLessThan(601.21d);
    }


}