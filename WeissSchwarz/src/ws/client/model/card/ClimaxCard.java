package ws.client.model.card;
/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 6:34:05 PM - May 4, 2013
 * Description :
 *   Climax cards act as a means to boost your soul and power damage output during your
 *   climax phase upon play, as means to increase soul output or resources advantage upon
 *   triggering on trigger phase and also serves as damage canceler during damage phase.
 *   Because of the usefulness of climax cards, a deck is capped at 8 climaxes.
 *   Climax card have to meet the color requirement to be played during climax phase.
 */

public class ClimaxCard extends Card {
	
	public ClimaxCard() {
		this.setCard_type(Card.Card_Type.CLIMAX);
	}
}
