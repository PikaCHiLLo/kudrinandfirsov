package ru.ssau.tk.kaf.kudrinandfirsov.functions;

import org.testng.annotations.Test;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.ArrayIsNotSortedException;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.DifferentLengthOfArraysException;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.InterpolationException;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    public LinkedListTabulatedFunction linkedListTabulatedFunction1() {
        final double[] xValues = new double[]{-2, -1, 0, 1, 2};
        final double[] yValues = new double[]{-4, -2, 0, 2, 4};
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    public LinkedListTabulatedFunction linkedListTabulatedFunction3() {
        final CompositeFunction compositeFunction = new CompositeFunction(new SqrFunction(), new TenthFunction());
        return new LinkedListTabulatedFunction(compositeFunction, -3, 3, 7);
    }

    @Test
    public void testCount() {
        assertEquals(linkedListTabulatedFunction1().getCount(), 5, 0.00001);
        assertEquals(linkedListTabulatedFunction3().getCount(), 7, 0.00001);
    }

    @Test
    public void testGetX() {
        assertEquals(linkedListTabulatedFunction1().getX(0), -2, 0.00001);
        assertEquals(linkedListTabulatedFunction1().getX(4), 2, 0.00001);
        assertEquals(linkedListTabulatedFunction3().getX(3), 0, 0.00001);
    }

    @Test
    public void testGetY() {
        assertEquals(linkedListTabulatedFunction1().getY(0), -4, 0.00001);
        assertEquals(linkedListTabulatedFunction1().getY(4), 4, 0.00001);
        assertEquals(linkedListTabulatedFunction3().getY(4), 0.1, 0.00001);
    }

    @Test
    public void testSetY() {
        final double[] xValues = new double[]{-2, -1, 0, 1, 2};
        final double[] yValues = new double[]{4, 1, 0, 1, 4};
        LinkedListTabulatedFunction node1 = new LinkedListTabulatedFunction(xValues, yValues);
        final CompositeFunction compositeFunction = new CompositeFunction(new SqrFunction(), new TenthFunction());
        LinkedListTabulatedFunction node3 = new LinkedListTabulatedFunction(compositeFunction, -3, 3, 7);
        node1.setY(0, 16);
        node3.setY(3, 16);
        assertEquals(node1.getY(0), 16, 0.00001);
        assertEquals(node3.getY(3), 16, 0.00001);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(linkedListTabulatedFunction1().indexOfX(-1), 1, 0.00001);
        assertEquals(linkedListTabulatedFunction1().indexOfX(10), -1, 0.00001);
        assertEquals(linkedListTabulatedFunction3().indexOfX(-1), 2, 0.00001);
        assertEquals(linkedListTabulatedFunction3().indexOfX(-5), -1, 0.00001);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(linkedListTabulatedFunction1().indexOfY(-4), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction3().indexOfY(100), -1, 0.00001);
    }

    @Test
    public void testLeftBound() {
        assertEquals(linkedListTabulatedFunction1().leftBound(), -2, 0.00001);
        assertEquals(linkedListTabulatedFunction3().leftBound(), -3, 0.00001);
    }

    @Test
    public void testRightBound() {
        assertEquals(linkedListTabulatedFunction1().rightBound(), 2, 0.00001);
        assertEquals(linkedListTabulatedFunction3().rightBound(), 3, 0.00001);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(linkedListTabulatedFunction1().floorIndexOfX(-1), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction3().floorIndexOfX(1.5), 4, 0.00001);
        assertEquals(linkedListTabulatedFunction3().floorIndexOfX(-2.5), 0, 0.00001);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(linkedListTabulatedFunction1().extrapolateLeft(-3), -6, 0.00001);
        assertEquals(linkedListTabulatedFunction1().extrapolateLeft(-5), -10, 0.00001);
        assertEquals(linkedListTabulatedFunction3().extrapolateLeft(-5), 1.9, 0.00001);
        assertEquals(linkedListTabulatedFunction3().extrapolateLeft(-10), 4.4, 0.00001);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(linkedListTabulatedFunction1().extrapolateRight(3), 6, 0.00001);
        assertEquals(linkedListTabulatedFunction1().extrapolateRight(5), 10, 0.00001);
        assertEquals(linkedListTabulatedFunction3().extrapolateRight(10), 4.4, 0.00001);
        assertEquals(linkedListTabulatedFunction3().extrapolateRight(5), 1.9, 0.00001);
        assertEquals(linkedListTabulatedFunction3().extrapolateRight(10), 4.4, 0.00001);
    }

    @Test
    public void testInterpolate() {
        assertEquals(linkedListTabulatedFunction1().interpolate(0.5, 2), 1, 0.00001);
        assertEquals(linkedListTabulatedFunction3().interpolate(0.5, 3), 0.05, 0.00001);
        assertEquals(linkedListTabulatedFunction3().interpolate(2.2, 5), 0.5, 0.00001);
    }

    @Test
    public void testApply() {
        assertEquals(linkedListTabulatedFunction1().apply(-3), -6, 0.00001);
        assertEquals(linkedListTabulatedFunction1().apply(0), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction1().apply(3), 6, 0.00001);
        assertEquals(linkedListTabulatedFunction1().apply(0.5), 1, 0.00001);
        assertEquals(linkedListTabulatedFunction3().apply(-4), 1.4, 0.00001);
        assertEquals(linkedListTabulatedFunction3().apply(4), 1.4, 0.00001);
        assertEquals(linkedListTabulatedFunction3().apply(0), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction3().apply(0.5), 0.05, 0.00001);
    }

    @Test
    public void testInterpolationException() {
        assertThrows(InterpolationException.class, () -> {
            linkedListTabulatedFunction1().interpolate(-2, 1);
            linkedListTabulatedFunction3().interpolate(-1, 5);
            linkedListTabulatedFunction3().interpolate(-2, 3);
        });
    }

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4});
            new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4, 5});
            new LinkedListTabulatedFunction(new double[]{1}, new double[]{});
        });
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            new LinkedListTabulatedFunction(new double[]{5, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
            new LinkedListTabulatedFunction(new double[]{1, 5, 3, 4}, new double[]{1, 2, 3, 4});
            new LinkedListTabulatedFunction(new double[]{3, 2}, new double[]{1, 2});
        });
    }
}