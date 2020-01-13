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

import org.wahlzeit.patterns.DesignPattern;
import org.wahlzeit.patterns.PatternInstance;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;

/**
 * Class representing a Coordinate of a specific Location
 */
@PatternInstance(
        patternName = DesignPattern.SINGLETON,
        participants = {"Singleton"}

)
@PatternInstance(
        patternName = DesignPattern.TEMPLATE_METHOD,
        participants = {"Concrete Instance"}

)
public class CartesianCoordinate extends AbstractCoordinate{
    private  double x;
    private  double y;
    private  double z;

    private final static ConcurrentHashMap<Integer,CartesianCoordinate> coordinateHashMap = new ConcurrentHashMap();

    /**
     * Constructor for Class Coordinate
     *
     * @param x:    X-Coordinate of some Location
     * @param y:    Y-Coordinate of some Location
     * @param z:    Z-Coordinate of some Location
     */
    private CartesianCoordinate(double x, double y, double z) throws IllegalStateException, IllegalArgumentException{
        assertClassInvariants();
        setX(x);
        setY(y);
        setZ(z);

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
     * @param x:    X-Coordinate of some Location
     * @param y:    Y-Coordinate of some Location
     * @param z:    Z-Coordinate of some Location
     * @return:     new or existing CartesianCoordinate with Coordinates x, y and z
     */
    public static CartesianCoordinate getInstance(double x, double y, double z){
        CartesianCoordinate newCoordinate = new CartesianCoordinate(x,y,z);
        int coordinateHash = newCoordinate.hashCode();

        synchronized (coordinateHashMap) {
            CartesianCoordinate existentCoordinate = coordinateHashMap.putIfAbsent(coordinateHash, newCoordinate);

            if (existentCoordinate != null) {
                return existentCoordinate;
            } else {
                return newCoordinate;
            }
        }
    }
    /**
     * Returns the x-coordinate of this coordinate
     * @return x-coordinate of this Coordinate
     */
    public double getX() throws IllegalStateException{
        assertClassInvariants();
        return x;
    }
    /**
     * Returns the z-coordinate of this coordinate
     * @return z-coordinate of this Coordinate
     */
    public double getZ() throws IllegalStateException{
        assertClassInvariants();
        return z;
    }

    /**
     * Returns the y-coordinate of this coordinate
     * @return y-coordinate of this Coordinate
     */
    public double getY() throws IllegalStateException{
        assertClassInvariants();
        return y;
    }


    /**
     * Sets the class Variable x
     * @param x Member Variable x
     */
    private void setX(double x) throws IllegalStateException, IllegalArgumentException{
        assertClassInvariants();
        this.x = x;

        try {
            assertClassInvariants();
        }catch (IllegalStateException e){
            final String msg = e.getMessage();
            log.log(Level.SEVERE,msg);
            throw new IllegalArgumentException(msg);
        }
    }
    /**
     * Sets the class Variable y
     * @param y Member Variable y
     */
    private void setY(double y) throws IllegalStateException, IllegalArgumentException{
        assertClassInvariants();
        this.y = y;

        try {
            assertClassInvariants();
        }catch (IllegalStateException e){
            final String msg = e.getMessage();
            log.log(Level.SEVERE,msg);
            throw new IllegalArgumentException(msg);
        }
    }
    /**
     * Sets the class Variable z
     * @param z Member Variable z
     */
    private void setZ(double z) throws IllegalStateException, IllegalArgumentException{
        assertClassInvariants();
        this.z = z;
        try {
            assertClassInvariants();
        }catch (IllegalStateException e){
            final String msg = e.getMessage();
            log.log(Level.SEVERE,msg);
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Gets this Cartesian Coordinate
     * @return CartesianCoordinate upon which the method is called
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException{
        assertClassInvariants();
        return this;
    }


    /**
     * Converts this {@link CartesianCoordinate} to a Spheric Coordinate
     * @return this {@link CartesianCoordinate} as a {@link SphericCoordinate}
     */
    @Override
    public SphericCoordinate asSphericCoordinate()  throws IllegalStateException{
        // Class Invariant
        assertClassInvariants();

        double radius = Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
        double phi = Math.atan(y/x);
        double theta =  Math.acos(z/radius);

        SphericCoordinate sphericCoordinate =  SphericCoordinate.getInstance(phi, theta, radius);

        /* No need to check Invariants/ Post Conditions ==> Invariants covered by Constructor */
        assertClassInvariants();
        return sphericCoordinate;
    }

    /**
     * ClassInvariant for checking
     * if the variables:
     *    - x
     *    - y
     *    - z
     * are:
     *     - a Number
     *     - Finite
     *
     * @throws Exception
     */
    @Override
    protected void assertClassInvariants() throws IllegalStateException{
        /*====================================
         *  check if a Number and Finite
         *===================================*/
        try {
            assert (!Double.isNaN(x)) : "X must be a Number";
            assert (Double.isFinite(x)) : "X must be finite";

            assert (!Double.isNaN(y)) : "Y must be a Number";
            assert (Double.isFinite(y)) : "Y must be finite";

            assert (!Double.isNaN(z)) : "Z must be a Number";
            assert (Double.isFinite(z)) : "Z must be finite";
        }catch(AssertionError e){
            final String msg = e.getMessage();
            log.log(Level.SEVERE,msg);
            throw new IllegalStateException(msg);
        }
    }

    /**
     * Comparing two Objects (Types and Contents)
     *
     * @param obj:  Object to be compared with this Instance
     * @return:     True, if the same
     *              False, else
     */
    @Override
    public boolean equals(Object obj) {
        assertClassInvariants();

        if ( obj == null || !(obj instanceof CartesianCoordinate) ){
            return false;
        }
        CartesianCoordinate argCoordinate = (CartesianCoordinate) obj;

        if ( !(this.isEqual(argCoordinate)) ){
            return false;
        }

        assertClassInvariants();
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
        assertClassInvariants();
        return Objects.hash(x,y,z);
    }

    /**
     * Return this instead of a clone
     *
     * @return this CartesianCoordinate
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone(){
        assertClassInvariants();
        return this;
    }
}
