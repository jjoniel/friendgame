package friendgame;

/**
 * The Patriot class represents a Person affiliated with GMU in the game
 */
public abstract class Patriot {

    /**
     * represents the name of the Patriot
     */
    private String firstName, middleName, lastName;
    /**
     * the age of the Patriot
     */
    private int age;
    /**
     * the unique identifier for the Patriot
     */
    protected String id;

    /**
     * constructs a new Patriot object with the provided first and last name and age
     * 
     * @param firstName the Patriot's first name
     * @param lastName  the Patriot's last name
     * @param age       the Patriot's age
     */
    public Patriot(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * constructs a new Patriot object with the provided first, middle, and last
     * names and age
     * 
     * @param firstName  the Patriot's first name
     * @param middleName the Patriot's middle name
     * @param lastName   the Patriot's last name
     * @param age        the Patriot's age
     */
    public Patriot(String firstName, String middleName, String lastName, int age) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * 
     * @return the first name of the Patriot
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @return the middle name of the Patriot
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * 
     * @return the last name of the Patriot
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @return the age of the Patriot
     */
    public int getAge() {
        return age;
    }

    /**
     * 
     * @return the ID of the Patriot
     */
    public String getID() {
        return id;
    }

    /**
     * 
     * @return the String representation of the Patriot
     */
    public String toString() {
        String out = firstName + " ";
        if (middleName != null)
            out += middleName + " ";
        out += lastName + ", Age: ";
        out += age;

        return out;
    }

    /**
     * 
     * @return a String representing the Patriot's full name
     */
    public String nameString() {
        if (middleName == null)
            return firstName + " " + lastName;
        else
            return firstName + " " + middleName + " " + lastName;
    }

    /**
     * sets the ID of the Patriot
     */
    public abstract void setID();

}
