/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.Timer;
import org.mclv.mclvlib.config.*;
import org.mclv.mclvlib.*;
import java.util.*;

/**
 *
 * @author god
 */
public class Jaguar extends MotorController{ //Due to name of class, must use fully qualified names with regard to wpi Jaguar
    public Object failOverDeviceInstance;
    public int failOverBus;
    public boolean failOverActive = false;
    public boolean failOverInit = false;
    public Jaguar(){
        if(DeviceConfig.CANJag){
            instanceBus = CanBus.getInstance().nextBus();
            failOverBus = PwmBus.getInstance().nextBus();
            try{
                deviceInstance = new edu.wpi.first.wpilibj.CANJaguar(instanceBus, ControlMode.kVoltage);
            }
            catch(CANTimeoutException e){
                Debug.output("Jaguar constructor: CANJaguar timeout on bus", new Integer(instanceBus), DebugConfig.deviceDebug);
            }
            failOverDeviceInstance = new edu.wpi.first.wpilibj.Jaguar(failOverBus);
            failOverInit = true;
        }
        else{
            instanceBus = PwmBus.getInstance().nextBus();
            failOverDeviceInstance = null;
            deviceInstance = new edu.wpi.first.wpilibj.Jaguar(instanceBus);
        }
    }
    public void run(){
        timeStart = Timer.getFPGATimestamp();
        while(!isInterrupted){
            assign();
            try{
            Thread.sleep(DeviceConfig.jaguarDelay); //I hope this isn't a problem 
            }
            catch(InterruptedException e){
                isInterrupted = true;
            }
        }
        //assign(lastAssign()); DO SOMETHING?
        while(isInterrupted){
            /*try{
                Thread.sleep(DeviceConfig.jaguarDelay); //I hope this isn't a problem 
            }
            catch(InterruptedException e){
                //Thread.yield(); //this is a placeholder for more complex action
            }*/
            if(motorSafe){
                isInterrupted = false;   
            }
            else{
                if(checkMotorSafe() == null){
                    motorSafe = true;
                }
                else if(checkMotorSafe().equals(new Boolean(true))){
                    motorSafe = true;
                }
                else{ // this can be ambigous, make sure to change if any more possible returns
                    motorSafe = false;
                }
            }
        }
    }    
    public void start(){
    } //monitor object starts here as well as 
    public void addNextAssign(double assignVal){ //Remember to manage the past assign values properly when modes are changed!
        nextAssignVal = assignVal;
        newAssign = true;
    }
    public void assign(){
        if(!failOverInit){
            if(newAssign){
                newAssign = false;
                ((edu.wpi.first.wpilibj.Jaguar) deviceInstance).set(nextAssignVal);
            }
            else{
                ((edu.wpi.first.wpilibj.Jaguar) deviceInstance).set(lastAssign());
            }
        }
        else{
            if(!failOverActive){
                if(newAssign){
                    newAssign = false;
                    try{
                        ((edu.wpi.first.wpilibj.CANJaguar) deviceInstance).setX(nextAssignVal);
                    }
                    catch(CANTimeoutException e){
                        Debug.output("Jaguar.assign: CANJaguar timeout on bus " + new Integer(instanceBus).toString() + " switching to pwm", null, DebugConfig.criticalDebug);
                        failOverActive = true;
                    }
                }
                else{
                    try{
                        ((edu.wpi.first.wpilibj.CANJaguar) deviceInstance).setX(lastAssign());
                    }
                    catch(CANTimeoutException e){
                        Debug.output("Jaguar.assign: CANJaguar timeout on bus " + new Integer(instanceBus).toString() + " switching to pwm", null, DebugConfig.criticalDebug);
                        failOverActive = true;
                    }
                }
            }
            else{
                if(newAssign){
                    newAssign = false;
                    ((edu.wpi.first.wpilibj.Jaguar) failOverDeviceInstance).set(nextAssignVal);
                }
                else{
                    ((edu.wpi.first.wpilibj.Jaguar) failOverDeviceInstance).set(lastAssign());
                }
            }
        }
    }
    public double lastAssign(){// hardware specific get (get(), getX());
        double getVal = 0;
        if(DeviceConfig.CANJag){
            if(failOverActive && failOverDeviceInstance != null){
                getVal = ((edu.wpi.first.wpilibj.Jaguar) failOverDeviceInstance).get();
            }
            else if(!failOverActive){
                try{
                    getVal = ((edu.wpi.first.wpilibj.CANJaguar) deviceInstance).getX();
                }
                catch(CANTimeoutException e){
                    Debug.output("Jaguar.lastAssign: CANJaguar timeout on bus " + new Integer(instanceBus).toString() + " switching to pwm", null, DebugConfig.criticalDebug);
                    failOverActive = true;
                }
            }
            else{
                //throw exception for having active fail over status but no initilization
            }
        }
        else{
            getVal = ((edu.wpi.first.wpilibj.Jaguar) deviceInstance).get();
        }
        return getVal;
    }
    public Object checkMotorSafe(){ //safety check for buses that support checking, i.e. CAN
       Object returnObject;
       if(!failOverInit){ //This means that the motor is configged via PWM and therefore cannot be safety checked 
           returnObject = null;
       }
       else{ //means CAN was configured @ start
           if(!failOverActive){
               returnObject = new Boolean(CANSafeCheck());
           }
           else{
               returnObject = null; //This means that the CAN connection was dropped and PWM mode was enabled. Alternatively, this could just mean that there is a problem with the controller and should be deemed unsafe. Check with some tests
           }
       }
       
       if(monitorSafe = false){
           returnObject = new Boolean(false);
       }
       return returnObject;
    };
    private boolean CANSafeCheck(){
        boolean isSafe = true;
        try{
            if(((edu.wpi.first.wpilibj.CANJaguar) deviceInstance).getFirmwareVersion() == 0){
            failOverActive = true;
            checkMotorSafe();
            }
            
            if(true){ //This needs some monitor feedback for motors
                
            }
        }
        catch(CANTimeoutException e){
            failOverActive = true; //enable failover
            checkMotorSafe(); //rerun checks
        }
        return isSafe;
    }
    public void setMode(String mode){
        if(DeviceConfig.CANJag){
            
        }
        else{
            if(mode.equals("driveSafe")){
                
            }
            Debug.output("Jaguar.setMode: Cannot set Jaguar mode for Jaguar with PWM connectivity", null, DebugConfig.deviceDebug);
        }
    }
}