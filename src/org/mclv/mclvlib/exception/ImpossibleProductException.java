/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.exception;

/**
 *
 * @author god
 */
public class ImpossibleProductException extends Exception{
    private int heightA;
    private int heightB;
    
    public ImpossibleProductException(int heightA, int heightB){
        this.heightA = heightA;
        this.heightB = heightB;
    }
    public ImpossibleProductException(String message){
        super(message);
    }
    public String toString(){
        return "AsymmetricalMatrixException[heightA: "+heightA+" heightB: " +heightB+"]";
    }
}
