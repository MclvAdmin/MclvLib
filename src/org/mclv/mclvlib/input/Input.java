/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.input;
import org.mclv.mclvlib.config.*;
import org.mclv.mclvlib.device.*;
import java.util.Vector;
/**
 *
 * @author god
 */
public class Input implements Runnable{ //Much like hardware class, but manages threads for all input devices as opposed to assignable devices. This includes sensors, tcp/ip, and human input devices
    public static Vector inputDevices;
    public static Vector inputThreads;
    public static Vector freshData;
    public static Vector newMappedData;
        public Input(){ //gets info from config. Maybe it holds maps?
            inputDevices = new Vector(0);
            inputThreads = new Vector(0);
            freshData = null;
            newMappedData = null;
            //Configure human input
            String[] driverInput = InputConfig.driverInput();
            String[] coInput = InputConfig.coDriverInput();
            for(int index = 0; index<driverInput.length; index++){
                Vector hardMap = new Vector(0);
                hardMap.addElement(new Integer(HardwareConfig.motorAssignGroup)); //Quick, bad map format (a group and a system)
                hardMap.addElement(new Integer(index));
                
                inputDevices.addElement(new InputElement(InputConfig.humanAssignGroup, InputConfig.driverSystem, index, new HumanInputDevice(driverInput[index], hardMap))); //0th system devoted to driver
                inputThreads.addElement(new Thread(((HumanInputDevice) ((InputElement) inputDevices.lastElement()).device)));
                ((Thread) inputThreads.lastElement()).start();
            }
            for(int index = 0; index<coInput.length; index++){
                Vector hardMap = null;
                inputDevices.addElement(new InputElement(InputConfig.humanAssignGroup, InputConfig.coSystem, index, new HumanInputDevice(driverInput[index], null)));
                inputThreads.addElement(new Thread(((HumanInputDevice) ((InputElement) inputDevices.lastElement()).device)));
                ((Thread) inputThreads.lastElement()).start();
            }
        }
        public void run(){
            updateMappedData();
        }
        public void updateMappedData(){
            for(int inputIndex = 0; inputIndex<inputDevices.size(); inputIndex++){
                if(((InputElement) inputDevices.elementAt(inputIndex)).group == InputConfig.humanAssignGroup){ //data is mappable
                    HumanInputDevice mappableDevice = ((HumanInputDevice) ((InputElement) inputDevices.elementAt(inputIndex)).device);
                    if(mappableDevice.hardwareMap != null){
                        if(mappableDevice.newData != null){
                            newMappedData.addElement(new Vector(0));
                            ((Vector) newMappedData.lastElement()).addElement(mappableDevice.hardwareMap);
                            ((Vector) newMappedData.lastElement()).addElement(((Vector) ((Vector) mappableDevice.newData).elementAt(0)).elementAt(InputConfig.joyMappedAxis)); //This is a Double.
                        }
                    }
                }
            }
        }
        public static InputElement getElement(int group, int system, int address){
            InputElement element = null;
            for(int inputIndex = 0; inputIndex<inputDevices.size(); inputIndex++){
                int devGroup = ((InputElement) inputDevices.elementAt(inputIndex)).group;
                int devSystem = ((InputElement) inputDevices.elementAt(inputIndex)).system;
                int devAddress = ((InputElement) inputDevices.elementAt(inputIndex)).address;
                if(devGroup == group && devSystem == system && devAddress == address){
                    element = ((InputElement) inputDevices.elementAt(inputIndex));
                }
            }
            if(element == null){
                 //Throw exception if none found
            }
            
            return element;
        }
}
