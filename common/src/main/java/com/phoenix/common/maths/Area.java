/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

package com.phoenix.common.maths;

public class Area {
    /**
     * Calculate the surface area of a cube.
     *
     * @param sideLength side length of cube
     * @return surface area of given cube
     */
    public static double surfaceAreaCube(double sideLength) {
        return 6 * sideLength * sideLength;
    }

    /**
     * Calculate the surface area of a sphere.
     *
     * @param radius radius of sphere
     * @return surface area of given sphere
     * <p>
     * - https://www.quora.com/Why-is-the-formula-for-surface-area-of-a-sphere-4-pi-r-2-and-why-is-volume-frac-4-3-pi-r-3
     */
    public static double surfaceAreaSphere(double radius) {
        return 4 * Math.PI * radius * radius;
    }

    /**
     * Calculate the area of a rectangle
     *
     * @param length length of rectangle
     * @param width  width of rectangle
     * @return area of given rectangle
     */
    public static double surfaceAreaRectangle(double length, double width) {
        return length * width;
    }

    /**
     * Calculate the area of a square
     *
     * @param sideLength side length of square
     * @return area of given square
     */
    public static double surfaceAreaSquare(double sideLength) {
        return sideLength * sideLength;
    }

    /**
     * Calculate the area of a triangle
     *
     * @param base   base of triangle
     * @param height height of triangle
     * @return area of given triangle
     */
    public static double surfaceAreaTriangle(double base, double height) {
        return base * height / 2;
    }

    /**
     * Calculate the area of a parallelogram
     *
     * @param base   base of parallelogram
     * @param height height of parallelogram
     * @return area of given parallelogram
     */
    public static double surfaceAreaParallelogram(double base, double height) {
        return base * height;
    }

    /**
     * Calculate the area of a trapezium
     *
     * @param base1  upper base of trapezium
     * @param base2  bottom base of trapezium
     * @param height height of trapezium
     * @return area of given trapezium
     */
    public static double surfaceAreaTrapezium(double base1, double base2, double height) {
        return (base1 + base2) * height / 2;
    }

    /**
     * Calculate the area of a circle
     *
     * @param radius radius of circle
     * @return area of given circle
     */
    public static double surfaceAreaCircle(double radius) {
        return Math.PI * radius * radius;
    }
}
