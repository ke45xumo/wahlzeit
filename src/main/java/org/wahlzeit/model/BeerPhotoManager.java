/*
 * Class:   BeerPhotoManager
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


import com.google.appengine.api.images.Image;
import org.wahlzeit.services.Persistent;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class BeerPhotoManager extends PhotoManager {

    /**
     *
     */
    protected static final PhotoManager instance = new BeerPhotoManager();

    private static final Logger log = Logger.getLogger(PhotoManager.class.getName());

    /**
     * Constructor
     */
    public BeerPhotoManager(){
        super.photoTagCollector = BeerPhotoFactory.getInstance().createPhotoTagCollector();
    }

    /**
     *
     */
    @Override
    public Photo getPhotoFromId(PhotoId id) {
        if (id == null) {
            return null;
        }

        Photo result = doGetPhotoFromId(id);

        if (result == null) {
            result = BeerPhotoFactory.getInstance().loadPhoto(id);
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    /**
     * @methodtype command
     *
     * Loads all scaled Images of this Photo from Google Cloud Storage
     */
    protected void loadScaledImages(BeerPhoto photo) {
        super.loadScaledImages(photo);
    }

    /**
     *
     */
    public void savePhoto(BeerPhoto photo) {
        updateObject(photo);
    }

    @Override
    protected void updateDependents(Persistent obj) {
        if (obj instanceof BeerPhoto) {
            Photo photo = (Photo) obj;
            saveScaledImages(photo);
            updateTags(photo);
            UserManager userManager = UserManager.getInstance();
            Client owner = userManager.getClientById(photo.getOwnerId());
            userManager.saveClient(owner);
        }
    }

    /**
     * @methodtype command
     *
     * Persists all available sizes of the Photo. If one size exceeds the limit of the persistence layer, e.g. > 1MB for
     * the Datastore, it is simply not persisted.
     */
    protected void saveScaledImages(BeerPhoto photo) {
       super.saveScaledImages(photo);
    }


    /**
     * @methodtype command
     */
    public void addPhoto(BeerPhoto photo) throws IOException {
        super.addPhoto(photo);
    }

    @Override
    public BeerPhoto createPhoto(String filename, Image uploadedImage) throws Exception {
        return (BeerPhoto)super.createPhoto(filename, uploadedImage);
    }
}
