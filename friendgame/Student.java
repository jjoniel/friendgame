package friendgame;

public class Student extends Patriot {

    /**
     * 
     * @param firstName the Student's first name
     * @param lastName  the Student's last name
     * @param age       the Student's age
     */
    public Student(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        setID();
    }

    /**
     * 
     * @param firstName the Student's first name
     * @param lastName  the Student's middle name
     * @param lastName  the Student's last name
     * @param age       the Student's age
     */
    public Student(String firstName, String middleName, String lastName, int age) {
        super(firstName, middleName, lastName, age);
        setID();
    }

    /**
     * sets the ID of the Studnet
     */
    public void setID() {
        id = "S";
        for (int x = 0; x < 10; x++)
            id += (int) (Math.random() * 10);
    }
}
