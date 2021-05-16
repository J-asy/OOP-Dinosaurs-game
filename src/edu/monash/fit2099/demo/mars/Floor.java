package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Ground;
import game.dinosaurs.CapableActor;


public class Floor extends Ground {

	public Floor() {
		super('.');
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
