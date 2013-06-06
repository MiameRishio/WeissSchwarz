package ws.client.logic.gui;

import net.sf.jirr.SEvent;

/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 6:46:03 PM - Jun 1, 2013
 * Description :
 *   
 */
public interface IState {
	/**
	 * Enter Game State
	 */
	public void enter();
	
	/**
	 * Run Game State
	 */
	public void run();
	
	/**
	 * Leave Game State
	 */
	public void leave();
	
	/**
	 * Pause Game State
	 */
	public void pause();
	
	/**
	 * Resume Game State
	 */
	public void resume();
	
	/**
	 * process GUI Event
	 * @param event_id
	 */
	public void guiEventCallback(SEvent event);
}
