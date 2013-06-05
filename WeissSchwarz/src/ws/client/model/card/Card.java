package ws.client.model.card;
/**
 * Team : Eight Dos and Don'ts & Philosophy Discussion Group
 * Author : MiameRishio
 * Creation Time : 5:08:32 PM - May 5, 2013
 * Description :
 *   1. Card Name
 *   2. English Name: Default
 *   3. Level
 *   4. Cost
 *   5. Counter-Attack Icon
 *   6. Trigger Icon
 *   7. Text
 *   8. Power
 *   9. Soul Points
 *   10. Trait
 *   11. Color: yellow(speed), green(power), red(technique), blue(advantage)
 */

public class Card {	
	
	public enum Card_Type {
		CHARACTER, EVENT, CLIMAX,
	}
	
	public enum Trait {
		Fairy
	}
	public enum Color {
		YELLOW, GREEN, RED, BLUE,
	}
	
	public enum Card_Status {
		STANDING_UP, RESTING, KNOCKED_OUT
	}
	
	/**
	 * @return the card_name
	 */
	public String getCard_name() {
		return card_name;
	}
	
	/**
	 * @param card_name the card_name to set
	 */
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	
	/**
	 * @return the card_type
	 */
	public Card_Type getCard_type() {
		return card_type;
	}
	
	/**
	 * @param card_type the card_type to set
	 */
	public void setCard_type(Card_Type card_type) {
		this.card_type = card_type;
	}
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	/**
	 * @return the counter_attack_icon
	 */
	public boolean isCounter_attack_icon() {
		return counter_attack_icon;
	}
	
	/**
	 * @param counter_attack_icon the counter_attack_icon to set
	 */
	public void setCounter_attack_icon(boolean counter_attack_icon) {
		this.counter_attack_icon = counter_attack_icon;
	}
	
	/**
	 * @return the tigger_icon
	 */
	public boolean isTigger_icon() {
		return tigger_icon;
	}
	
	/**
	 * @param tigger_icon the tigger_icon to set
	 */
	public void setTigger_icon(boolean tigger_icon) {
		this.tigger_icon = tigger_icon;
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}
	
	/**
	 * @param power the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}
	
	/**
	 * @return the soul_point
	 */
	public int getSoul_point() {
		return soul_point;
	}
	
	/**
	 * @param soul_point the soul_point to set
	 */
	public void setSoul_point(int soul_point) {
		this.soul_point = soul_point;
	}
	
	/**
	 * @return the traits
	 */
	public Trait[] getTraits() {
		return traits;
	}
	
	/**
	 * @param traits the traits to set
	 */
	public void setTraits(Trait[] traits) {
		this.traits = traits;
	}
	
	/**
	 * @return the color
	 */
	public Card.Color getColor() {
		return color;
	}
	
	/**
	 * @param color the color to set
	 */
	public void setColor(Card.Color color) {
		this.color = color;
	}
	
	public void resetCardStatus() {
		card_status = Card_Status.STANDING_UP;
	}
	
	public void assertCardDataMissing() {
		assert(card_name != null);
		assert(card_type != null);
		assert(level != -1);
		assert(cost != -1);
		assert(power != -1);
		assert(soul_point != -1);
		assert(traits != null);
		assert(color != null);
		// TODO write log
	}
	
	private String card_name = null;
	private Card_Type card_type = null;
	private int level = -1;
	private int cost = -1;
	private boolean counter_attack_icon = false;
	private boolean tigger_icon = false;
	private String text = null;
	private int power = -1;
	private int soul_point = -1;
	private Trait[] traits = null;
	private Color color = null;
	
	private Card_Status card_status = null;
}
