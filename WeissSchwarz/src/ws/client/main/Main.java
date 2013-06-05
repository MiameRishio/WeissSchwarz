package ws.client.main;

import ws.client.logic.rule.GameRule;
import ws.client.model.player.LocalPlayer;
import ws.client.model.player.NetPlayer;
import ws.client.model.player.Player;
import ws.server.logic.desktop.Desktop;

public class Main {

	public static void main(String[] args) {
		Desktop desktop = new Desktop();
		Player p1 = new LocalPlayer();
		Player p2 = new NetPlayer();
		GameRule rule = new GameRule();
		
		
	}

}
