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


import org.wahlzeit.services.ObjectManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is responsible for the Creation of the Beer related objects ( Beer, BeerType).
 * It is implemented as Singleton and responsible for not having redundant beer Objects.
 *
 */
public class BeerManager extends ObjectManager {
    private static BeerManager instance;
    private static Map<String, BeerType> mBeerTypeMap;
    private static Map<String, Beer> mBeerMap;

    /**
     * Constructor.
     * Initializing the Object Maps
     */
    private BeerManager(){
        mBeerTypeMap = new ConcurrentHashMap<>();
        mBeerMap = new ConcurrentHashMap<>();
    }

    /**
     * Singleton with double check locking
     *
     * @return unique instance of BeerManager
     */
    public static synchronized BeerManager getInstance(){
        // double check locking
        if (instance == null) {
            synchronized (BeerManager .class) {
                if (instance == null) {
                    instance = new BeerManager();
                }
            }
        }
        return instance;
    }

    /**
     * This function creates a Beer Object Instance.
     * In Addition it is logging
     *      * the Beer Object to a HashMap (key: Name of the Beer)
     *      * the BeerType to a HashMap (key: Name of the BeerType)
     *
     * If the Beer already exists (in the HashMap) it will return the object logged in the map,
     * else it will be creating and adding a new Beer Object Instance with properties provided by
     * the arguments to the Beer HashMap.
     *
     * It also checks, whether the Type Object BeerType is already known,
     * by checking if it is in the HashMap mBeerTypeMap.
     * If not: it will be added within the creation of an Beer object instance.
     *
     * ( It is ensured, that the parameters are correct by colling the new operator on Beer class.
     * Else an AssertionException will be thrown.see the class invariants)
     *
     * @param beerType          Type Object for beer
     * @param beerSort          BeerSort
     * @param name              Name of the Beer
     * @param alcoholStrength   alcohol strength in permille
     * @param capacity          Capacity in Litre
     * @param origin            Location, where the beer was brewed
     * @param make              make of the beer
     * @param model             model of the beer
     *
     * @return  a unique Beer instance (either previously created or a fresh instance)
     */
    public synchronized Beer createBeerInstance(BeerType beerType, BeerSort beerSort, String name, double alcoholStrength, double capacity, Location origin, String make, String model){
        assert(name != null && name.length() > 0);

        // Beer Object instance already exists
        if(mBeerMap.containsKey(name)){
            return mBeerMap.get(name);
        }

        // Beer Object instance does not exist
        // --> create Beer Instance and add to map
        Beer beer = new Beer(beerType, beerSort, name, alcoholStrength,capacity,origin,make,model);
        mBeerMap.put(name,beer);

        // Add the beerType to the BeerType map, if not already exists
        String beerTypeName = beerType.getName();
        mBeerTypeMap.putIfAbsent(beerTypeName,beerType);

        return beer;
    }

    /**
     * Creates an Instance of a BeerType Type Object
     *
     * @param name   = name of the type (unique identifier for the typeObject)
     * @return  unique instance of a type object (either previously created or a fresh instance)
     */
    public synchronized BeerType createBeerTypeInstance(String name){
        if(mBeerTypeMap.containsKey(name)){
            return mBeerTypeMap.get(name);
        }

        BeerType beerType = new BeerType(name);
        mBeerTypeMap.put(name,beerType);

        return beerType;
    }


}
