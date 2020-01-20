package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is a Type Object Class for Beer
 */
public class BeerType extends DataObject {
    private String name;                            // Name of the beertype
    private BeerType superType = null;              // Supertype of BeerType
    private Set<BeerType> subTypes;                 // SubTypes of the BeerType


    public BeerType(String name) {
        this.name = name;
        subTypes = new HashSet();
    }

    /**
     * This function adds a BeerType object (provided by Argument <beerType>) as a Subtype to this Object.
     * In Addition it this object as the parent of the Subtype.
     *
     * @param beerType  Beertype to be added as Subtype to this type object
     */
    protected void addSubType(BeerType beerType){
        assertClassInvariants();
        assert(beerType != null);

        beerType.addSubType(this);    // make this parent of beerType
        subTypes.add(beerType);                 // add beerType as child to this

        assert(subTypes.size() > 0);
        assertClassInvariants();
    }

    /**
     * Determines whether the BeerType object <beerType> is a subtype of this object.
     * This is done by checking if
     *          * this type is the same as the provided argument
     *          * going down the beertype object hirarchy of this beerType ( recursively)
     *
     * @param beerType  BeerType, to be checked whether it is a subtype of this
     * @return  true,   if it is a subtype of this BeerType
     *          false,  else
     */
    protected boolean isSubtype(BeerType beerType){
        assertClassInvariants();
        assert(beerType != null);

        // beerType is the same Type as this
        if(beerType == this){
            return true;
        }

        // Check recursively if beerType is a subtype of this
        for(BeerType child : subTypes){
            if (child.isSubtype(this)){
                return true;
            }
        }

        assertClassInvariants();
        return false;
    }
    /*=================================================================
     * GETTER AND SETTER
     *=================================================================*/
    public BeerType getSuperType() {
        return superType;
    }

    public void setSuperType(BeerType superType) {
        this.superType = superType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BeerType> getSubTypes() {
        return subTypes;
    }


    private void assertClassInvariants(){
        assert(name != null);
        assert(name.length() > 0);
        assert(subTypes != null);
    }
}

