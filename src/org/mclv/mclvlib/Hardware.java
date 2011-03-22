/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib;
import java.util.*;
import org.mclv.mclvlib.config.*;
import org.mclv.mclvlib.device.*;
import org.mclv.mclvlib.exception.*;
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
            if(groupIndex == HardwareConfig.motorAssignGroup -HardwareConfig.minGroups()){
                for(int motorSystem = 0; motorSystem<HardwareConfig.motorGroupSystems; motorSystem++){
                    if(HardwareConfig.symmetricalDrive){
                        for(int motorAddress = 0; motorAddress<HardwareConfig.driveSystemMotorCount; motorAddress++){
                            hardwareContainer.addElement(new HardwareElement(groupIndex + HardwareConfig.minGroups(), motorSystem, motorAddress, new Jaguar()));
                            hardwareThreads.addElement(new Thread(((Jaguar) ((HardwareElement) hardwareContainer.lastElement()).device)));
                            ((Thread) hardwareThreads.lastElement()).start();
                        }
                    }
                    else{
                        for(int motorAddress = 0; motorAddress<HardwareConfig.driveInit()[motorSystem]; motorAddress++){
                            hardwareContainer.addElement(new HardwareElement(groupIndex + HardwareConfig.minGroups(), motorSystem, motorAddress, new Jaguar()));
                            hardwareThreads.addElement(new Thread(((Jaguar) ((HardwareElement) hardwareContainer.lastElement()).device)));
                            ((Thread) hardwareThreads.lastElement()).start();
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
            if(!(assign == null)){ //only bother if not nulltype
                for(int hardIndex = 0; hardIndex<hardwareContainer.size(); hardIndex++){
                    if((((HardwareElement) hardwareContainer.elementAt(hardIndex)).group == group) && (((HardwareElement) hardwareContainer.elementAt(hardIndex)).system == system) && (((HardwareElement) hardwareContainer.elementAt(hardIndex)).address == address)){
                        //assignForGroup(group, assign, ((HardwareElement) hardwareContainer.elementAt(hardIndex)));
                        checkActiveTypeAndAssign(assign, ((HardwareElement) hardwareContainer.elementAt(hardIndex)));
                    }
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
    public void assignFormatAndAdd(Vector assignUnform) throws BadAssignFormatException{ //takes a wide range of assignment formats and reforms/adds to assignRequests.
        if(assignUnform.lastElement().getClass().equals(Integer.class)){ //means system-wide assignments with the Integer telling you assign group
            int systems = systemCount(HardwareConfig.motorAssignGroup);
            int assignGroup = ((Integer) assignUnform.lastElement()).intValue();
            for(int assignIndex = 0; assignIndex<assignUnform.size() -1; assignIndex++){ //maps directly to a system
                if(assignIndex + 1 > systems){
                    throw new BadAssignFormatException(assignIndex + 1);
                }
                else{
                    for(int address = 0; address<memberCount(assignGroup, assignIndex); address++){
                    assignRequests.addElement(new Vector(0));
                    ((Vector) assignRequests.lastElement()).addElement(new Integer(assignGroup));
                    ((Vector) assignRequests.lastElement()).addElement(new Integer(assignIndex)); 
                    ((Vector) assignRequests.lastElement()).addElement(new Integer(address));
                    ((Vector) assignRequests.lastElement()).addElement(assignUnform.elementAt(assignIndex)); 
                    }
                }
            }
        }
    }
    public void assignFromMapAdd(Vector assign){
        
    }
    public int systemCount(int assignGroup){
        int groupMembers = 0;
        for(int hardIndex = 0; hardIndex<hardwareContainer.size(); hardIndex++){
            if(((HardwareElement) hardwareContainer.elementAt(hardIndex)).group == assignGroup){
                groupMembers++;
            }
        }
        return groupMembers;
    }
    public int memberCount(int assignGroup, int system){
        int systemMembers = 0;
        for(int hardIndex = 0; hardIndex<hardwareContainer.size(); hardIndex++){
            if(((HardwareElement) hardwareContainer.elementAt(hardIndex)).group == assignGroup && ((HardwareElement) hardwareContainer.elementAt(hardIndex)).system == system){
                systemMembers++;
            }
        }
        return systemMembers;
    }
    public boolean uniqueAddress(int assignGroup, int system, int address){
        boolean unique = true;
        for(int hardIndex = 0; hardIndex<hardwareContainer.size(); hardIndex++){
            if(((HardwareElement) hardwareContainer.elementAt(hardIndex)).group == assignGroup && ((HardwareElement) hardwareContainer.elementAt(hardIndex)).system == system && ((HardwareElement) hardwareContainer.elementAt(hardIndex)).address == address){
                unique = false;
            }
        }
        return unique;
    }
}
