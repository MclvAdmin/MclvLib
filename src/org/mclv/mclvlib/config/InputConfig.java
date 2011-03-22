/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.config;

/**
 *
 * @author god
 */
public class InputConfig {
    public static int humanAssignGroup = 1;
    public static int sensorAssignGroup = 2;
    
    public static int joyAxisCount = 6;
    public static int joyAxisMin = 1;
    public static int joyButtonCount = 12;
    public static int joyButtonMin = 1;
    public static int joySystems = 2;
    public static int joyMappedAxis = 2;
    public static int driverInputs = 2;
    public static int coInputs = 2;
    public static boolean driverUniformInput = true;
    public static boolean coDriverUniformInput = false;
    public static String driverInputType = "atk3";
    public static int driverSystem = 0;
    public static int coSystem = 1;
    
    public static String[] driverInput(){
        String[] driverInput = new String[driverInputs];
        if(driverUniformInput == true){
            for(int index = 0; index<driverInput.length; index++){
                driverInput[index] = driverInputType;
            }
        }
        else{
           //This is not used
        }
        return driverInput;
    }
    public static String[] coDriverInput(){
        String[] coDriverInputs = new String[coInputs];
        coDriverInputs[0] = "gamepad";
        coDriverInputs[1] = "keypad";
        return coDriverInputs;
    }
}
