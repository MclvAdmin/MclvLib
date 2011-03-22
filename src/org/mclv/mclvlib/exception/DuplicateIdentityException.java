/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.exception;

/**
 *
 * @author god
 */
public class DuplicateIdentityException extends Exception{
    private int intError;
    
    public DuplicateIdentityException(int intErrorNo){
        intError = intErrorNo;
    }
    public DuplicateIdentityException(String message){
        super(message);
    }
    public String toString(){
        return "DuplicateIdentityException["+intError+"]";
    }
}
