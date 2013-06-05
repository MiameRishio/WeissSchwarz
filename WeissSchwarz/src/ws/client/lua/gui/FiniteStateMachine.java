package ws.client.lua.gui;

import java.util.Stack;

import net.sf.jirr.IGUIEnvironment;

/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 6:19:36 PM - Jun 1, 2013
 * Description :
 *   this Finite State Machine makes sure at least one state for 
 */
public class FiniteStateMachine {
	
	public FiniteStateMachine(IGUIEnvironment env) {
		stateStack = new Stack<IState>();
		this.env = env;
		FiniteStateMachine.pushState(new MainMenuState(env));
	}
	
	public static void pushState(IState state) {
		if (stateStack.size() > 0) {
			stateStack.peek().pause();
		}
		stateStack.push(state);
		stateStack.peek().enter();
		FiniteStateMachine.stateChange();
	}
	
	public static void popState() {
		stateStack.peek().leave();
		stateStack.pop();
		if (stateStack.size() == 0) {
			assert(false);
		}
		stateStack.peek().resume();
		FiniteStateMachine.stateChange();
	}
	
	public static void pauseState() {
		if (stateStack.size() == 0) {
			assert(false);
		}
		stateStack.peek().pause();
	}
	
	public static void resumeState() {
		stateStack.peek().resume();
	}
	
	public static void run() {
		
	}
	
	private static void stateChange() {
		GameEventReceiver.stateChange(stateStack.peek());
	}
	
	public final static int MAIN_MENU_STATE = 0;
	public final static int LAN_MENU_STATE = 1;
	public final static int HOST_MENU_STATE = 2;
	public final static int GAME_STATE = 3;

	private static Stack<IState> stateStack = null;
	private final IGUIEnvironment env;

}
