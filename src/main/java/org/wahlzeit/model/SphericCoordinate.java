/*
 * Class:   SphericCoordinate
 * Version: 1.0
 * Date:    2019-11-16
 *
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {
    // Members
    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate(double phi, double theta, double radius) throws Exception{
        if (radius < 0){
            throw new Exception("Radius cannot be less then 0");
        }

        if (theta < 0 || theta >180){
            throw new Exception("Theta must be 0 <= theta <= 180");
        }

        if (phi < 0 || phi >= 360){
            throw new Exception("Phi must be 0 <= phi <360");
        }
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    /**
     * Gets Class Member phi
     * @return Class Member phi
     */
    public double getPhi() {
        return phi;
    }
    /**
     * Gets Class Member radius
     * @return Class Member radius
     */
    public double getRadius() {
        return radius;
    }
    /**
     * Gets Class Member theta
     * @return Class Member theta
     */
    public double getTheta() {
        return theta;
    }
    /**
     * Sets Class Member phi
     * @param phi Class Member phi
     * @return
     */
    public void setPhi(double phi) {
        this.phi = phi;
    }
    /**
     * Sets Class Member radius
     * @param radius Class Member radius
     * @return
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
    /**
     * Sets Class Member theta
     * @param theta:    Class Member theta
     * @return
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }

    /**
     * Converts this {@link SphericCoordinate} to an Cartesian Coordinate.
     * @return this SphericCoordinate as Cartesian Coordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);

        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x,y,z);

        return cartesianCoordinate;
    }

    /**
     * Gets the Cartesian Distance between this and the Coordinate provided by Argument
     *
     * @param coordinate Coordinate to calculate the distance to
     * @return Cartesian Distance between the Coordinates
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = this.asCartesianCoordinate();             // This object as Cartesian Coordinate
        CartesianCoordinate cartesianCoordinateArgument = coordinate.asCartesianCoordinate(); // Argument as Cartesian Coordinate

        return cartesianCoordinate.getCartesianDistance(cartesianCoordinateArgument);
    }

    /**s
     * Returns this Spheric Coordinate as Cartesian Coordinate
     * @return this Spheric Coordinate as Cartesian Coordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * This function calculates the CentralAngle between two Coordinates.
     * This is done by getting the Spheric Coordinates of this and the provided Coordinate
     * and calculating the Great-Circle-Distance between them.
     *
     * @param coordinate Coordinate to get central angle between this coordinate.
     * @return central Angle between this coordinate and the coordinate provided by Argument
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) throws Exception {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();   // Coordinate (argument) as Spheric Coordinate
        /*========================================================================================
         * Get Longitude and Latitudes of the Coordinates
         *========================================================================================*/
        double lon1 = getTheta() - 90; // Longitude of this Coordinate
        double lat1 = getPhi();        // Latitude of this Coordinate

        double lon2 = sphericCoordinate.getTheta() - 90; // longitude of second Coordinate (argument)
        double lat2 = sphericCoordinate.getPhi();        // latitude of seconde Coordinate (argument)

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

        return centralAngle;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = asCartesianCoordinate();

        return cartesianCoordinate.isEqual(coordinate);
    }
}
