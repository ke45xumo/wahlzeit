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

public class SphericCoordinate extends AbstractCoordinate {
    // Members
    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate(double phi, double theta, double radius){
        assertClassInvariants();
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
        assertClassInvariants();
    }

    /**
     * Gets Class Member phi
     * @return Class Member phi
     */
    public double getPhi() {
        assertClassInvariants();
        return phi;
    }
    /**
     * Gets Class Member radius
     * @return Class Member radius
     */
    public double getRadius() {
        assertClassInvariants();
        return radius;
    }
    /**
     * Gets Class Member theta
     * @return Class Member theta
     */
    public double getTheta() {
        assertClassInvariants();
        return theta;
    }
    /**
     * Sets Class Member phi
     * @param phi Class Member phi
     * @return
     */
    public void setPhi(double phi) {
        assertClassInvariants();
        this.phi = phi;
        assertClassInvariants();
    }
    /**
     * Sets Class Member radius
     * @param radius Class Member radius
     * @return
     */
    public void setRadius(double radius) {
        assertClassInvariants();
        this.radius = radius;
        assertClassInvariants();
    }
    /**
     * Sets Class Member theta
     * @param theta:    Class Member theta
     * @return
     */
    public void setTheta(double theta) {
        assertClassInvariants();
        this.theta = theta;
        assertClassInvariants();
    }

    /**
     * Converts this {@link SphericCoordinate} to an Cartesian Coordinate.
     * @return this SphericCoordinate as Cartesian Coordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        /* No need to check Pre and Post Conditions
            ==> Those are already checked by Contructors of this and
                and the new Coordinate
         */

        assertClassInvariants();
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);

        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x,y,z);
        assertClassInvariants();
        return cartesianCoordinate;
    }

    /**s
     * Returns this Spheric Coordinate as Cartesian Coordinate
     * @return this Spheric Coordinate as Cartesian Coordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();
        return this;
    }


    /**
     * ClassInvariant for checking if the variables
     *      - radius
     *      - theta
     *      - phi
     * are in expected boundries.
     *
     * In addition checking if variables are:
     *     - a Number
     *     - are Finite
     *
     * @throws Exception
     */
    @Override
    protected void assertClassInvariants()  {
        /*====================================
         *  check if a Number and Finite
         *===================================*/
        assert(!Double.isNaN(radius)): "Radius must be a Number";
        assert(Double.isFinite(radius)): "Radius must be finite";

        assert(!Double.isNaN(theta)): "Theta must be a Number";
        assert(Double.isFinite(theta)): "Theta must be finite";

        assert(!Double.isNaN(phi)): "Phi must be a Number";
        assert(Double.isFinite(phi)): "Phi must be finite";

        /*====================================
         * check expected boundries
         *====================================*/
        assert(radius >= 0): "Radius: " + radius + ".Radius must be greater than 0";

        assert(theta >= 0): "Theta: " + theta + ".Theta must be greater than 0";
        assert(theta <= 180): "Theta: " + theta + ".Theta must be less than 180";

        assert(phi >= 0): "Phi: " + phi + ".Phi must be greater than 0";
        assert(phi < 360): "Phi: " + phi + ".Phi must be less than 360";
    }
}
