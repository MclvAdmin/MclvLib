/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device.addressing;
import org.mclv.mclvlib.exception.DuplicateIdentityException;
import java.util.Vector;
/**
 *
 * @author god
 */
public class System implements Identity{
    public static Vector systems;
    public static boolean init = false;
    public int system;
    public System(int system) throws DuplicateIdentityException{
        if(!init){
            systems = new Vector(0);
            init = true;
        }
        checkUnique(system);
        //System.addElement(new Integer(system));
        this.system = system;
    }
    public void checkUnique(int system) throws DuplicateIdentityException{
        for(int index = 0; index<systems.size(); index++){
            
        }
    }
}
