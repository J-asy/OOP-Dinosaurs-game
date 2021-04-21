package game.dinosaurs;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.FollowBehaviour;
import game.actions.PlayerFeedAction;
import game.WanderBehaviour;

import java.util.ArrayList;

public abstract class DinoActor extends Actor {

    private ArrayList<Behaviour> behaviour;  // should have priority of behaviours
    private char sex;

    public DinoActor(String name, char displayChar, int hitPoints, int maxHitPoints, char sex){
        super(name, displayChar, hitPoints);
        setMaxHitPoints(maxHitPoints);
        setSex(sex);
        initializeDinoBehaviour();
    }

    private void initializeDinoBehaviour(){
        behaviour = new ArrayList<>();
        behaviour.add(new WanderBehaviour());
    }

    private void setMaxHitPoints(int newMaxHitPoints){
        if (newMaxHitPoints > 0 && newMaxHitPoints >= hitPoints){
            maxHitPoints = newMaxHitPoints;
        }
    }

    private void setSex(char dinoSex){
        char s = Character.toLowerCase(dinoSex);
        if (s == 'm'|| s == 'f'){
            sex = dinoSex;
        }
    }

    public String getName(){ return name; }

    public int getFoodLevel(){
        return hitPoints;
    }

    private void decrementFoodLevel(){
        if (getFoodLevel() > 0){
            super.hurt(1);
        }
    }

    // based on current square, determine whether there are characters worth following
    // opposite sex, then add the actions of those actors in getAllowableActions
    public Actions followWho(GameMap map){
        Location here = map.locationOf(this);

        Actions validActions = new Actions();
        for (Exit exit: here.getExits()){
            Location checkLocation = exit.getDestination();
            if (map.isAnActorAt(checkLocation)){
                Actor potentialTarget = map.getActorAt(checkLocation);

                if (potentialTarget.getClass().getName().equals(this.getClass().getName())){
                    // add condition opposite sex
                    validActions.add(new FollowBehaviour(potentialTarget).getAction(this, map));
                }

            }
        }

        return validActions;
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions list = super.getAllowableActions(otherActor, direction, map);
        list.add(new PlayerFeedAction(this));
        return list;
    }

    /**
     * FIXME: wanders around at random, or if no suitable MoveActions are available, it just stands there.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     *
     * actions received already has
     * - all actions you can do with an item you are carrying, including drop item
     * - movement actions to adjacent grounds with no actors on it - feeding
     * - what you can do to adjacent actors - attack, breed
     * - actions to do using items in current location, including pickup item, feeding on corpse
     * - skip turn
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        decrementFoodLevel();
        roarIfHungry();

        Action wander = behaviour.get(0).getAction(this, map);
        if (wander != null){
            return wander;
        }
        return new DoNothingAction();
    }

    abstract public void roarIfHungry();

}



// priority -> eat -> breed -> wander -> doNothing
// priority
// -> eat when hungry
// -> breed when not hungry
// -> eat when not hungry and food level not full
// -> wander
// -> do nothing

//    class SortHotkeysFirst implements Comparator<Action> {
//        public int compare(Action a, Action b) {
//            if (a.hotkey() != null && b.hotkey() == null)
//                return -1;
//
//            if (a.hotkey() == null && b.hotkey() != null)
//                return 1;
//
//            return 0;
//        }
//    }