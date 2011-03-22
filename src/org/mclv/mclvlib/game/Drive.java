/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.game;
import org.mclv.mclvlib.input.Input;
import org.mclv.mclvlib.device.*;
import org.mclv.mclvlib.*;
import org.mclv.mclvlib.exception.*;
import java.util.Vector;

/**
 *
 * @author god
 */
public class Drive implements Runnable{
    public boolean driveSafe = true;
        public void run(){
            while(true){
                if(driveSafe = true){
                    mapAndPass();
                }
            }
        }
        public void mapAndPass(){
            for(int index = 0; index<Input.newMappedData.size(); index++){
               Input.newMappedData.elementAt(index);
               Integer group = ((Integer) ((Vector) Input.newMappedData.elementAt(0)).elementAt(0));
               int system = ((Integer) ((Vector) Input.newMappedData.elementAt(0)).elementAt(1)).intValue();
               Double data = ((Double) Input.newMappedData.elementAt(1));
               Vector unformattedAssign = new Vector(0);
               for(int systemIndex = 0; systemIndex<system; systemIndex++){
                   unformattedAssign.addElement(null);
               }
               unformattedAssign.addElement(data);
               unformattedAssign.addElement(group);
               try{
                MclvMain.hardwareInstance.assignFormatAndAdd(unformattedAssign);
               }
               catch(BadAssignFormatException e){
                   //Do something
               }
            }
            Input.newMappedData.removeAllElements(); //THIS IS SOOO SKETCHY! CHANGE SOON
        }
}
