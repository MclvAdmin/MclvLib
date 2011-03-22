/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.device.addressing;
import org.mclv.mclvlib.exception.DuplicateIdentityException;
/**
 *
 * @author god
 */
public interface Identity {
   public void checkUnique(int checkVal) throws DuplicateIdentityException;
}
