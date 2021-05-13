package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	private int healPoints;

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}

	public boolean edibleByHerbivores(){
		return hasCapability(FoodType.HERBIVORE);
	}

	public boolean edibleByCarnivores(){
		return hasCapability(FoodType.CARNIVORE);
	}

	public void setHealPoints(int newHealPoints) {
		if (newHealPoints > 0){
			healPoints = newHealPoints;
		}
	}

	 public int getHealPoints(){
		return healPoints;
	}

}
