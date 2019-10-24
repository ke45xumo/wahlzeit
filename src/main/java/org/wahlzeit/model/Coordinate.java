package org.wahlzeit.model;
/*
 * Class:   Coordinate
 * Version: 1.0
 * Date:    2019-10-24
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
public class Coordinate {
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
    public Coordinate(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Calculates the Cartesian distance between the Points provided by Coordinate coordinate.
     *
     * @param coordinate:   Coordinate (3-dimensional) to Calculate the cartesian Distance
     * @return              direct Cartesian Distance of Parameter coordinate
     */
    public double getDistance(Coordinate coordinate){

        // Calculate the Difference and square it
        double squaredDiff_X = Math.pow(this.x - coordinate.x, 2);
        double squaredDiff_Y = Math.pow(this.y - coordinate.y, 2);
        double squaredDiff_Z = Math.pow(this.z - coordinate.z, 2);

        //Calculate and return the Distance
        return Math.sqrt(squaredDiff_X + squaredDiff_Y + squaredDiff_Z);
    }

    /**
     * Test whether the provided Coordinate coordinate is equal to this Coordinate,
     * by checking if its x-, y-, z-Coordinates are equal.
     *
     * @param coordinate:   Coordinate (3-dimensional) to compare this Coordinate with
     * @return             True:   if coordinate is equal to this Coordinate
     *                      False:  else
     */
    public boolean isEqual(Coordinate coordinate){

        // Test for specific Coordinates
        boolean bEqual_x = (this.x == coordinate.x);
        boolean bEqual_y = (this.y == coordinate.y);
        boolean bEqual_z = (this.z == coordinate.z);

        return bEqual_x && bEqual_y && bEqual_z;
    }
}
