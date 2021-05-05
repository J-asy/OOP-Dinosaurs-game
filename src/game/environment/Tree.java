package game.environment;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.Probability;
import java.util.ArrayList;
import java.util.List;

public class Tree extends CapableGround {
	private int age = 0;
	private ArrayList<Fruit> treeFruits = new ArrayList<>(); //fruits on the tree

	public Tree() {
		super('+');
		addCapability(TerrainType.TREE);
	}

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


//			List<Item> itemAtLocation = location.getItems();
//			for (Item item : itemAtLocation) {
//				if (item instanceof Fruit) {
//					int groundTime = ((Fruit) item).getGroundTime();
//					if (groundTime == 15) {
//						location.removeItem(item);
//					}
//				}
//			}

			if (Probability.generateProbability(0.5f)){
				Fruit fruit = new Fruit(displayChar);
//				fruit.addCapability(FoodType.HERBIVORE);
				EcoPoints.incrementEcoPoints(1);
				treeFruits.add(fruit);
			}

			//check if the fruit will fall and set the item as portable and add to location, removed from treeFruits
			if (treeFruits.size() > 0){
				for (int i = 0; i < treeFruits.size(); i++){
					if (Probability.generateProbability(0.05f)) {
//						treeFruits.get(i).setPortability(true);
						location.addItem(treeFruits.get(i));
						treeFruits.remove(i);
					}
				}
			}
		}
	}

	public Fruit decrementTreeItem(){
		Fruit fruit = null;
		if (treeFruits.size()>0) {
			fruit = treeFruits.remove(0);
		}
		return fruit;
	}

	public void removeTreeItem(){
		if (treeFruits.size()>0)
			treeFruits.remove(0);
	}

	public int getTreeFruitsSize() {
		return treeFruits.size();
	}
}
