/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;

/**
 *
 * @author god
 */
public class DsBus implements DataBus{
public static final int DS_BUS_MIN = 1;
public static int deviceCount = 0;
    private DsBus(){}
    public static DsBus getInstance(){
        return new DsBus();
    }
    public int nextBus(){
        deviceCount++;
        return deviceCount + DS_BUS_MIN -1; //-1 because we added a device before the device was initialized
    }
    public void resetBus(){ //remember to free existing device assignments!
        deviceCount = 0;
    }
}
