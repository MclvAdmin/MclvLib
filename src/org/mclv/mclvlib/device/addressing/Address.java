/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device.addressing;
import org.mclv.mclvlib.exception.*;
import java.util.Vector;
/**
 *
 * @author god
 */
public class Address {
private static Vector addressList;
private int addRun = 0;
public int group;
public int system;
public int id;
    public Address(int group, int system, int id){
        if(addRun == 0){
            addressList = new Vector(0);
        }
        this.group = group;
        this.system = system;
        this.id = id;
    }
    public synchronized static void constructAndList(int groupReq,int systemReq,int idReq) throws DuplicateIdentityException{
       if(isUniqueInt(groupReq, systemReq, idReq)){
        addressList.addElement(new Address(groupReq, systemReq, idReq));
       }
       else{
           throw new DuplicateIdentityException(groupReq, systemReq, idReq); //tells that there is already an address with
       }
    }
    public synchronized static boolean isUniqueInt(int groupReq, int systemReq, int idReq){
        boolean unique = true;
        for(int index = 0; index<addressList.size(); index++){
            if(((Address) addressList.elementAt(index)).group == groupReq && ((Address) addressList.elementAt(index)).system == systemReq && ((Address) addressList.elementAt(index)).id == idReq){
                unique = false; 
            }
        }
        return unique;
    }
    public synchronized boolean equals(Address address){
        boolean equal = false;
        if(address.group == group && address.system == system && address.id == id){
            equal = true;
        }
        return equal;
    }
    public synchronized static void removeAddress(Address removeThis){ //An address is given because there is a address object attached to each hardware object.
        for(int index = 0; index<addressList.size(); index++){
            if(((Address) addressList.elementAt(index)).equals(removeThis)){
                addressList.removeElementAt(index);
            }
        }
    }
}
