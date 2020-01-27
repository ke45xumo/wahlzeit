package org.wahlzeit.main;

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

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

/**
 * A single-threaded Main class with database connection. Can be used by tools that don't want to start a server.
 */
public class BeerMain extends ServiceMain {

    private static final Logger log = Logger.getLogger(BeerMain.class.getName());

    /**
     *
     */
    protected static BeerMain instance = new BeerMain();

    /**
     *
     */
    protected boolean isToStop = false;

    /**
     *
     */
    protected boolean isInProduction = false;

    /**
     *
     */
    public static BeerMain getInstance() {
        return instance;
    }

    /**
     *
     */
    public void requestStop() {
        synchronized (instance) {
            instance.isToStop = true;
        }
    }

    /**
     *
     */
    public boolean isShuttingDown() {
        return instance.isToStop;
    }

    /**
     *
     */
    public boolean isInProduction() {
        return instance.isInProduction;
    }

    /**
     *
     */
    public void startUp(boolean inProduction, String rootDir) throws Exception {
        isInProduction = inProduction;

        log.config(LogBuilder.createSystemMessage().addAction("Start up ModelMain").toString());
        super.startUp(rootDir);

        log.config(LogBuilder.createSystemMessage().addAction("Configure WebPartTemplateService").toString());
        configureWebPartTemplateService();

        log.config(LogBuilder.createSystemMessage().addAction("Configure WebPartHandler").toString());
        configureWebPartHandlers();

        log.config(LogBuilder.createSystemMessage().addAction("Configure LanguageModels").toString());
        configureLanguageModels();

        log.config(LogBuilder.createSystemMessage().addAction("Add default user with pictures").toString());
        addDefaultUserWithPictures();

        log.config(LogBuilder.createSystemMessage().addMessage("StartUp complete.").toString());
    }
}
