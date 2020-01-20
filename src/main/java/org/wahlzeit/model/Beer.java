/*
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

import org.wahlzeit.services.DataObject;

/**
 * This class is representing a Beer (in real life).
 *
 */
public class Beer extends DataObject {
    private BeerType beerType;          // Type Object for beer

    private BeerSort beerSort;          // BeerSort
    private String name;                // Name of the Beer

    private double alcoholStrength;     // alcohol strength in permille
    private double capacity;            // Capacity in Litre
    private Location origin;              // Location, where the beer was brewed
    private String make;                // make of the beer
    private String model;               // model of the beer


    public Beer(BeerType beerType, BeerSort beerSort, String name, double alcoholStrength, double capacity, Location origin, String make, String model) {
        this.beerType = beerType;
        this.beerSort = beerSort;
        this.name = name;
        this.alcoholStrength = alcoholStrength;
        this.capacity = capacity;
        this.origin = origin;
        this.make = make;
        this.model = model;

        assertClassInvariants();
    }

    /*=================================================================
     * GETTER AND SETTER
     *=================================================================*/
    public String getName() {
        assertClassInvariants();
        return name;
    }

    public void setName(String name) {
        assertClassInvariants();
        this.name = name;
    }

    public BeerType getBeerType() {
        assertClassInvariants();
        return beerType;
    }

    public void setBeerType(BeerType beerType) {
        assertClassInvariants();
        this.beerType = beerType;
    }

    public BeerSort getBeerSort() {
        assertClassInvariants();
        return beerSort;
    }

    public void setBeerSort(BeerSort beerSort) {
        assertClassInvariants();
        this.beerSort = beerSort;
        assertClassInvariants();
    }

    public double getAlcoholStrength() {
        assertClassInvariants();
        return alcoholStrength;
    }

    public void setAlcoholStrength(double alcoholStrength) {
        assertClassInvariants();
        this.alcoholStrength = alcoholStrength;
        assertClassInvariants();
    }

    public double getCapacity() {
        assertClassInvariants();
        return capacity;
    }

    public void setCapacity(double capacity) {
        assertClassInvariants();
        this.capacity = capacity;
        assertClassInvariants();
    }

    public Location getOrigin() {
        assertClassInvariants();
        return origin;
    }

    public void setOrigin(Location origin) {
        assertClassInvariants();
        this.origin = origin;
        assertClassInvariants();
    }

    public String getMake() {
        assertClassInvariants();
        return make;
    }

    public void setMake(String make) {
        assertClassInvariants();
        this.make = make;
        assertClassInvariants();
    }

    public String getModel() {
        assertClassInvariants();
        return model;
    }

    public void setModel(String model) {
        assertClassInvariants();
        this.model = model;
        assertClassInvariants();
    }


    /**
     * This is  a helper method to check whether the parameters to initialize an Beer Object are semantically correct.
     * (In Addition this ensures, that no semantically wrong Beer and BeerType Objects are in their corresponding maps
     * in the BeerManager)
     *
     */
    void assertClassInvariants(){
        assert(beerType != null);
        assert(beerSort != null);
        assert(name != null && name.length() > 0);
        assert(alcoholStrength > 0);
        assert(capacity > 0);
        assert(origin != null);
        assert(make != null && make.length() > 0);
        assert(model != null && make.length() > 0);
    }
}
