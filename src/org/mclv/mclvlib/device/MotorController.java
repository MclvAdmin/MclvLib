/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;

/**
 *
 * @author god
 */
public abstract class MotorController implements Runnable{
    public Object deviceInstance;
    public double timeStart;
    public int instanceBus;
    public boolean isInterrupted = false;
    public boolean newAssign = false;
    public double nextAssignVal = 0;
    public String assignOrigin; //Use to ensure safety features with driver and not with sequences
    public abstract void start(); //monitor object starts here
    public abstract void assign();
    public abstract double lastAssign(); // hardware specific get (get(), getX());
    public abstract void setMode(String mode);
    public abstract void addNextAssign(double assignVal);
}
