package friendgame;
/**
 * The Player class represents the Player of the game
 */
public class Player extends Student
{
    /**
     * the Player's friends list
     */
    private Character[] friends = new Character[10];
    /**
     * the Player's exfriends list
     */
    private Character[] exFriends = new Character[100];
    /**
     * the Player's professors list
     */
    private Professor[] professors = new Professor[(int)(Math.random()*3+3)];

    /**
     * 
     * @param firstName the Player's first name
     * @param lastName the Player's last name
     */
    public Player(String firstName, String lastName)
    {
        super(firstName, lastName, 18);
    } 

    /**
     * 
     * @param firstName the Player's first name
     * @param middleName the Player's middle name
     * @param lastName the Player's last name
     */
    public Player(String firstName, String middleName, String lastName)
    {
        super(firstName, middleName, lastName, 18);
    } 

    /**
     * 
     * @return the Player's friends list
     */
    public Character[] getFriends()
    {
        return friends;
    }
    /**
     * adds a Character to the Player's friends list
     * @param c the Character to befriend
     */
    public void addFriend(Character c)
    {
        int i = 0;
        while(friends[i] != null)
            i ++;
        friends[i] = c;
    }
    /**
     * 
     * @return the number of friends the Player has
     */
    public int countFriends()
    {
        int i = 0;
        for(int x = 0; x < friends.length; x ++)
        {
            if(friends[x] != null) 
                i ++;
        }
        return i;
    }

    /**
     * 
     * @return the Player's professors list
     */
    public Professor[] getProfessors()
    {
        return professors;
    }
    /**
     * assigns professors to the Player
     * @param p the array of professors to assign to the player
     */
    public void assignProfessors(Professor[] p)
    {
        professors = p;
    }
    /**
     * @return the String representation 
     */
    public String toString()
    {
        return getFirstName() + " " + getLastName();
    }

    /**
     * 
     * @param c the Character to check
     * @return whether the given Character is in the Player's friends list
     */
    public boolean inFriends(Character c)
    {
        for (Character character : friends) {
            if(c == character)
                return true;
        }
        return false;
    }

    /**
     * 
     * @param c the Character to check
     * @return whether the given Character is in the Player's ex friends list
     */
    public boolean inExFriends(Character c)
    {
        for (Character character : exFriends) {
            if(c == character)
                return true;
        }
        return false;
    }

    /**
     * adds a Character to the Player's exfriends list
     * @param c the Character to add
     */
    public void addExFriend(Character c)
    {
        int i = 0;
        while(exFriends[i] != null)
            i ++;
        exFriends[i] = c;
    }
}
