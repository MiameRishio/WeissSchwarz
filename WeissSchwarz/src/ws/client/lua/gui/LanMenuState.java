package ws.client.lua.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jirr.EGUI_EVENT_TYPE;
import net.sf.jirr.IGUIButton;
import net.sf.jirr.IGUIEnvironment;
import net.sf.jirr.SEvent;
import net.sf.jirr.recti;

/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 7:21:17 PM - Jun 1, 2013
 * Description :
 *   
 */
public class LanMenuState implements IState {

	public LanMenuState(IGUIEnvironment env) {
		this.env = env;
		buttonMap.put(new Integer(CANCEL), env.addButton(new recti(10, 280, 100, 310), null, CANCEL, "Cancel"));
	}
	/* (non-Javadoc)
	 * @see ws.client.lua.gui.GameState#enter()
	 */
	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see ws.client.lua.gui.GameState#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see ws.client.lua.gui.GameState#leave()
	 */
	@Override
	public void leave() {
		for (Entry<Integer, IGUIButton> entry : buttonMap.entrySet()) {
			entry.getValue().setVisible(false);
		}
	}

	/* (non-Javadoc)
	 * @see ws.client.lua.gui.GameState#pause()
	 */
	@Override
	public void pause() {
		for (Entry<Integer, IGUIButton> entry : buttonMap.entrySet()) {
			entry.getValue().setVisible(false);
		}
	}

	/* (non-Javadoc)
	 * @see ws.client.lua.gui.GameState#resume()
	 */
	@Override
	public void resume() {
		for (Entry<Integer, IGUIButton> entry : buttonMap.entrySet()) {
			entry.getValue().setVisible(true);
		}
	}

	/* (non-Javadoc)
	 * @see ws.client.lua.gui.IState#guiEventCallback(net.sf.jirr.SEvent)
	 */
	@Override
	public void guiEventCallback(SEvent event) {
		int event_id = event.getGUIEventCaller().getID();
		EGUI_EVENT_TYPE event_type = event.getGUIEventType();
		if (event_type == EGUI_EVENT_TYPE.EGET_BUTTON_CLICKED) {
			System.out.println("Button Click " + event_id);
			switch (event_id) {
			case CANCEL:
			{
				FiniteStateMachine.popState();
				break;
			}
			}
		}
	}

	
	// Lan Menu		0x2000 ~ 0x2fff
	public final static int NICK_NAME		= 0x2000;
	public final static int JOIN_IP			= 0x2001;
	public final static int JOIN_PORT		= 0x2002;
	public final static int CANCEL			= 0x2003;
	
	private final IGUIEnvironment env;
	private Map<Integer, IGUIButton> buttonMap = new HashMap<Integer, IGUIButton>();
}
