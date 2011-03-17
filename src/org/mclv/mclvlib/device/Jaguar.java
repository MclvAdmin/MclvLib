/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import org.mclv.mclvlib.config.*;
import org.mclv.mclvlib.*;

/**
 *
 * @author god
 */
public class Jaguar extends MotorController{
    //public Object deviceInstance;
    public Object failOverDeviceInstance;
    public double timeStart;
    public int instanceBus;
    public Jaguar(){
        if(DeviceConfig.CANJag){
            int busAssign = CanBus.getInstance().nextBus();
            try{
            deviceInstance = new edu.wpi.first.wpilibj.CANJaguar(busAssign, ControlMode.kVoltage);
            }
            catch(CANTimeoutException e){
            Debug.output("Jaguar constructor: CANJaguar timeout on bus", new Integer(busAssign), DebugConfig.deviceDebug);
            }
        }
        else{
            
        }
    }
    public void run(){
        
    }    
    public void start(){
    } //monitor object starts here as well as 
    public void assign(double assignVal){
    }
    public double lastAssign(){
    }// hardware specific get (get(), getX());
    public void setMode(){
        
    }
}
