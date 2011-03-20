/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib;
import java.util.*;
import org.mclv.mclvlib.config.*;
import org.mclv.mclvlib.device.*;
/**
 *
 * @author god
 */
public class Hardware implements Runnable{
    public Vector hardwareContainer;
    public Vector hardwareThreads;
    public Vector assignRequests; //This is a vector of vectors. each element of the top vector is a vector of four elements; Integer group, Integer system, Integer address, Object assignment (this depends on the assign group, for motors it is a Double etc)
    public Hardware(){ //Same as old 'init' takes info from config file (no argument)
        hardwareContainer = new Vector(0);
        hardwareThreads = new Vector(0);
        assignRequests = new Vector(0);
        //Create drive assignments. Try a nice, freeform list of hardware elements sorted only by assign group and then assign values by checking each element's public system and address val. Could be easier on the eyes!
        for(int groupIndex = 0; groupIndex<HardwareConfig.maxGroups() - HardwareConfig.minGroups() +1; groupIndex++){
            /*
            hardwareContainer.addElement(new Vector(0));
            if(groupIndex == HardwareConfig.motorAssignGroup){
                for(int motorSystem = 0; motorSystem<HardwareConfig.motorGroupSystems; motorSystem++){
                ((Vector) hardwareContainer.elementAt(groupIndex)).addElement(new Vector(0));
                    if(HardwareConfig.symmetricalDrive){
                        for(int motorAddress = 0; motorAddress<HardwareConfig.driveSystemMotorCount; motorAddress++){
                            ((Vector) ((Vector) hardwareContainer.elementAt(groupIndex)).elementAt(motorSystem)).addElement(new Jaguar(groupIndex, motorSystem, motorAddress)); //create a jaguar
                            hardwareThreads.addElement(new Thread(((Jaguar) ((Vector) ((Vector) hardwareContainer.elementAt(groupIndex)).elementAt(motorSystem)).elementAt(motorAddress)))); //create a thread for that jaguar in a separate thread collection
                            ((Thread) hardwareThreads.lastElement()).start(); //start that thread. may move to run() or a thread management method
                        }
                    }
                    else{
                        for(int motorAddress = 0; motorAddress<HardwareConfig.driveSystemMotorCount; motorAddress++){
                            ((Vector) ((Vector) hardwareContainer.elementAt(groupIndex)).elementAt(motorSystem)).addElement(new Jaguar(groupIndex, motorSystem, motorAddress)); //create a jaguar
                            hardwareThreads.addElement(new Thread(((Jaguar) ((Vector) ((Vector) hardwareContainer.elementAt(groupIndex)).elementAt(motorSystem)).elementAt(motorAddress)))); //create a thread for that jaguar in a separate thread collection
                            ((Thread) hardwareThreads.lastElement()).start(); //start that thread. may move to run() or a thread management method
                        }
                    }
                }
                ((Vector) hardwareContainer.elementAt(groupIndex)).addElement(new Integer(HardwareConfig.motorAssignGroup));
            }
            else if(groupIndex == HardwareConfig.pneumaticAssignGroup){
                ((Vector) hardwareContainer.elementAt(groupIndex)).addElement(new Vector(0));
            }*/ //Old, complex method
            if(groupIndex == HardwareConfig.motorAssignGroup -HardwareConfig.minGroups()){
                for(int motorSystem = 0; motorSystem<HardwareConfig.motorGroupSystems; motorSystem++){
                    if(HardwareConfig.symmetricalDrive){
                        for(int motorAddress = 0; motorAddress<HardwareConfig.driveSystemMotorCount; motorAddress++){
                            hardwareContainer.addElement(new HardwareElement(groupIndex + HardwareConfig.minGroups(), motorSystem, motorAddress, new Jaguar()));   
                        }
                    }
                    else{
                        for(int motorAddress = 0; motorAddress<HardwareConfig.driveInit()[motorSystem]; motorAddress++){
                            hardwareContainer.addElement(new HardwareElement(groupIndex + HardwareConfig.minGroups(), motorSystem, motorAddress, new Jaguar()));
                        }
                    }
                }
            }
        }
    }
    public void run(){
        while(true){
            passAssign();
            try{
                Thread.sleep(HardwareConfig.hardwareDelay);
            }
            catch(InterruptedException e){
                //Do something.
            }
        }
    }
    public void passAssign(){ //the actual hardware element is responsible for sending the final assign to the corresponding piece of hardware, this just gives it new info based on what has been received and safety-checked.
        for(int assignIndex = 0; assignIndex<assignRequests.size(); assignIndex++){
            int group = ((Integer) ((Vector) assignRequests.elementAt(assignIndex)).elementAt(0)).intValue();
            int system = ((Integer) ((Vector) assignRequests.elementAt(assignIndex)).elementAt(1)).intValue();
            int address = ((Integer) ((Vector) assignRequests.elementAt(assignIndex)).elementAt(2)).intValue();
            Object assign = ((Vector) assignRequests.elementAt(assignIndex)).elementAt(3);
            
            for(int hardIndex = 0; hardIndex<hardwareContainer.size(); hardIndex++){
                if((((HardwareElement) hardwareContainer.elementAt(hardIndex)).group == group) && (((HardwareElement) hardwareContainer.elementAt(hardIndex)).system == system) && (((HardwareElement) hardwareContainer.elementAt(hardIndex)).address == address)){
                    //assignForGroup(group, assign, ((HardwareElement) hardwareContainer.elementAt(hardIndex)));
                    checkActiveTypeAndAssign(assign, ((HardwareElement) hardwareContainer.elementAt(hardIndex)));
                }
            }
        }
        assignRequests.removeAllElements();
    }
    private void checkActiveTypeAndAssign(Object assign, HardwareElement hardwareElement){
        if(hardwareElement.device.getClass() == Jaguar.class){
            ((Jaguar) hardwareElement.device).addNextAssign(((Double) assign).doubleValue());
        }
        else{
            
        }
    }
    public void assignFormatAndAdd(Vector assignUnform){ //takes a wide range of assignment formats and reforms/adds to assignRequests.
        
    }
}
