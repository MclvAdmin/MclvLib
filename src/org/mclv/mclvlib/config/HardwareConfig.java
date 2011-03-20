/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.config;
import java.util.Vector;
/**
 *
 * @author god
 */
public class HardwareConfig {
public static final long hardwareDelay = 20;
public static final int motorAssignGroup = 1; //new sorting scheme: first layer is assign group, which means that you can assign in the same way to all of these elements. Second layer is address, which is the same as last year's assign type within a group.  system addressing starts from 0.
public static final int motorGroupSystems = 2; //first system is drive other is arm
public static final int pneumaticAssignGroup = 2;
public static final int pneumaticGroupSystems = 1; //claw.
public static final int otherAssignGroup = 3;
public static final int DRIVE_MOTOR_SYSTEMS = 2;
public static final boolean symmetricalDrive = true; //Tired of caps!
public static final int driveSystemMotorCount = 2;
//public static final String driveMode = "tank"; This, as well as a controller to motor map will go in DriveConfig

    public static int[] driveInit(){ //This is where you customize how many motors in each system if you have asymmetrical drive (or something we haven't heard of).
        int[] driveMotorInit = new int[DRIVE_MOTOR_SYSTEMS];    
        driveMotorInit[0] = 2;
        driveMotorInit[1] = 2;
        return driveMotorInit;
    }
    
    public static int minGroups(){
        Vector groups = new Vector(0);
        int calc = motorAssignGroup;
        groups.addElement(new Integer(motorAssignGroup));
        groups.addElement(new Integer(pneumaticAssignGroup));
        for(int index = 0; index<groups.size()-1; index++){
            calc = Math.min(((Integer) groups.elementAt(index)).intValue(), ((Integer) groups.elementAt(index + 1)).intValue());
        }
        return calc;
    }
    public static int maxGroups(){
        Vector groups = new Vector(0);
        int calc = motorAssignGroup;
        groups.addElement(new Integer(motorAssignGroup));
        groups.addElement(new Integer(pneumaticAssignGroup));
        for(int index = 0; index<groups.size()-1; index++){
            calc = Math.max(((Integer) groups.elementAt(index)).intValue(), ((Integer) groups.elementAt(index + 1)).intValue());
        }
        return calc;
    }
    /*public static int minAddress(int group){
        
    }
    public static int maxAddress(int group){
        
    }*/
}
