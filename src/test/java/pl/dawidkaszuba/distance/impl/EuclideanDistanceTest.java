package pl.dawidkaszuba.distance.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanDistanceTest {

    private final EuclideanDistance distance = new EuclideanDistance();

    @Test
    void testSameVectorShouldReturnZero() {
        double[] vector = {1.0, 2.0, 3.0};
        double result = distance.compute(vector, vector);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void testSimpleCaseWithDistanceFive() {
        double[] v1 = {0.0, 0.0};
        double[] v2 = {3.0, 4.0};
        double result = distance.compute(v1, v2);
        assertEquals(5.0, result, 1e-9);
    }

    @Test
    void testOneDimensionalVectors() {
        double[] v1 = {2.0};
        double[] v2 = {5.0};
        double result = distance.compute(v1, v2);
        assertEquals(3.0, result, 1e-9);
    }

    @Test
    void testVectorsWithNegativeValues() {
        double[] v1 = {-1.0, -2.0};
        double[] v2 = {1.0, 2.0};
        double result = distance.compute(v1, v2);
        assertEquals(Math.sqrt(20), result, 1e-9);
    }

    @Test
    void testDifferentLengthVectorsShouldThrowException() {
        double[] v1 = {1.0, 2.0};
        double[] v2 = {1.0};

        assertThrows(IllegalArgumentException.class, () -> distance.compute(v1, v2));
    }


}