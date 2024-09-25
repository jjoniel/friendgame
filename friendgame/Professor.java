package friendgame;

public class Professor extends Patriot {

    /**
     * the course taught by the Professor
     */
    private String course;
    /**
     * the karma level of the Professor
     */
    private int karma;

    /**
     * 
     * @param firstName the Professor's first name
     * @param lastName  the Professor's last name
     * @param age       the Professor's age
     */
    public Professor(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        setID();
        setCourse();
    }

    /**
     * 
     * @param firstName  the Professor's first name
     * @param middleName the Professor's middle name
     * @param lastName   the Professor's last name
     * @param age        the Professor's age
     */
    public Professor(String firstName, String middleName, String lastName, int age, int salary) {
        super(firstName, middleName, lastName, age);
        setID();
        setCourse();
    }

    /**
     * sets the ID of the Professor
     */
    public void setID() {
        id = "P";
        for (int x = 0; x < 10; x++)
            id += (int) (Math.random() * 10);
    }

    /**
     * 
     * @return the String representation of the course taught and the Professor
     */
    public String courseString() {
        return course + " - " + nameString();
    }

    /**
     * 
     * @return the course taught by the Professor
     */
    public String getCourse() {
        return course;
    }

    /**
     * sets the course taught by the Professor
     */
    public void setCourse() {
        String alphabet = "ABCDEFGHILMNOPRSTU";
        String courseID = "";

        int len = (int) (Math.random() * 2 + 2);
        for (int x = 0; x < len; x++) {
            courseID += alphabet.charAt((int) (Math.random() * 18));
        }
        courseID += (int) (Math.random() * 4 + 1);
        courseID += (int) (Math.random() * 89 + 10);
        course = courseID;
    }

    /**
     * 
     * @param karma the karma value
     */
    public void setKarma(int karma) {
        this.karma = karma;
    }

    public int getKarma() {
        return karma;
    }

}
