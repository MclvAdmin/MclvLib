/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;
import java.util.Vector;
/**
 *
 * @author god
 */
public abstract class InputDevice implements Runnable{
    public String type;
    public Vector hardwareMap;
    public Object inputObject;
    public Object newData;
    public Object oldData;
    public int dataBus;
    public abstract void gather();
    public abstract void clearData();
}
