package ws.server.logic.desktop;

import ws.client.logic.rule.GameRule;
import ws.client.model.player.Player;

/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 7:47:38 PM - May 5, 2013
 * Description :
 *   
 */

public class Desktop {
	
	public Desktop() {
		gameRule = new GameRule();
	}
	
	public void init(Player[] players) {
		gameRule.init(players);
		for (int i = 0; i < players.length; i++) {
			players[i].init();
		}
	}

	private Player[] players = null;
	private GameRule gameRule = null;
}
