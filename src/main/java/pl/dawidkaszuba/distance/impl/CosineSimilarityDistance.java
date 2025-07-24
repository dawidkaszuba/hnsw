package pl.dawidkaszuba.distance.impl;

import pl.dawidkaszuba.distance.DistanceFunction;

public class CosineSimilarityDistance implements DistanceFunction {

    @Override
    public double compute(double[] v1, double[] v2) {

        if (v1.length != v2.length) {
            throw new IllegalArgumentException("Vector lengths must match");
        }

        double dotProd = 0;

        for (int i = 0; i < v1.length; i++) {
            dotProd += v1[i] * v2[i];
        }

        double normA = 0;
        double normB = 0;


        for (int i = 0; i < v1.length; i++) {
            normA += Math.pow(v1[i], 2);
            normB += Math.pow(v2[i], 2);
        }

        if (normA < 1e-12 || normB < 1e-12) {
            throw new IllegalArgumentException("IllegalArgumentException: Cannot compute cosine distance for zero vector");
        }

        return 1.0 - dotProd / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
