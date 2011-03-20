/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;
import edu.wpi.first.wpilibj.Joystick;
import org.mclv.mclvlib.config.*;
import java.util.*;
/**
 *
 * @author god
 */
public class HumanInputDevice extends InputDevice{
    /* xData structure: first element is a vector containing integer-indexed axes gathered via getRawAxis(int). The data points are doubles.
     * second element contains integer-indexed buttons, gathered via getRawButton(int). The data points are booleans.
     */
    public HumanInputDevice(String type){
        this.type = type;
        if(type.equals("atk3")){ //Could use switch based on enumeration... damn you 1.4!
            inputObject = new Joystick(DsBus.getInstance().nextBus());
        }
        else if(type.equals("twistJoy")){
            //Not sure what to do here... research!
        }
        else if(type.equals("gamepad")){
            inputObject = new Joystick(DsBus.getInstance().nextBus());
        }
        else if(type.equals("keypad")){
            //Research cypress
        }
        else{
            inputObject = null;
        }
    }
    public void run(){
        gather();
        try{
        Thread.sleep(DeviceConfig.humanInputDelay);
        }
        catch(InterruptedException e){
            //Do something
        }
    }
    public void gather(){
        //oldData = newData; //Can't clone?!
        if(newData != null){ //Clones oldData
        oldData = new Vector(0);
            for(int index = 0; index<((Vector) newData).size(); index++){
                ((Vector) oldData).addElement(((Vector) newData).elementAt(index));
            }
        }
        newData = new Vector(0);
        for(int index = 0; index<InputConfig.joyAxisCount; index ++){
            ((Vector) ((Vector) newData).elementAt(0)).addElement(new Double(((Joystick) inputObject).getRawAxis(index + InputConfig.joyAxisMin)));
        }
        
        for(int index = 0; index<InputConfig.joyButtonCount; index ++){
            ((Vector) ((Vector) newData).elementAt(1)).addElement(new Boolean(((Joystick) inputObject).getRawButton(index + InputConfig.joyButtonMin)));
        }
    }
    public void clearData(){
        newData = null;
        oldData = null;
    }
}
