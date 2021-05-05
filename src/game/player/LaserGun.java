package game.player;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Class representing the Laser Gun (weapon user to kill Stegosaur).
 */
public class LaserGun extends WeaponItem {
    /**
     * Constructor.
     */
    public LaserGun() {
        super("laserGun", 'z', 50, "zaps");
    }
}
