package ws.client.logic.rule;

import ws.client.model.player.Player;

/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 7:51:41 PM - May 5, 2013
 * Description :
 *   
 */
public class GameRule {
	
	public GameRule() {
		
	}
	
	public void init(Player[] players) {
		this.m_Players = players;
		m_turns = new Turn[players.length];
		for (int i = 0; i < players.length; i++) {
			m_turns[i] = new Turn();
		}
		for (int i = 0; i < players.length; i++) {
			int j = (i + 1) % players.length;
			m_turns[i].activePlayer = m_Players[i];
			m_turns[i].nextTurn = m_turns[j];
		}
		m_CurTurn = m_turns[0];
	}
	
	public Player getActivePlayer() {
		return m_CurTurn.activePlayer;
	}
	
	public void nextTurn() {
		m_CurTurn = m_CurTurn.nextTurn;
	}
	
	class Turn {
		public Player activePlayer = null;
		public Turn nextTurn = null;
	}
	private Player[] m_Players = null;
	private Turn[] m_turns;
	private Turn m_CurTurn = null;
}
