package ws.client.model.player;

import java.util.Random;

import ws.client.model.card.Card;

/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group Author :
 * MiameRishio Creation Time : 11:49:58 PM - May 4, 2013
 * Description :
 *   1: Deck
 *   2: Level Area
 *   3: Clock Area
 *   4: Stock Area
 *   5: Climax Area
 *   6: Stage Position(contains center stage and back stage)
 *   7: Waiting Room
 *   8: Memory Room
 */
public class Player {
	
	/**
	 * @return the hand
	 */
	public Card[] getHand() {
		return hand;
	}
	/**
	 * @param hand the hand to set
	 */
	public void setHand(Card[] hand) {
		this.hand = hand;
	}
	/**
	 * @return the deck
	 */
	public Card[] getDeck() {
		return deck;
	}
	/**
	 * @param deck the deck to set
	 */
	public void setDeck(Card[] deck) {
		this.deck = deck;
	}
	/**
	 * @return the level_area
	 */
	public Card[] getLevel_area() {
		return level_area;
	}
	/**
	 * @param level_area the level_area to set
	 */
	public void setLevel_area(Card[] level_area) {
		this.level_area = level_area;
	}
	/**
	 * @return the clock_area
	 */
	public Card[] getClock_area() {
		return clock_area;
	}
	/**
	 * @param clock_area the clock_area to set
	 */
	public void setClock_area(Card[] clock_area) {
		this.clock_area = clock_area;
	}
	/**
	 * @return the stock_area
	 */
	public Card[] getStock_area() {
		return stock_area;
	}
	/**
	 * @param stock_area the stock_area to set
	 */
	public void setStock_area(Card[] stock_area) {
		this.stock_area = stock_area;
	}
	/**
	 * @return the climax_area
	 */
	public Card[] getClimax_area() {
		return climax_area;
	}
	/**
	 * @param climax_area the climax_area to set
	 */
	public void setClimax_area(Card[] climax_area) {
		this.climax_area = climax_area;
	}
	/**
	 * @return the center_stage
	 */
	public Card[] getCenter_stage() {
		return center_stage;
	}
	/**
	 * @param center_stage the center_stage to set
	 */
	public void setCenter_stage(Card[] center_stage) {
		this.center_stage = center_stage;
	}
	/**
	 * @return the back_stage
	 */
	public Card[] getBack_stage() {
		return back_stage;
	}
	/**
	 * @param back_stage the back_stage to set
	 */
	public void setBack_stage(Card[] back_stage) {
		this.back_stage = back_stage;
	}
	/**
	 * @return the waiting_room
	 */
	public Card[] getWaiting_room() {
		return waiting_room;
	}
	/**
	 * @param waiting_room the waiting_room to set
	 */
	public void setWaiting_room(Card[] waiting_room) {
		this.waiting_room = waiting_room;
	}
	/**
	 * @return the memory_room
	 */
	public Card[] getMemory_room() {
		return memory_room;
	}
	/**
	 * @param memory_room the memory_room to set
	 */
	public void setMemory_room(Card[] memory_room) {
		this.memory_room = memory_room;
	}
	
	public void init() {
		// TODO
		hand = new Card[DECK_SIZE];
		deck = new Card[DECK_SIZE];
		level_area = new Card[DECK_SIZE];
		clock_area = new Card[DECK_SIZE];
		stock_area = new Card[DECK_SIZE];
		climax_area = new Card[DECK_SIZE];
		center_stage = new Card[DECK_SIZE];
		back_stage = new Card[DECK_SIZE];
		waiting_room = new Card[DECK_SIZE];
		memory_room = new Card[DECK_SIZE];
	}
	
	// Card Operations
	
	// Deck Operations
	public void drawCard() {
		// TODO
	}
	
	public void shuffleDeck() {
		Random rnd = new Random();
		for (int i = 1; i < DECK_SIZE; i++) {
			// swap cards
			int j = Math.abs(rnd.nextInt()) % i;
			Card tmpCard = deck[i];
			deck[i] = deck[j];
			deck[j] = tmpCard;
		}
	}
	
	public void showDeck() {
		// TODO
	}
	
	public void reverseDeck() {
		// TODO
	}
	
	// Player Operations
	
	public void assertPlayerDataMissing() {
		
	}
	
	private Card[] hand = null;
	private Card[] deck = null;
	private Card[] level_area = null;
	private Card[] clock_area = null;
	private Card[] stock_area = null;
	private Card[] climax_area = null;
	private Card[] center_stage = null;
	private Card[] back_stage = null;
	private Card[] waiting_room = null;
	private Card[] memory_room = null;
	
	private final static int DECK_SIZE = 50;
}
