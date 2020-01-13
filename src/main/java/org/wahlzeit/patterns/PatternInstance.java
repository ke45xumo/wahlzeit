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

package org.wahlzeit.patterns;


import java.lang.annotation.*;


/**
 * Annotation, identifying a Design Pattern
 * and its components (participant)
 */
@Repeatable(PatternInstance.patternInstanceList.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PatternInstance {
    public DesignPattern patternName();
    public String [] participants();

    /*============================================
     * For Mutliple Patterns
     * ***********************
     * (A Class can be part of multiple Patterns,
     *  e.g. Singleton and Abstract Factory
     *============================================*/
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface patternInstanceList{
        PatternInstance [] value();
    }
}
