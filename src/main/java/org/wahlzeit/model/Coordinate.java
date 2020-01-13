/*
 * Class:   Coordinate
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

@PatternInstance(
        patternName = DesignPattern.TEMPLATE_METHOD,
        participants = {"Abstract Template"}

)
public interface Coordinate {

    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException;

    public SphericCoordinate asSphericCoordinate() throws IllegalStateException;

    public double getCartesianDistance(Coordinate coordinate) throws IllegalStateException, IllegalArgumentException;

    public double getCentralAngle(Coordinate coordinate) throws IllegalStateException, IllegalArgumentException;

    public boolean isEqual(Coordinate coordinate);

    public boolean equals (Object obj);

    public int hashCode();



}
