package friendgame;

/**
 * The Character class represents non-player Characters whom the Player of the
 * game can befriend
 */
public class Character extends Student {

    /**
     * the Personality of the Character object that determines its behavior
     */
    private Personality p;

    /**
     * constructs a new Character object with the provided first and last name, age,
     * and personality
     * 
     * @param firstName the Character's first name
     * @param lastName  the Character's last name
     * @param age       the Character's age
     * @param p         the Character's Personality
     */
    public Character(String firstName, String lastName, int age, Personality p) {
        super(firstName, lastName, age);
        this.p = p;
    }

    /**
     * constructs a new Character object with the provided first, middle, and last
     * names, age, and personality
     * 
     * @param firstName  the Character's first name
     * @param middleName the Character's middle name
     * @param lastName   the Character's last name
     * @param age        the Character's age
     * @param p          the Character's Personality
     */
    public Character(String firstName, String middleName, String lastName, int age, Personality p) {
        super(firstName, middleName, lastName, age);
        this.p = p;
    }

    /**
     * 
     * @return the Personality of the Character
     */
    public Personality getPersonality() {
        return p;
    }

    /**
     * @return a String representation of the Character
     */
    @Override
    public String toString() {
        return super.toString() + ", Personality: " + p;
    }

    /**
     * used to decide the introductory string for the Character
     * 
     * @return an int representing the Personality of the Character
     */
    public int introString() {
        switch (p) {
            case LOYAL:
                return 0;
            case SIMP:
                return 1;
            case SNAKE:
                return 2;
            default:
                return -1;
        }
    }

    /**
     * decides whether the Character befriends the given Player
     * 
     * @param player Player for the Character to befriend
     * @return whether or not the character befriended the Player
     */
    public boolean friendZone(Player player) {
        if (p == Personality.SNAKE) {
            if (Math.random() > 0.10 - (player.getFriends().length / 15)) {
                player.addFriend(this);
                return true;
            } else
                return false;
        } else if (p == Personality.LOYAL) {
            if (Math.random() > 0.50 - (player.getFriends().length / 20)) {
                player.addFriend(this);
                return true;
            } else
                return false;
        } else if (p == Personality.SIMP) {
            if (Math.random() > 0.30 - (player.getFriends().length / 15)) {
                player.addFriend(this);
                return true;
            } else
                return false;
        }
        return false;
    }

    /**
     * decides whether the Character befriends the given mutual friend Player
     * 
     * @param player mutual friend Player for the Character to befriend
     * @return whether or not the character befriended the mutual friend Player
     */
    public boolean mutualFriendZone(Player player) {
        if (p == Personality.SNAKE) {
            if (Math.random() > 0.05 - (player.getFriends().length / 10)) {
                player.addFriend(this);
                return true;
            } else
                return false;
        } else if (p == Personality.LOYAL) {
            if (Math.random() > 0.30 - (player.getFriends().length / 15)) {
                player.addFriend(this);
                return true;
            } else
                return false;
        } else if (p == Personality.SIMP) {
            if (Math.random() > 0.10 - (player.getFriends().length / 10)) {
                player.addFriend(this);
                return true;
            } else
                return false;
        }
        return false;
    }
}
