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
 * Creation Time : 6:12:22 PM - Jun 1, 2013
 * Description :
 *   
 */
public class MainMenuState implements IState {
	
	public MainMenuState(IGUIEnvironment env) {
		this.env = env;
		buttonMap.put(new Integer(NET_GAME), env.addButton(new recti(10, 210, 100, 240), null, NET_GAME, "Net Game"));
		buttonMap.put(new Integer(EDIT_DECK), env.addButton(new recti(10, 250, 100, 270), null, EDIT_DECK, "Edit Deck"));
		buttonMap.put(new Integer(EXIT_GAME), env.addButton(new recti(10, 280, 100, 310), null, EXIT_GAME, "Exit Game"));
	}
	
	/* (non-Javadoc)
	 * @see ws.client.lua.gui.GameState#enter()
	 */
	@Override
	public void enter() {
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
		env.clear();
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
	
	@Override
	public void guiEventCallback(SEvent event) {
		int event_id = event.getGUIEventCaller().getID();
		EGUI_EVENT_TYPE event_type = event.getGUIEventType();
		if (event_type == EGUI_EVENT_TYPE.EGET_BUTTON_CLICKED) {
			System.out.println("Button Click " + event_id);
			switch (event_id) {
			case NET_GAME:
			{
				FiniteStateMachine.pushState(new LanMenuState(env));
				break;
			}
			case EDIT_DECK:
			{
				// TODO
				break;
			}
			case EXIT_GAME:
				break;
			}
		}
	}
	
	// Main Menu	0x1000 ~ 0x1fff
	public final static int NET_GAME		= 0x1000;
	public final static int EDIT_DECK		= 0x1001;
	public final static int EXIT_GAME		= 0x1002;
	
	private final IGUIEnvironment env;
	private Map<Integer, IGUIButton> buttonMap = new HashMap<Integer, IGUIButton>();
}
