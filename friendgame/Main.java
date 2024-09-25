package friendgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * the possible firstName and middleNames
     */
    private static String[] firstNames = new String[1000];
    /**
     * the possibe lastNames
     */
    private static String[] lastNames = new String[1000];
    /**
     * the population of Characters in the game
     */
    private static Character[] characters = new Character[100];
    /**
     * the number of possible quiz questions
     */
    private static final int NUM_QUESTIONS = 16;
    /**
     * the list of questions
     */
    private static String[] questions = new String[NUM_QUESTIONS];
    /**
     * the list of answers
     */
    private static String[] answers = new String[NUM_QUESTIONS];
    /**
     * the Player of the game
     */
    private static Player player;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Scanner infile = new Scanner(new File("fnames.txt"));
        Scanner infile2 = new Scanner(new File("lnames.txt"));
        for (int x = 0; x < firstNames.length; x++) {
            firstNames[x] = infile.next();
            lastNames[x] = infile2.next();
        }
        infile.close();
        infile2.close();

        Scanner infile3 = new Scanner(new File("QA.txt"));
        for (int x = 0; x < NUM_QUESTIONS; x++) {
            questions[x] = infile3.nextLine();
            answers[x] = infile3.nextLine();
        }

        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter your full name: ");
        String inName = scnr.nextLine();
        String[] name = inName.split(" ");
        while (name.length > 3 || name.length < 2) {
            System.out.println("Invalid Name Format");
            System.out.print("Enter your full name: ");
            inName = scnr.nextLine();
            name = inName.split(" ");
        }

        if (name.length == 2)
            player = new Player(name[0], name[1]);
        else
            player = new Player(name[0], name[1], name[2]);

        initializeProfessors();
        initializeCharacters();

        printlnSlow("Welcome " + player + ".", true);
        TimeUnit.MILLISECONDS.sleep(500);
        printlnSlow("Your schedule for the semester is as follows:", true);
        for (Professor professor : player.getProfessors()) {
            printlnSlow(professor.courseString(), false);
            TimeUnit.MILLISECONDS.sleep(500);
        }

        printSlow("\nYour goal is to make 10 friends by the end of the semester. ", true);
        printlnSlow("You have 15 weeks.", true);
        printSlow("Are you ready to begin? ", true);
        scnr.next();
        printSlow("Lol. ", true);
        printlnSlow("You thought you had a choice.", true);
        printlnSlow("Good luck.\n", true);

        scnr.nextLine();
        System.out.print("\033\143");

        for (int x = 0; x < 15; x++) {
            printlnSlow("Week " + (x + 1), true);
            boolean exec = false;
            TimeUnit.MILLISECONDS.sleep(500);
            printlnSlow("You currently have " + player.countFriends() + " friends.", false);
            TimeUnit.MILLISECONDS.sleep(500);
            if (player.countFriends() >= 10) {
                System.out.println("YOU WIN!");
                System.exit(0);
            }
            friendProcess(scnr, exec);

            if (Math.random() < 0.40) {
                Professor actor = player.getProfessors()[(int) (Math.random() * player.getProfessors().length)];
                int karma = actor.getKarma();
                if (karma < -90) {
                    printlnSlow("Your " + actor.getCourse() + " professor, Professor " + actor.getLastName()
                            + " honor coded you. You have been suspended for two weeks.", false);
                    scnr.nextLine();
                    x++;
                    printlnSlow("Week " + (x + 1), true);
                    printlnSlow("You are still suspended.", false);
                    scnr.nextLine();
                    exec = true;
                    continue;
                } else if (karma < -70) {
                    printSlow("You have been assigned a lot of work in " + actor.getCourse() + " by Professor "
                            + actor.getLastName() + ". ", false);
                    TimeUnit.MILLISECONDS.sleep(500);
                    printlnSlow("You spend your whole week doing coursework.", false);
                    scnr.nextLine();
                    exec = true;
                    continue;
                } else if (karma < 50) {
                    printSlow("You have been given a pop quiz. Answer the following question: ", false);
                    int qN = (int) (Math.random() * NUM_QUESTIONS);
                    printlnSlow(questions[qN], true);
                    printSlow("Your answer: ", true);
                    String ans = scnr.next();
                    if (ans.toLowerCase().equals(answers[qN].toLowerCase())) {
                        printlnSlow("You are correct!", true);
                        scnr.nextLine();
                        exec = true;
                    } else {
                        printSlow("Incorrect answer. ", false);
                        TimeUnit.MILLISECONDS.sleep(500);
                        printlnSlow("You should spend the rest of this week studying harder.\n", false);
                        scnr.nextLine();
                        exec = true;
                        continue;
                    }
                }
                System.out.println();
            }

            double chance = 0.25;
            while (Math.random() > chance) {
                exec = true;
                int cN = (int) (Math.random() * characters.length);
                Character c = characters[cN];
                while (player.inExFriends(c) || player.inFriends(c)) {
                    cN = (int) (Math.random() * characters.length);
                    c = characters[cN];
                }
                introduceCharacter(c, scnr);
                System.out.println();
                chance *= 1.7;
            }
            if (!exec)
                scnr.nextLine();
        }
        printlnSlow("At the end of the semester you have " + player.countFriends() + " friends.", false);
        scnr.close();
        if (player.countFriends() >= 10) {
            System.out.println("YOU WIN!");
            System.exit(0);
        } else {
            System.out.println("YOU LOSE!");
            System.exit(0);
        }
    }

    /**
     * 
     * @param toPrint the String to print
     * @param bold    whether to bold the text
     * @throws InterruptedException
     */
    public static void printSlow(String toPrint, boolean bold) throws InterruptedException {
        if (bold) {
            for (char c : toPrint.toCharArray()) {
                System.out.print("\033[1m" + c + "\033[0m");
                TimeUnit.MILLISECONDS.sleep(25);
                if (c == '.' || c == '!' || c == '?')
                    TimeUnit.MILLISECONDS.sleep(500);
                else if (c == ',')
                    TimeUnit.MILLISECONDS.sleep(250);
            }
        } else {
            for (char c : toPrint.toCharArray()) {
                System.out.print(c);
                TimeUnit.MILLISECONDS.sleep(25);
                if (c == '.' || c == '!' || c == '?')
                    TimeUnit.MILLISECONDS.sleep(500);
                else if (c == ',' || c == ':')
                    TimeUnit.MILLISECONDS.sleep(250);
            }
        }
    }

    /**
     * 
     * @param toPrint the String the print
     * @param bold    whether to bold the text
     * @throws InterruptedException
     */
    public static void printlnSlow(String toPrint, boolean bold) throws InterruptedException {
        if (bold) {
            for (char c : toPrint.toCharArray()) {
                System.out.print("\033[1m" + c + "\033[0m");
                TimeUnit.MILLISECONDS.sleep(25);
                if (c == '.' || c == '!' || c == '?')
                    TimeUnit.MILLISECONDS.sleep(500);
                else if (c == ',' || c == ':')
                    TimeUnit.MILLISECONDS.sleep(250);
            }
        } else {
            for (char c : toPrint.toCharArray()) {
                System.out.print(c);
                TimeUnit.MILLISECONDS.sleep(25);
                if (c == '.' || c == '!' || c == '?')
                    TimeUnit.MILLISECONDS.sleep(500);
                else if (c == ',' || c == ':')
                    TimeUnit.MILLISECONDS.sleep(250);
            }
        }
        System.out.println();
    }

    /**
     * initializes the Player's professors
     */
    public static void initializeProfessors() {
        for (int x = 0; x < player.getProfessors().length; x++) {
            String rFirst = firstNames[(int) (Math.random() * firstNames.length)];

            String rMiddle = null;
            if (Math.random() > 0.5) {
                rMiddle = firstNames[(int) (Math.random() * firstNames.length)];
            }

            String rLast = lastNames[(int) (Math.random() * lastNames.length)];

            int rAge = (int) (Math.random() * 50 + 24);

            int rSalary = ((int) (Math.random() * 1000 + (rAge * 20))) * 100;

            player.getProfessors()[x] = new Professor(rFirst, rMiddle, rLast, rAge, rSalary);
            player.getProfessors()[x].setKarma((int) (Math.random() * 200 - 100));
        }
    }

    /**
     * initializes the Characters in the game
     */
    public static void initializeCharacters() {
        for (int x = 0; x < characters.length; x++) {
            String rFirst = firstNames[(int) (Math.random() * firstNames.length)];

            String rMiddle = null;
            if (Math.random() > 0.5) {
                rMiddle = firstNames[(int) (Math.random() * firstNames.length)];
            }

            String rLast = lastNames[(int) (Math.random() * lastNames.length)];

            int rAge = (int) (Math.random() * 50 + 24);

            int pChoice = (int) (Math.random() * 3);
            Personality rP = Personality.values()[pChoice];

            characters[x] = new Character(rFirst, rMiddle, rLast, rAge, rP);
        }
    }

    /**
     * processes the Player's friends on each week change
     * 
     * @param scnr the Scanner used to process the input
     * @param e
     * @throws InterruptedException
     */
    public static void friendProcess(Scanner scnr, boolean e) throws InterruptedException {
        Character[] pFriends = player.getFriends();
        for (int x = 0; x < pFriends.length; x++) {
            if (pFriends[x] == null)
                continue;
            Character curr = pFriends[x];
            if (curr.getPersonality() == Personality.SNAKE) {
                if (Math.random() > 0.2) {
                    printlnSlow(curr.nameString() + " is a snake and has unfriended you.", false);
                    pFriends[x] = null;
                    player.addExFriend(curr);
                    scnr.nextLine();
                    e = true;
                }

            }
            if (curr.getPersonality() == Personality.SIMP) {
                if (Math.random() > 0.5)
                    for (int y = 0; y < pFriends.length; y++) {
                        if (pFriends[y] == null)
                            continue;
                        Character curr2 = pFriends[y];
                        if (curr2.getPersonality() == Personality.SIMP && x != y) {
                            printlnSlow("Your friends " + curr.nameString() + " and " + curr2.nameString()
                                    + " are now a couple. They do not have time to be your friends.", false);
                            scnr.nextLine();
                            pFriends[x] = null;
                            pFriends[y] = null;
                            player.addExFriend(curr);
                            player.addExFriend(curr2);
                            break;
                        }
                    }
            }
            if (curr.getPersonality() == Personality.LOYAL) {
                if (Math.random() > 0.3) {
                    int cN = (int) (Math.random() * characters.length);
                    Character c = characters[cN];
                    while (player.inExFriends(c) || player.inFriends(c)) {
                        cN = (int) (Math.random() * characters.length);
                        c = characters[cN];
                    }
                    printlnSlow("Your friend " + curr.nameString() + " introduces you to " + c.nameString() + ".",
                            true);
                    printSlow("Would you like to become friends with " + c.nameString() + "? (Y/N) ", true);
                    String decision = scnr.nextLine();
                    if (decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes")) {
                        boolean outcome = c.mutualFriendZone(player);
                        if (outcome) {
                            printlnSlow(c.nameString() + " is now your friend!", false);
                            if (player.countFriends() >= 10) {
                                printlnSlow("You currently have " + player.countFriends() + " friends.", false);
                                System.out.println("YOU WIN!");
                                System.exit(0);
                            }
                        } else
                            printlnSlow(c.nameString() + " rejected your friendship.", false);
                    }
                    scnr.nextLine();
                }
            }
        }
    }

    /**
     * 
     * @param c    the Character to introduce
     * @param scnr the Scanner to process input
     * @throws InterruptedException
     */
    public static void introduceCharacter(Character c, Scanner scnr) throws InterruptedException {
        double msg = Math.random();
        switch (c.introString()) {
            case 0:
                if (msg < 0.3) {
                    printlnSlow("You see a student walking in front of you carrying some books.", false);
                    printlnSlow("'Probably just headed to class', you suppose and look away.", false);
                    printlnSlow("Then you feel a tap on your shoulder.", false);
                    printSlow("'Hey, I'm " + c.getFirstName() + ". " + c.nameString() + ". ", false);
                    printlnSlow("Whats your name?'", false);
                    printSlow("Would you like to become friends with " + c.getFirstName() + "? (Y/N) ", true);
                    String decision = scnr.nextLine();
                    if (decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes")) {
                        printlnSlow("'I'm " + player.getFirstName() + "', you respond.", false);
                        boolean outcome = c.friendZone(player);
                        if (outcome) {
                            printlnSlow(c.nameString() + " is now your friend!", false);
                            if (player.countFriends() >= 10) {
                                printlnSlow("You currently have " + player.countFriends() + " friends.", false);
                                System.out.println("YOU WIN!");
                                System.exit(0);
                            }
                        } else {
                            printlnSlow("The moment you respond, " + c.getFirstName()
                                    + "'s face falls. Just something about you seemed off.", false);
                            player.addExFriend(c);
                        }
                        scnr.nextLine();
                    } else {
                        printlnSlow("You walk off without responding. 'Creep' you think to yourself.", false);
                        player.addExFriend(c);
                    }
                } else if (msg < 0.6) {
                    printlnSlow("Professor " + player.getProfessors()[(int) (Math.random() * 3)].getLastName()
                            + " assigns you a partner assignment.", false);
                    printlnSlow("You get assigned to work with " + c.nameString() + ".", false);
                    printlnSlow("They seem hardworking. ", false);
                    printSlow("Would you like to become friends with " + c.getFirstName() + "? (Y/N) ", true);
                    String decision = scnr.nextLine();
                    if (decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes")) {
                        printlnSlow("'Hey! How do you wanna split up the work?' you ask.", false);
                        TimeUnit.MILLISECONDS.sleep(500);
                        boolean outcome = c.friendZone(player);
                        if (outcome) {
                            printlnSlow("'Let's work on it together' they respond.", false);
                            printlnSlow(c.nameString() + " is now your friend!", false);
                            if (player.countFriends() >= 10) {
                                printlnSlow("You currently have " + player.countFriends() + " friends.", false);
                                System.out.println("YOU WIN!");
                                System.exit(0);
                            }
                        } else {
                            printlnSlow("'Professor, can I work with someone else?' they yell out. You feel hurt.",
                                    false);
                            player.addExFriend(c);
                        }
                        scnr.nextLine();
                    } else {
                        printlnSlow("'Professor, can I work with someone else?' you ask? " + c.getFirstName()
                                + " begins to cry.", false);
                        player.addExFriend(c);
                    }
                } else {
                    printlnSlow("'Excuse me, is this seat taken?'", false);
                    TimeUnit.MILLISECONDS.sleep(500);
                    printlnSlow("You look up from your chair at Southside to see someone standing near your table.",
                            false);
                    TimeUnit.MILLISECONDS.sleep(500);
                    printSlow("Would you like to become friends with this stranger? (Y/N) ", true);
                    String decision = scnr.nextLine();
                    if (decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes")) {
                        printlnSlow("'Nope, feel free! What's your name?'", false);
                        TimeUnit.MILLISECONDS.sleep(500);
                        boolean outcome = c.friendZone(player);
                        if (outcome) {
                            printlnSlow("'I'm " + c.nameString() + "', they respond.", false);
                            printlnSlow(c.nameString() + " is now your friend!", false);
                            if (player.countFriends() >= 10) {
                                printlnSlow("You currently have " + player.countFriends() + " friends.", false);
                                System.out.println("YOU WIN!");
                                System.exit(0);
                            }
                        } else {
                            printlnSlow(
                                    "'Thank you!' they exclaim before you finish your sentence. They take the empty chair and carry it off to another table to sit with their group of friends.",
                                    false);
                            player.addExFriend(c);
                        }
                        scnr.nextLine();
                    } else {
                        printlnSlow("'Professor, can I work with someone else?' you ask? " + c.getFirstName()
                                + " begins to cry.", false);
                        player.addExFriend(c);
                    }
                }
                break;

            case 1:
                if (msg < 0.5) {
                    printlnSlow("You're brushing your teeth. The sink next to you is surrounded by skin care products.",
                            false);
                    printlnSlow("You turn to the side to see the creator of the mess.", false);
                    printlnSlow("'Don't mind me just trying to look my best,' they say.", false);
                    printSlow("'I'm " + c.nameString() + " from room " + (int) (Math.random() * 999 + 2000), false);
                    printlnSlow(". And you are? ", false);
                    printSlow("Would you like to become friends with " + c.getFirstName() + "? (Y/N) ", true);
                    String decision = scnr.nextLine();
                    if (decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes")) {
                        printlnSlow("'I'm " + player.getFirstName() + "', you respond.", false);
                        boolean outcome = c.friendZone(player);
                        if (outcome) {
                            printlnSlow(c.nameString() + " is now your friend!", false);
                            if (player.countFriends() >= 10) {
                                printlnSlow("You currently have " + player.countFriends() + " friends.", false);
                                System.out.println("YOU WIN!");
                                System.exit(0);
                            }
                        } else {
                            printlnSlow("As you respond " + c.getFirstName()
                                    + " turns to looks at your face. They recommend that you get a skincare routine and walk off.",
                                    false);
                            player.addExFriend(c);
                        }
                        scnr.nextLine();
                    } else {
                        printlnSlow("You splash water on your face, somewhat carelessly. " + c.getFirstName()
                                + "'s pimple cream lined faced is drenched in the process. You walk off without a word.",
                                false);
                        player.addExFriend(c);
                    }

                } else {
                    printlnSlow("You're on your way to class.", false);
                    printlnSlow("Suddenly, someone walks into you.", false);
                    printSlow("'I'm so sorry! they exclaim. I was just a bit...", false);
                    printlnSlow("distracted' they say.", false);
                    printlnSlow("'Anyways, I'm " + c.getFirstName() + ". " + c.nameString() + ". And you are?'", false);
                    printSlow("Would you like to become friends with " + c.getFirstName() + "? (Y/N) ", true);
                    String decision = scnr.nextLine();
                    if (decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes")) {
                        printSlow("'I'm " + player.getFirstName() + "'", false);
                        printlnSlow(", you respond.", false);
                        boolean outcome = c.friendZone(player);
                        if (outcome) {
                            printlnSlow(c.nameString() + " is now your friend!", false);
                            if (player.countFriends() >= 10) {
                                printlnSlow("You currently have " + player.countFriends() + " friends.", false);
                                System.out.println("YOU WIN!");
                                System.exit(0);
                            }
                        } else {
                            printlnSlow(
                                    c.getFirstName()
                                            + " rejected your friendship. They didn't like the sound of your voice.",
                                    false);
                            player.addExFriend(c);
                        }
                        scnr.nextLine();
                    } else {
                        printlnSlow("You walk off without responding. 'Wacko' you think to yourself.", false);
                        player.addExFriend(c);
                    }
                }
                break;
            case 2:
                printlnSlow("'HIII'. Someone walks up to you excitedly.", false);
                printlnSlow("'I'm " + c.nameString() + ". Do you wanna be friends??'", false);
                printSlow("Would you like to become friends with " + c.getFirstName() + "? (Y/N) ", true);
                String decision = scnr.nextLine();
                if (decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes")) {
                    printSlow("'Sure, I'm " + player.getFirstName() + "'", false);
                    TimeUnit.MILLISECONDS.sleep(500);
                    printlnSlow(", you respond.", false);
                    boolean outcome = c.friendZone(player);
                    if (outcome) {
                        printlnSlow(c.nameString() + " is now your friend!", false);
                        if (player.countFriends() >= 10) {
                            printlnSlow("You currently have " + player.countFriends() + " friends.", false);
                            System.out.println("YOU WIN!");
                            System.exit(0);
                        }
                    } else {
                        printlnSlow(c.getFirstName() + " rejected your friendship. You didn't match their vibe.",
                                false);
                        player.addExFriend(c);
                    }
                    scnr.nextLine();
                } else {
                    printlnSlow("You walk off without responding.", false);
                    printlnSlow(
                            "'What do you I need to do to get some peace and quiet on this campus?' you think to yourself.",
                            false);
                    player.addExFriend(c);
                }
                break;
        }
    }
}
