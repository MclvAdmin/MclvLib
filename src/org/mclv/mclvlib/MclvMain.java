package org.mclv.mclvlib;

/*
 * StartApplication.java
 *
 */
import javax.microedition.midlet.MIDlet; //DAMMIT MICRO EDITION, DAMN YOU!
import javax.microedition.midlet.MIDletStateChangeException;
import org.mclv.mclvlib.input.Input;
import java.util.Vector;
/**
 * The startApp method of this class is called by the VM to start the
 * application.
 * 
 * The manifest specifies this class as MIDlet-1, which means it will
 * be selected for execution.
 */
public class MclvMain extends MIDlet {
public static Hardware hardwareInstance;
public static Input inputInstance;
public static Vector parentThreads;
    protected void startApp() throws MIDletStateChangeException {
        parentThreads = new Vector(0);
        hardwareInstance = new Hardware();
        inputInstance = new Input();
        parentThreads.addElement(new Thread(hardwareInstance)); //this is all you need to start
        ((Thread) parentThreads.lastElement()).start();
       parentThreads.addElement(new Thread(inputInstance));
        ((Thread) parentThreads.lastElement()).start();
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
    }
    
}
