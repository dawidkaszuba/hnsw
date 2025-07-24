package pl.dawidkaszuba.distance.impl;

import pl.dawidkaszuba.distance.DistanceFunction;

public class EuclideanDistance implements DistanceFunction {

    @Override
    public double compute(double[] v1, double[] v2) {

        if (v1.length != v2.length) {
            throw new IllegalArgumentException("Vector lengths must match");
        }

        double acc = 0;

        for (int i = 0; i < v1.length; i++) {
            double powDiff = Math.pow(v1[i] - v2[i], 2);
            acc += powDiff;
        }
        return Math.sqrt(acc);
    }
}
