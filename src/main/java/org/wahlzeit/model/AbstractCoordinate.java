package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();

    @Override
    public  abstract SphericCoordinate asSphericCoordinate() throws Exception;


    /**
     * Gets the Cartesian Distance between this and the Coordinate provided by Argument
     *
     * @param coordinate Coordinate to calculate the distance to
     * @return Cartesian Distance between the Coordinates
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        // Precondition
        assert(coordinate != null);
        assert(coordinate instanceof CartesianCoordinate || coordinate instanceof SphericCoordinate);

        Double distance = doGetCartesianDistance(coordinate);

        //PostCondition
        assert(Double.isFinite(distance));
        assert(!Double.isNaN(distance));
        assert(distance >= 0);

        return  distance;
    }

    /**
     * Gets the Cartesian Distance between this and the Coordinate provided by Argument
     * @param coordinate Coordinate to calculate the distance to
     * @return Cartesian Distance between the Coordinates
     */
    protected double doGetCartesianDistance(Coordinate coordinate){
        CartesianCoordinate cartesianCoordinate = this.asCartesianCoordinate();             // This object as Cartesian Coordinate
        CartesianCoordinate cartesianCoordinateArgument = coordinate.asCartesianCoordinate(); // Argument as Cartesian Coordinate

        // Calculate the Difference and square it
        double squaredDiff_X = Math.pow(cartesianCoordinate.getX() - cartesianCoordinateArgument.getX(), 2);
        double squaredDiff_Y = Math.pow(cartesianCoordinate.getY() - cartesianCoordinateArgument.getY(), 2);
        double squaredDiff_Z = Math.pow(cartesianCoordinate.getZ() - cartesianCoordinateArgument.getZ(), 2);

        //Calculate and return the Distance
        return Math.sqrt(squaredDiff_X + squaredDiff_Y + squaredDiff_Z);
    }

    /**
     * This function calculates the CentralAngle between two Coordinates.
     * This is done by getting the Spheric Coordinates of this and the provided Coordinate
     *
     * @param coordinate Coordinate to get central angle between this coordinate.
     * @return central Angle between this coordinate and the coordinate provided by Argument
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) throws Exception {
        // Precondition
        assert(coordinate != null) : "Coordinate is null";
        assert(coordinate instanceof CartesianCoordinate || coordinate instanceof SphericCoordinate):
                "Coordinate is not instance of CartesianCoordinate nor of SphericCoordinate";

        Double angle = doGetCentralAngle(coordinate);

        //PostCondition
        assert(Double.isFinite(angle)):"CentralAngle is not Finite";
        assert(!Double.isNaN(angle)):"CentralAngle is not a Number";
        assert(angle >= 0):"CentralAngle is less than 0";

        return  angle;
    }

    /**
     * This function calculates the CentralAngle between two Coordinates.
     * This is done by getting the Spheric Coordinates of this and the provided Coordinate
     *
     * @param coordinate Coordinate to get central angle between this coordinate.
     * @return central Angle between this coordinate and the coordinate provided by Argument
     */
    protected double doGetCentralAngle(Coordinate coordinate) throws Exception {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();   // Coordinate (argument) as Spheric Coordinate
        SphericCoordinate thisSphericCoordinate = this.asSphericCoordinate();     // This as Spheric Coordinate
        /*========================================================================================
         * Get Longitude and Latitudes of the Coordinates
         *========================================================================================*/
        double lon1 = thisSphericCoordinate.getTheta();       // Longitude of this Coordinate
        double lat1 = 90 - thisSphericCoordinate.getPhi();    // Latitude of this Coordinate

        double lon2 = sphericCoordinate.getTheta();     // longitude of second Coordinate (argument)
        double lat2 = 90 - sphericCoordinate.getPhi();  // latitude of seconde Coordinate (argument)

        double deltaLambda = Math.abs(lon2 - lon1);     // delta of Longitudes
        /*==========================================================================================
         * Calculate the Numberator of the Calculation
         *===========================================================================================*/
        double numFirstSummand = Math.pow(Math.cos(lat2 * Math.sin(deltaLambda)), 2);   // First summand of the numberator

        // Second summand of the numberator
        double numSecSummand =  Math.pow(
                Math.cos(lat1) * Math.sin(lat2) -
                        Math.sin(lat1) * Math.cos(lat2) * Math.cos(deltaLambda)
                ,2);

        double numerator = Math.sqrt(numFirstSummand + numSecSummand);
        /*==========================================================================================
         * Calculate the Denumberator of the Calculation
         *==========================================================================================*/
        double denumerator = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.sin(lat2) * Math.cos(deltaLambda);
        /*===========================================================================================
         * Calculate the Angle
         *===========================================================================================*/
        double centralAngle = Math.atan(numerator/denumerator);

        return Math.abs(centralAngle);
    }

    /**
     * Test whether the provided Coordinate coordinate is equal to this Coordinate,
     * by checking if its x-, y-, z-Coordinates are equal.
     *
     * @param coordinate:   Coordinate (3-dimensional) to compare this Coordinate with
     * @return             True:   if coordinate is equal to this Coordinate
     *                      False:  else
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        // Precondition
        assert(coordinate != null) : "Coordinate is null";
        assert(coordinate instanceof CartesianCoordinate || coordinate instanceof SphericCoordinate):
                "Coordinate is not instance of CartesianCoordinate nor of SphericCoordinate";

        boolean isEqual = doIsEqual(coordinate);

        //PostCondition
        assert(isEqual == true || isEqual == false): "isEqual is not true nor false";

        return  isEqual;
    }

    /**
     * Test whether the provided Coordinate coordinate is equal to this Coordinate,
     * by checking if its x-, y-, z-Coordinates are equal.
     *
     * @param coordinate:   Coordinate (3-dimensional) to compare this Coordinate with
     * @return             True:   if coordinate is equal to this Coordinate
     *                      False:  else
     */

    protected boolean doIsEqual(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        CartesianCoordinate thisCartasianCoordinate = this.asCartesianCoordinate();

        // Test for specific Coordinates
        boolean bEqual_x = (thisCartasianCoordinate.getX() == cartesianCoordinate.getX());
        boolean bEqual_y = (thisCartasianCoordinate.getY() == cartesianCoordinate.getY());
        boolean bEqual_z = (thisCartasianCoordinate.getZ() == cartesianCoordinate.getZ());

        return bEqual_x && bEqual_y && bEqual_z;
    }

    /**
     * Check class invariants Abstract Coordinate and its subclasses
     */
    protected abstract void assertClassInvariants();
}
