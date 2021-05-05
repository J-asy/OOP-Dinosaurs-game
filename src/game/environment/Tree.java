package game.environment;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.utility.Probability;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing terrain of Tree type.
 */
public class Tree extends Ground {
	private int age = 0;
	private ArrayList<Fruit> treeFruits = new ArrayList<>(); //fruits on the tree

	public Tree() {
		super('+');
		addCapability(TerrainType.TREE);
	}

	/**
	 * Updates the tree every turn.
	 * On every tick, the age of the Tree is increased. When the age of the Tree is over 20, it will have a 50% chance
	 * to grow a Fruit. The Fruit have a 5% chance of falling onto the ground, when that happens it is removed from the
	 * list of Fruits. The time the fallen Fruit are on the ground are checked every turn to see if it reached 15, if
	 * yes, it will rot and be removed from the Location.
	 * @param location the current Location of the Tree
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		if (displayChar == 'T' && age > 20){

			//get the list of items at the current location
			//if the item is a Fruit, get the groundTime, if > 15, remove from location(rots)
			List<Item> itemAtLocation = location.getItems();
			for (int i = 0; i < itemAtLocation.size(); i++) {
				if (itemAtLocation.get(i) instanceof Fruit){
					int groundTime = ((Fruit) itemAtLocation.get(i)).getGroundTime();
					if (groundTime == 15){
						location.removeItem(itemAtLocation.get(i));
					}
				}
			}

			if (Probability.generateProbability(0.5f)){
				Fruit fruit = new Fruit(displayChar);
				EcoPoints.incrementEcoPoints(1);
				treeFruits.add(fruit);
			}

			//check if the fruit will fall and set the item as portable and add to location, removed from treeFruits
			if (treeFruits.size() > 0){
				for (int i = 0; i < treeFruits.size(); i++){
					if (Probability.generateProbability(0.05f)) {
						location.addItem(treeFruits.get(i));
						treeFruits.remove(i);
					}
				}
			}
		}
	}

	/**
	 * Removes the first Fruit in list.
	 *
	 * @return the removed Fruit
	 */
	public Fruit decrementTreeItem(){
		Fruit fruit = null;
		if (treeFruits.size()>0) {
			fruit = treeFruits.remove(0);
		}
		return fruit;
	}

	/**
	 * Removes the first Fruit in the List without returning anything.
	 */
	public void removeTreeItem(){
		if (treeFruits.size()>0)
			treeFruits.remove(0);
	}

}

