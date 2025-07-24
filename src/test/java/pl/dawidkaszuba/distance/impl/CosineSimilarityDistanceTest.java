package pl.dawidkaszuba.distance.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CosineSimilarityDistanceTest {

    private final CosineSimilarityDistance distance = new CosineSimilarityDistance();

    @Test
    void testSameVectorShouldReturnZero() {
        double[] vector = {1.0, 2.0, 3.0};
        double result = distance.compute(vector, vector);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testOrthogonalVectorsShouldReturnOne() {
        double[] v1 = {1.0, 0.0};
        double[] v2 = {0.0, 1.0};
        double result = distance.compute(v1, v2);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testOppositeVectorsShouldReturnTwo() {
        double[] v1 = {1.0, 0.0};
        double[] v2 = {-1.0, 0.0};
        double result = distance.compute(v1, v2);
        assertEquals(2.0, result, 1e-9);
    }

    @Test
    void testRandomVectors() {
        double[] v1 = {1.0, 1.0};
        double[] v2 = {2.0, 2.0};
        double result = distance.compute(v1, v2);
        assertEquals(0, result, 1e-9);
    }

    @Test
    void testWithNegativeValues() {
        double[] v1 = {-1.0, -1.0};
        double[] v2 = {1.0, 1.0};
        double result = distance.compute(v1, v2);
        assertEquals(2, result, 1e-9);
    }

    @Test
    void testDifferentLengthVectorsShouldThrowException() {
        double[] v1 = {1.0, 2.0};
        double[] v2 = {1.0};

        assertThrows(IllegalArgumentException.class, () -> distance.compute(v1, v2));
    }

    @Test
    void testFirstZeroVectorShouldThrowException() {
        double[] v1 = {0.0, 0.0};
        double[] v2 = {1.0, 0.0};

        assertThrows(IllegalArgumentException.class, () -> distance.compute(v1, v2));
    }

    @Test
    void testSecondZeroVectorShouldThrowException() {
        double[] v1 = {1.0, 2.0};
        double[] v2 = {0.0, 0.0};

        assertThrows(IllegalArgumentException.class, () -> distance.compute(v1, v2));
    }
}