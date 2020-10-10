package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    private final MathFunction sqr=new SqrFunction();
    @Test
    public void testApply() {
        assertEquals(sqr.apply(5),25,0.00001);
        assertEquals(sqr.apply(9),81,0.00001);
        assertEquals(sqr.apply(7),49,0.00001);
    }
}