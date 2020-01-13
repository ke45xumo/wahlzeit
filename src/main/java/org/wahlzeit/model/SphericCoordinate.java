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

import com.google.apphosting.api.ApiProxy;
import org.wahlzeit.patterns.DesignPattern;
import org.wahlzeit.patterns.PatternInstance;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

@PatternInstance(
        patternName = DesignPattern.SINGLETON,
        participants = {"Singleton"}

)
@PatternInstance(
        patternName = DesignPattern.TEMPLATE_METHOD,
        participants = {"Concrete Instance"}

)
public class SphericCoordinate extends AbstractCoordinate {
    // Members
    private double phi;
    private double theta;
    private double radius;

    private final static ConcurrentHashMap<Integer,SphericCoordinate> coordinateHashMap = new ConcurrentHashMap<>();



    private SphericCoordinate(double phi, double theta, double radius) throws IllegalStateException, IllegalArgumentException {
        assertClassInvariants();
        setPhi(phi);
        setTheta(theta);
        setRadius(radius);

        // False Arguments
        try {
            assertClassInvariants();
        }catch (IllegalStateException e){
            final String msg = e.getMessage();
            log.log(Level.SEVERE,msg);
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Get an Singleton Instance
     * (--> Making CartesianCoordinate Shared)
     *
     * @param phi:          Phi
     * @param theta:        Theta
     * @param radius:       Radius
     * @return:             new or existing SphericCoordinate with phi, theta and radius
     */
    public static SphericCoordinate getInstance(double phi, double theta, double radius){
        SphericCoordinate newCoordinate = new SphericCoordinate(phi, theta, radius);
        int coordinateHash = newCoordinate.hashCode();

        synchronized (coordinateHashMap){
            SphericCoordinate existentCoordinate = coordinateHashMap.putIfAbsent(coordinateHash,newCoordinate);

            // Already in Map
            if (existentCoordinate != null){
                return  existentCoordinate;
            }else{
                return newCoordinate;
            }
        }

    }
    /**
     * Gets Class Member phi
     * @return Class Member phi
     */
    public double getPhi() throws IllegalStateException{
        assertClassInvariants();
        return phi;
    }
    /**
     * Gets Class Member radius
     * @return Class Member radius
     */
    public double getRadius() throws IllegalStateException{
        assertClassInvariants();
        return radius;
    }
    /**
     * Gets Class Member theta
     * @return Class Member theta
     */
    public double getTheta() throws IllegalStateException{
        assertClassInvariants();
        return theta;
    }
    /**
     * Sets Class Member phi
     * @param phi Class Member phi
     * @return
     */
    private void setPhi(double phi) throws IllegalStateException, IllegalArgumentException{
        assertClassInvariants();
        this.phi = phi;

        try {
            assertClassInvariants();
        }catch (IllegalStateException e){
            final String msg = e.getMessage();
            log.log(Level.SEVERE,msg);
            throw new IllegalArgumentException(msg);
        }
    }
    /**
     * Sets Class Member radius
     * @param radius Class Member radius
     * @return
     */
    private void setRadius(double radius) throws IllegalStateException{
        assertClassInvariants();
        this.radius = radius;

        try {
            assertClassInvariants();
        }catch (IllegalStateException e){
            final String msg = e.getMessage();
            log.log(Level.SEVERE,msg);
            throw new IllegalArgumentException(msg);
        }
    }
    /**
     * Sets Class Member theta
     * @param theta:    Class Member theta
     * @return
     */
    private void setTheta(double theta) throws IllegalStateException{
        assertClassInvariants();
        this.theta = theta;
        try {
            assertClassInvariants();
        }catch (IllegalStateException e){
            final String msg = e.getMessage();
            log.log(Level.SEVERE,msg);
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Converts this {@link SphericCoordinate} to an Cartesian Coordinate.
     * @return this SphericCoordinate as Cartesian Coordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException{
        /* No need to check Pre and Post Conditions
            ==> Those are already checked by Contructors of this and
                and the new Coordinate
         */

        assertClassInvariants();
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);

        CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getInstance(x,y,z);
        assertClassInvariants();
        return cartesianCoordinate;
    }

    /**s
     * Returns this Spheric Coordinate as Cartesian Coordinate
     * @return this Spheric Coordinate as Cartesian Coordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() throws IllegalStateException{
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
    protected void assertClassInvariants()  throws IllegalStateException{
        try {
            /*====================================
             *  check if a Number and Finite
             *===================================*/
            assert (!Double.isNaN(radius)) : "Radius must be a Number";
            assert (Double.isFinite(radius)) : "Radius must be finite";

            assert (!Double.isNaN(theta)) : "Theta must be a Number";
            assert (Double.isFinite(theta)) : "Theta must be finite";

            assert (!Double.isNaN(phi)) : "Phi must be a Number";
            assert (Double.isFinite(phi)) : "Phi must be finite";

            /*====================================
             * check expected boundries
             *====================================*/
            assert (radius >= 0) : "Radius: " + radius + ".Radius must be greater than 0";

            assert (theta >= 0) : "Theta: " + theta + ".Theta must be greater than 0";
            assert (theta <= 180) : "Theta: " + theta + ".Theta must be less than 180";

            assert (phi >= 0) : "Phi: " + phi + ".Phi must be greater than 0";
            assert (phi < 360) : "Phi: " + phi + ".Phi must be less than 360";
        }catch (AssertionError e){
            final String msg = e.getMessage();
            log.log(Level.SEVERE,msg);
            throw new IllegalStateException(msg);
        }
    }

    /**
     * Comparing the the Objects (Types and Contents)
     *
     * @param obj:  Object to be compared with this Instance
     * @return:     True, if the same
     *              False, else
     */
    @Override
    public boolean equals(Object obj) {
        if ( obj == null || !(obj instanceof SphericCoordinate) ){
            return false;
        }
        SphericCoordinate argCoordinate = (SphericCoordinate) obj;

        if ( !(this.isEqual(argCoordinate)) ){
            return false;
        }
        return true;
    }

    /**
     * Hashcode of the Object by Building
     * the Hash over the Coordinates x,y,z
     *
     * @return      Hash of the Coordinates x,y,z
     */
    @Override
    public int hashCode() {
        return Objects.hash(theta,phi,radius);
    }

    /**
     * Return this instead of a clone
     *
     * @return this {@link SphericCoordinate}
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() {
        return this;
    }
}
