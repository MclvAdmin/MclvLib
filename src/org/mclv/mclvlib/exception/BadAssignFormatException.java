/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.exception;

/**
 *
 * @author god
 */
public class BadAssignFormatException extends Exception{
    private int intError;
    
    public BadAssignFormatException(int intErrorNo){
        intError = intErrorNo;
    }
    public BadAssignFormatException(String message){
        super(message);
    }
    public String toString(){
        return "BadAssignFormatException["+intError+"]";
    }
}
