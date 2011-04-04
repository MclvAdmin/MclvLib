/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.math;
import java.util.Vector;
/**
 *
 * @author god
 */
public class DataSet {
public Vector dataPoints; //this collection may only hold Points. order is unimportant
public Vector dataQueue;
private boolean frozen = false;
    public DataSet(){
        dataPoints = new Vector(0);
        dataQueue = new Vector(0);
    }
    public synchronized void addData(Point p){
        if(!frozen){
            dataPoints.addElement(p);
        }
        else{ //data added when frozen goes into a queue and is held there until the dataSet is unfrozen as to not affect computations
            dataQueue.addElement(p);
        }
    }
    public synchronized void freeze(){ //This is called when a function is being perfomed on the dataSet
        frozen = true;
    }
    public synchronized void unfreeze(){
        while(dataQueue.size()>0){
            dataPoints.addElement(dataQueue.lastElement());
            dataQueue.removeElementAt(dataQueue.size() - 1);
        }
        frozen = false;
    }
}
