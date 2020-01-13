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

/**
 * Interface, representing 23 Design Patterns,
 * mentioned in the "Gang of Four"
 *
 * Creational: The design patterns that deal with the creation of an object.
 * Structural: The design patterns in this category deals with the class structure such as Inheritance and Composition.
 * Behavioral: This type of design patterns provide solution for the better interaction between objects, how to provide lose coupling, and flexibility to extend easily in future.
 *
 * Inspired by https://www.journaldev.com/31902/gangs-of-four-gof-design-patterns
 */
public enum DesignPattern {
    /*================================
     * Creational Designs
     *==============================*/
    SINGLETON,
//    FACTORY,
    ABSTRACT_FACTORY,
//    BUILDER,
//    PROTOTYPE,
    /*================================
     * Structural Design Patterns
     *==============================*/
//    ADAPTER,
//    COMPOSITE,
    PROXY,
//    FLYWEIGHT,
//    FACADE,
//    BRIDGE,
//    DECORATOR,
    /*================================
     * Behavioral Design Patterns
     *==============================*/
    TEMPLATE_METHOD,
//    MEDIATOR,
//    CHAIN_OF_RESPONSIBILITY,
//    OBSERVER,
//    STRATEGY,
    COMMAND,
//    STATE,
//    VISITOR,
//    INTERPRETER,
//    ITERATOR,
//    MEMENTO,
    /*================================
    * Miscellaneous Design Patterns
    *==============================*/
//    DAO_DESIGN,
//    DEPENDENCY_INJECTION,
//    MVC
}
