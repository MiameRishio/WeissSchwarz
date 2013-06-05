package ws.server.logic.desktop;

import ws.client.model.card.Card;
import ws.client.model.player.Player;

/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 5:15:42 PM - May 4, 2013
 * Description :
 *   Mainly we have three kind of events:  card event, deck event, player event
 */
public class DecktopEvent {
	/**
	 * Card Event: player discard one card
	 * @param player
	 * @param card
	 */
	void DealCardFromHandToWaitingRoom(Player player, Card card) {
		//player.discardCard(card);
	}
		
	/**
	 * Deck Event: player draws one card
	 * @param player
	 * @param card
	 */
	void DealCardFromDeckToHand(Player player, Card card) {
		//player.drawCard(card);
	}
	
	/**
	 * Deck Event: shuffle deck
	 * @param player
	 */
	void DealShuffleDeck(Player player) {
		
	}
	
	/**
	 * Show hand to opponent player
	 * @param player
	 * @param opponent_player
	 */
	void ShowHand(Player player, Player opponent_player) {
		
	}
	
	/**
	 * Show deck to opponent player
	 * @param player
	 * @param opponentPlayer
	 */
	void ShowDeck(Player player, Player opponent_player) {
		
	}
	
	/**
	 * Exchange Team member
	 * @param player
	 * @param other_player
	 */
	void ExchagePlayer(Player player, Player other_player) {
		
	}
}
