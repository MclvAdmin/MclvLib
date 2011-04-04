/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device.addressing;
import org.mclv.mclvlib.device.*;
import org.mclv.mclvlib.math.*;
/**
 *
 * @author god
 */
public class Map { //will map an input to a hardware output or sequence of equations that are in turn mapped to hardware. This is entirely passive and does not carry out those actions.
    InputElement input; //This is where data actually comes from
    Address hardwareAddress; //This is where the final actions will be sent.
    DataSet functions; //This is what happens in between
    public Map(Address hardwareAddress, DataSet functions, InputElement input){
        this.hardwareAddress = hardwareAddress;
        this.input = input;
        this.functions = functions;
    }
}
