package ws.client.logic.gui;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import net.sf.jirr.EEVENT_TYPE;
import net.sf.jirr.EGUI_EVENT_TYPE;
import net.sf.jirr.IEventReceiver;
import net.sf.jirr.IGUIEnvironment;
import net.sf.jirr.IrrlichtDevice;
import net.sf.jirr.SEvent;

/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group Author :
 * MiameRishio Creation Time : 9:30:44 PM - Jun 2, 2013 Description :
 * 
 */
public class GameEventReceiver extends IEventReceiver {
	public GameEventReceiver(IrrlichtDevice device) {
		this.device = device;
		this.env = device.getGUIEnvironment();
		/*
		try {
			Class<?> c = Class.forName("ws.client.lua.gui.GameGui");
			Field[] fields = c.getFields();
			for (Field f : fields) {
				System.out.println(f.getName() + " " + f.getInt(this));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public synchronized static void stateChange(IState state) {
		currentState = state;
		event_ids.clear();
		Class<?> c = state.getClass();
		Field[] fields = c.getFields();
		for (Field f : fields) {
			try {
				//System.out.println(f.getName() + " " + f.getInt(c));
				event_ids.add(new Integer(f.getInt(c)));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				
			}
		}
	}
	
	public synchronized boolean OnEvent(SEvent event) {
		if (event.getEventType() == EEVENT_TYPE.EET_GUI_EVENT) {
			//int id = event.getGUIEventCaller().getID();
			//EGUI_EVENT_TYPE egui_event_type = event.getGUIEventType();
			/*
			If a button was clicked, it could be one of 'our'
			three buttons. If it is the first, we shut down the engine.
			If it is the second, we create a little window with some
			text on it. We also add a string to the list box to log
			what happened. And if it is the third button, we create
			a file open dialog, and add also this as string to the list box.
			That's all for the event receiver.
			*/
			currentState.guiEventCallback(event);

			//if (egui_event_type == EGUI_EVENT_TYPE.EGET_BUTTON_CLICKED) {
			//	currentState.guiEventCallback(event);
			//}
		}
		return false;
	}
	
	private final IrrlichtDevice device;
	private final IGUIEnvironment env;
	private static Set<Integer> event_ids = new HashSet<Integer>();
	private static IState currentState;
}
