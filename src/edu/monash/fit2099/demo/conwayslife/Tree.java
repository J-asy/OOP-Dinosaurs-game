package edu.monash.fit2099.demo.conwayslife;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.CapableActor;

public class Tree extends Ground {
	private int age = 0;

	public Tree() {
		super('+');
		addCapability(Status.ALIVE);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}

	@Override
	public boolean canLayEggHere(CapableActor capableActor) {
		return false;
	}

	@Override
	public boolean canBreedHere(CapableActor capableActor) {
		return false;
	}
}
