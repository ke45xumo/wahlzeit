package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {
    private double falsePhi1 = -1;
    private double falsePhi2 = 361;

    private double falseTheta1 = -1;
    private double falseTheta2 = 181;

    private double falseRadius = -1;

    private double correctPhi = 180;
    private double correctTheta = 45;
    private double correctRadius = 1;

    private SphericCoordinate correctSphericCoordinate = null;

    private CartesianCoordinate correctCartesianCoordinate = null;
    private CartesianCoordinate correctCartesianCoordinate2 = null;

    @Before
    public void setup() throws Exception{
        correctSphericCoordinate = new SphericCoordinate(correctPhi, correctTheta, correctRadius);

        correctCartesianCoordinate = new CartesianCoordinate(7,4,3);
        correctCartesianCoordinate2 = new CartesianCoordinate(17, 6, 2);
    }
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
            assertNotNull(correctSphericCoordinate.asCartesianCoordinate());
            assertEquals(correctCartesianCoordinate.getClass(), correctSphericCoordinate.asCartesianCoordinate().getClass());

        }catch (Exception ex){
            Assert.fail("Silent mode does not allow exceptions");
        }
    }

    @Test
    public void testAsSphericCoordinate(){
        try {
            assertNotNull(correctCartesianCoordinate.asSphericCoordinate());
            assertEquals(correctSphericCoordinate.getClass(), correctCartesianCoordinate.asSphericCoordinate().getClass());

        }catch (Exception ex){
            Assert.fail("Silent mode does not allow exceptions");
        }
    }

    @Test
    public void testGetCartesianDistance(){
        try {
            double expectedDistance = 10.246951;    // with help https://www.calculatorsoup.com/calculators/geometry-solids/distance-two-points.php

            SphericCoordinate transformedCartasian1 = correctCartesianCoordinate.asSphericCoordinate();
            SphericCoordinate transformedCartasian2 = correctCartesianCoordinate2.asSphericCoordinate();

            // same Cartesian Coordinate
            assertEquals(0,correctCartesianCoordinate.getCartesianDistance(correctCartesianCoordinate),0);

            // Distance to other cartesianCoordinate
            assertEquals(expectedDistance,correctCartesianCoordinate.getCartesianDistance(correctCartesianCoordinate2),0.0001);

            // Calculation with Cartasian and Spheric Coordinate
            assertEquals(expectedDistance, transformedCartasian1.getCartesianDistance(correctCartesianCoordinate2),0.005);
            assertEquals(expectedDistance, correctCartesianCoordinate.getCartesianDistance(transformedCartasian2),0.005);

            // Same Coordinate (Comparing Cartasian and Spheric Coordinate
            assertEquals(0, transformedCartasian1.getCartesianDistance(correctCartesianCoordinate),0.005);

        }catch (Exception ex){
            Assert.fail("Silent mode does not allow exceptions");
        }
    }

    @Test
    public void testGetCentralAngle(){
        // Test Berlin and Tokio https://de.wikipedia.org/wiki/Orthodrome
        double expectedAngle = 1.400;

        double lon1 = 13.40;
        double lat1 =  52.517;

        double theta1 = lon1 ;
        double  phi1 = 90 -lat1;

        double lon2 = 138.767;
        double lat2 = 35.70;

        double theta2 = lon2 ;
        double phi2 = 90 - lat2;

        try {
            SphericCoordinate sphericCoordinate1 = new SphericCoordinate(phi1,theta1,6371);
            SphericCoordinate sphericCoordinate2 = new SphericCoordinate(phi2,theta2, 6371);
            assertEquals(expectedAngle,sphericCoordinate1.getCentralAngle(sphericCoordinate2),0.1);
        } catch (Exception e) {
            Assert.fail("Silent mode does not allow exceptions");
        }


    }
}
