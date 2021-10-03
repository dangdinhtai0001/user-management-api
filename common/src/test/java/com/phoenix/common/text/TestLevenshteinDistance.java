/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.text;

import org.junit.Test;

public class TestLevenshteinDistance {
    @Test
    public void testLevenshteinDistance() {
        String a = "testLevenshteinDistance";
        String b = "testLeve";

        System.out.print("Levenshtein distance between " + a + " and " + b + " is: ");
//        System.out.println(LevenshteinDistance.calculateDistance(a, b));
        System.out.println(LevenshteinDistance.calculate(a, b));
    }
}
