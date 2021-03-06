/*
 * Class:   BeerPhotoFactory
 * Version: 1.0
 * Date:    2019-11-08
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

import org.wahlzeit.patterns.DesignPattern;
import org.wahlzeit.patterns.PatternInstance;

import java.util.logging.Logger;
@PatternInstance(
        patternName = DesignPattern.SINGLETON,
        participants = {"Singleton"}

)

@PatternInstance(
        patternName = DesignPattern.ABSTRACT_FACTORY,
        participants = {"Concrete Factory"}

)
public class BeerPhotoFactory extends PhotoFactory {

    private static final Logger log = Logger.getLogger(BeerPhotoFactory.class.getName());

    private static BeerPhotoFactory instance = null;

    /**
     * Constructor
     */
    public BeerPhotoFactory(){
       // super.setInstance(this);
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(PhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize PhotoFactory twice");
        }

        instance = (BeerPhotoFactory)photoFactory;
    }

    /**
     * @methodtype factory
     */
    public Photo createPhoto() {
        return new BeerPhoto();
    }

    /**
     * Creates a new photo with the specified id
     */
    @Override
    public Photo createPhoto(PhotoId id) {
        return new BeerPhoto(id);
    }

}
