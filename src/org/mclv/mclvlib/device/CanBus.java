/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;

/**
 *
 * @author god
 */
public class CanBus implements DataBus{
public static final int CAN_BUS_MIN = 1;
public static int deviceCount = 0;
public static CanBus canBus = CanBus.getInstance();
        private CanBus(){}
        public static CanBus getInstance(){ //fake static.. the variables accessed are static anyway
            return new CanBus();
        }
        public int nextBus(){
            deviceCount++;
            return deviceCount + CAN_BUS_MIN -1;
        }
        public void resetBus(){
            deviceCount = 0;
        }
}
