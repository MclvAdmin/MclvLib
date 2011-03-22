/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device;

/**
 *
 * @author god
 */
public class InputElement extends Element {
        public InputElement(int group, int system, int address, Object device){
            this.group = group;
            this.system = system;
            this.address = address;
            this.device = device;
        }
}
