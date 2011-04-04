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
    private int group;
    private int system;
    private int id;
    
    public DuplicateIdentityException(int group, int system, int id){
        this.group = group;
        this.system = system;
        this.id = id;
    }
    public DuplicateIdentityException(String message){
        super(message);
    }
    public String toString(){
        return "DuplicateIdentityException["+group+" " +system+" "+id+"]";
    }
}
