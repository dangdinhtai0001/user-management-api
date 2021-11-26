package com.phoenix.common.text.similarity.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Unit tests for {@link CosineSimilarity}.
 */
public class CosineDistanceTest {

    /**
     * Cosine distance under test.
     */
    private static CosineDistance cosineDistance;

    /**
     * Creates the cosine distance object used throughout the tests.
     */
    @BeforeAll
    public static void setUp() {
        cosineDistance = new CosineDistance();
    }

    /**
     * Tests the cosine distance with several inputs.
     */
    @Test
    public void testCosineDistance() {
        Assertions.assertEquals(roundValue(cosineDistance.apply("the house", "da house")), Double.valueOf(0.5d));
        Assertions.assertEquals(roundValue(cosineDistance.apply("AB", "AB")), Double.valueOf(0.0d));
        Assertions.assertEquals(roundValue(cosineDistance.apply("AB", "BA")), Double.valueOf(1.0d));
        Assertions.assertEquals(roundValue(cosineDistance.apply(
                        "the boy was from tamana shi, kumamoto ken, and the girl was from rio de janeiro, rio",
                        "the boy was from tamana shi, kumamoto, and the boy was from rio de janeiro, rio de janeiro")),
                Double.valueOf(0.5d));
    }
    // --- Utility methods

    /**
     * Rounds up a value.
     *
     * @param value a value
     * @return rounded up value
     */
    private Double roundValue(final Double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
