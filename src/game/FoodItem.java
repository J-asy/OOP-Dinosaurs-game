package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.CapableActor;
import game.dinosaurs.DinoActor;

/**
 * Base class for any item that can be picked up and dropped.
 */
public abstract class FoodItem extends PortableItem implements Food {

	private int healPoints;

	public FoodItem(String name, char displayChar) {
		super(name, displayChar);
	}

	public boolean edibleByHerbivores(){
		return hasCapability(FoodType.HERBIVORE);
	}

	public boolean edibleByCarnivores(){
		return hasCapability(FoodType.CARNIVORE);
	}

	public void setForHerbivores(){
		if (!edibleByHerbivores()) {
			addCapability(FoodType.HERBIVORE);
		}
		removeCapability(FoodType.CARNIVORE);
	}

	public void setForCarnivores(){
		if (!edibleByCarnivores()) {
			addCapability(FoodType.CARNIVORE);
		}
		removeCapability(FoodType.HERBIVORE);
	}

	public void setHealPoints(int newHealPoints) {
		if (newHealPoints > 0){
			healPoints = newHealPoints;
		}
	}

	public int getHealPoints(){
		return healPoints;
	}

	private void decrementHealPoints(int decrementBy) {
		healPoints = Math.max(healPoints - decrementBy, 0);
	}

	@Override
	public String foodName() {
		return this.name;
	}

	public boolean canEat(CapableActor capableActor){
		return capableActor.isHerbivorous() && edibleByHerbivores() ||
				capableActor.isCarnivorous() && edibleByCarnivores();
	}

	@Override
	public int eat(GameMap map, Location location, int biteSize) {
		int beforeHealPoints = healPoints;
		decrementHealPoints(biteSize);
		if (healPoints == 0) {
			location.removeItem(this);
		}
		return beforeHealPoints - getHealPoints();
	}
}
