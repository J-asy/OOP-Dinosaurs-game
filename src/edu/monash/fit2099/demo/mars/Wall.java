package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.dinosaurs.CapableActor;

public class Wall extends Ground {

	public Wall() {
		super('#');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
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
