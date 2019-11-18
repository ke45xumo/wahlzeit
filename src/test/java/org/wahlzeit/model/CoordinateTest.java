package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class CoordinateTest {
    private double falsePhi1 = -1;
    private double falsePhi2 = 360;

    private double falseTheta1 = -1;
    private double falseTheta2 = 181;

    private double falseRadius = -1;

    private double correctPhi = 180;
    private double correctTheta = 90;
    private double correctRadius = 1;

    @Test
    public void testSphericCoordinate()  {
        assertThrows(Exception.class, () -> new SphericCoordinate(falsePhi1,correctTheta,correctRadius));
        assertThrows(Exception.class, () -> new SphericCoordinate(falsePhi2,correctTheta,correctRadius));

        assertThrows(Exception.class, () -> new SphericCoordinate(correctPhi,falseTheta1,correctRadius));
        assertThrows(Exception.class, () -> new SphericCoordinate(correctPhi,falseTheta2,correctRadius));

        assertThrows(Exception.class, () -> new SphericCoordinate(correctPhi,correctTheta,falseRadius));

        try {
            assertNotNull(new SphericCoordinate(correctPhi, correctTheta, correctRadius));
        }catch (Exception ex){
            Assert.fail("Silent mode does not allow exceptions");
        }
    }
    @Test
    public void testAsCartesianCoordinate(){
        try {
            SphericCoordinate sphericCoordinate = new SphericCoordinate(correctPhi, correctTheta, correctRadius);

            assertNotNull(sphericCoordinate.asCartesianCoordinate());


        }catch (Exception ex){
            Assert.fail("Silent mode does not allow exceptions");
        }
    }
}
