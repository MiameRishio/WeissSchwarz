package ws.client.logic.gui;

import net.sf.jirr.E_DRIVER_TYPE;
import net.sf.jirr.IGUIEnvironment;
import net.sf.jirr.IVideoDriver;
import net.sf.jirr.IrrlichtDevice;
import net.sf.jirr.Jirr;
import net.sf.jirr.SColor;
import net.sf.jirr.dimension2di;

/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 6:19:51 PM - Jun 1, 2013
 * Description :
 *   
 */

public class GameGui {
	static {
		System.loadLibrary("irrlicht_wrap");
	}

	public static void main(String[] args) {
		// create device and exit if creation failed
		device = Jirr.createDevice(E_DRIVER_TYPE.EDT_OPENGL, new dimension2di(640, 480), 32, false, false, false, null);
		if (device == null) {
			System.exit(1); // could not create selected driver.
		}
		
		/* The creation was successful, now we set the event receiver and
		store pointers to the driver and to the gui environment. */
		//MyEventReceiver receiver = new MyEventReceiver();
		device.setWindowCaption("WeissSchwarz");
		driver = device.getVideoDriver();
		env = device.getGUIEnvironment();

		GameEventReceiver receiver = new GameEventReceiver(device);
		device.setEventReceiver(receiver);
		
		fsm = new FiniteStateMachine(env);
		
		while (device.run()) {
			if (device.isWindowActive()) {
				driver.beginScene(true, true, new SColor(0, 200, 200, 200));
				env.drawAll();
				driver.endScene();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		device.drop();
		System.exit(0);
	}
	
	private static IrrlichtDevice device;
	private static IVideoDriver driver;
	private static IGUIEnvironment env;
	private static FiniteStateMachine fsm;
}