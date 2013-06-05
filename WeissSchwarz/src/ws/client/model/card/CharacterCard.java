package ws.client.model.card;
/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 6:33:24 PM - May 4, 2013
 * Description :
 *   Character cards form the base of the gameplay, serving as your means of damaging the
 *   opponent and subsequently defeating them. Character cards have several features which
 *   include the level and cost on the top left, soul trigger on the top right, effects,
 *   color, power, soul output and traits on the bottom of the card. To play a character, 
 *   you have to meet the color requirement (applies only to card level 1 and above), the
 *   cost requirement and the level requirement.
 */

public class CharacterCard extends Card {
	
	public CharacterCard() {
		this.setCard_type(Card.Card_Type.CHARACTER);
	}
}
