/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;

/**
 *
 * @author god
 */
public class PwmBus implements DataBus{
public static final int PWM_BUS_MIN = 1;
public static int deviceCount = 0;
        private PwmBus(){}
        public static PwmBus getInstance(){ //fake static.. the variables accessed are static anyway
            return new PwmBus();
        }
        public int nextBus(){
            deviceCount++;
            return deviceCount + PWM_BUS_MIN -1; //-1 because we added a device before the device was initialized
        }
        public void resetBus(){ //need to free the ports that were previously used!
            deviceCount = 0;
        }
}
