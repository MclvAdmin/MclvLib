/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.exception;

/**
 *
 * @author god
 */
public class AsymmetricalMatrixException extends Exception{
    private int width;
    private int height;
    
    public AsymmetricalMatrixException(int width, int height){
        this.width = width;
        this.height = height;
    }
    public AsymmetricalMatrixException(String message){
        super(message);
    }
    public String toString(){
        return "AsymmetricalMatrixException[Width: "+width+" Height: " +height+"]";
    }
}