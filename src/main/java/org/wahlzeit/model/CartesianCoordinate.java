package org.wahlzeit.model;
/*
 * Class:   CartesianCoordinate
 * Version: 1.1
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

/**
 * Class representing a Coordinate of a specific Location
 */
public class CartesianCoordinate implements Coordinate{
    private double x;
    private double y;
    private double z;

    /**
     * Constructor for Class Coordinate
     *
     * @param x:    X-Coordinate of some Location
     * @param y:    Y-Coordinate of some Location
     * @param z:    Z-Coordinate of some Location
     */
    public CartesianCoordinate(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Returns the x-coordinate of this coordinate
     * @return x-coordinate of this Coordinate
     */
    public double getX(){
        return x;
    }
    /**
     * Returns the z-coordinate of this coordinate
     * @return z-coordinate of this Coordinate
     */
    public double getZ(){
        return z;
    }

    /**
     * Returns the y-coordinate of this coordinate
     * @return y-coordinate of this Coordinate
     */
    public double getY(){
        return y;
    }

    /**
     * Sets the class Variable x
     * @param x Member Variable x
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Sets the class Variable y
     * @param y Member Variable y
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * Sets the class Variable z
     * @param z Member Variable z
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Gets this Cartesian Coordinate
     * @return CartesianCoordinate upon which the method is called
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     * Calculates the Cartesian distance between the Points provided by Coordinate coordinate.
     *
     * @param coordinate:   Coordinate (3-dimensional) to Calculate the cartesian Distance
     * @return              direct Cartesian Distance of Parameter coordinate
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();

        // Calculate the Difference and square it
        double squaredDiff_X = Math.pow(this.x - cartesianCoordinate.x, 2);
        double squaredDiff_Y = Math.pow(this.y - cartesianCoordinate.y, 2);
        double squaredDiff_Z = Math.pow(this.z - cartesianCoordinate.z, 2);

        //Calculate and return the Distance
        return Math.sqrt(squaredDiff_X + squaredDiff_Y + squaredDiff_Z);
    }

    /**
     * Converts this {@link CartesianCoordinate} to a Spheric Coordinate
     * @return this {@link CartesianCoordinate} as a {@link SphericCoordinate}
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
        double phi = Math.atan(y/x);
        double theta =  Math.acos(z/radius);

        SphericCoordinate sphericCoordinate =  new SphericCoordinate(phi, theta, radius);

        return sphericCoordinate;
    }

    /**
     * This function calculates the CentralAngle between two Coordinates.
     * This is done by getting the Spheric Coordinates of this and the provided Coordinate
     *
     * @param coordinate Coordinate to get central angle between this coordinate.
     * @return central Angle between this coordinate and the coordinate provided by Argument
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = this.asSphericCoordinate();   // This Coordinate as Spheric Coordinate
        double centralAngle = sphericCoordinate.getCartesianDistance(coordinate);

        return centralAngle;
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
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();

        // Test for specific Coordinates
        boolean bEqual_x = (this.x == cartesianCoordinate.x);
        boolean bEqual_y = (this.y == cartesianCoordinate.y);
        boolean bEqual_z = (this.z == cartesianCoordinate.z);

        return bEqual_x && bEqual_y && bEqual_z;
    }
}
