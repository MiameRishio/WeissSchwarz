package ws.client.model.card;
/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 6:33:48 PM - May 4, 2013
 * Description :
 *   Event cards act as an instant play card during your main phase which may give functions
 *   like retrievals or healing effects. Event cards have to meet color and level
 *   requirements to be played.
 */

public class EventCard extends Card {
	
	public EventCard() {
		this.setCard_type(Card.Card_Type.EVENT);
	}
}
