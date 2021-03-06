package org.wahlzeit.model;
/*
 * Class:   Location
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
 * Class representing a location linked to a Photo
 */
public class Location {
    private Coordinate coordinate;

    /**
     * Constructor for Initialization of an Location object
     * @param coordinate: Coordinate according to the Location
     */
    public Location(AbstractCoordinate coordinate) throws IllegalStateException{
        if (coordinate == null){
            throw new IllegalArgumentException("AbstractCoordinate coordinate may not be null");
        }
        this.coordinate = coordinate;
    }

}
