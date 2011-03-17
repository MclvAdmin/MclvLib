/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;

/**
 *
 * @author god
 */
public abstract class MotorController extends Thread{
    public Object deviceInstance;
    public Object failOverDeviceInstance;
    public double timeStart;
    public int instanceBus;
    public abstract void start(); //monitor object starts here
    public abstract void assign(double assignVal);
    public abstract double lastAssign(); // hardware specific get (get(), getX());
    public abstract void setMode();
}
