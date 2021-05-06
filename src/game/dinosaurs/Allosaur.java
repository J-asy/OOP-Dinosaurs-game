package game.dinosaurs;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.attack.AttackAction;
import game.breed.BreedingAction;

import java.util.HashMap;

public class Allosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.ALLOSAUR;
    private HashMap<Stegosaur,Integer> attackedStegos = new HashMap<>();

    public Allosaur(DinoCapabilities sex, Boolean isMatured) {
        super(DINO_TYPE, sex, isMatured);
    }

    public Allosaur(Boolean isMatured) {
        super(DINO_TYPE, isMatured);
    }

    @Override
      void initializeCapabilities(){
        super.initializeCapabilities();
        addCapability(DinoCapabilities.CARNIVORE);
        addCapability(DinoCapabilities.CAN_ATTACK);
    }

    public void addAttackedStego(Stegosaur stegosaur) {
        attackedStegos.put(stegosaur, 20);
    }

    public boolean hasAttackedStegosaur(Stegosaur stegosaur) {
        return attackedStegos.containsKey(stegosaur);
    }

    public void decrementAttackedPeriod(Stegosaur stegosaur) {
        attackedStegos.put(stegosaur, attackedStegos.get(stegosaur) - 1);
    }

    public int getAttackedPeriod(Stegosaur stegosaur){
        return attackedStegos.get(stegosaur);
    }

    public void removeAttackedStego(Stegosaur stegosaur){
        attackedStegos.remove(stegosaur);
    }

//    @Override
    // allosaur can be attacked by player so add attack action
//    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//        Actions allowableActions = new Actions();
//        allowableActions.add(new AttackAction(this));
//        allowableActions.add(new BreedingAction(this));
//
//        return allowableActions;
//    }


}
