/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;

/**
 *
 * @author god
 */
public class HardwareElement { //VERY simple, just a container that allows for unorganized, collective assignment and reduces the Vector-in-Vector factor of CF01's code
public Object device;
public int group;
public int system;
public int address;
    public HardwareElement(int group, int system, int address, Object device){
        this.group = group;
        this.system = system;
        this.address = address;
        this.device = device;
    }
}
