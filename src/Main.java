import java.util.Scanner;

public class Main {

    static Roster roster = new Roster();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args)
    {
        printMenu();

            String input = scan.nextLine();

            //this makes sure the input put in is recognized
            if(!input.equals("0") && !input.equals("1")
                    && !input.equals("2") && !input.equals("3")
                    && !input.equals("4") && !input.equals("5")
                    && !input.equals("6")
                    && !input.equals("8")){
                System.out.println("\ninput unrecognized");
            }

            //this calls on dispatch and shuts down if 0 is pressed
        while (!input.equals("0"))
        {
            dispatch(input);
            printMenu();
            input = scan.nextLine();
        }
        System.out.println("\ngoodbye");
    }
    //this is our main menu that allows the user to use all the features
    public static void dispatch(String input) {
        switch (input) {
            case "1": //starts tournament
                roster.tournament();
                break;
            case "2": //prints roster
                System.out.println("here is the current roster");
                System.out.println("--------------------------");
                roster.viewParticipants();
                System.out.println("--------------------------");
                break;
            case "3": //adds new participants and makes sure roster doesn't exceed 8 participants
                if (roster.sizeOf() == 8) {
                    System.out.println("roster cannot exceed 8 participants");
                    break;
                }

                System.out.println("their name?");
                Scanner names = new Scanner(System.in);
                String name = names.nextLine();

                if (roster.nameExist(name)) {
                    System.out.println("participant with name " + name
                            + " already exist");
                    break;
                }

                System.out.println("what is their title?(sir, night, etc...)");
                Scanner titles = new Scanner(System.in);
                String title = titles.nextLine();


                Participant seed = new Participant(name, title);
                roster.addParticipant(seed);

                System.out.println(title + " " + name + " has been added to " +
                        "the roster");
                break;
            case "4": //this removes a participant
                System.out.println("who do you want to remove?");
                Scanner targets = new Scanner(System.in);
                String del = targets.nextLine();

                roster.deleteParticipant(del);
                break;
            case "5": //this swaps the seeding of two participants
                System.out.println("first person to swap?");
                Scanner swapscan = new Scanner(System.in);
                String swap1 = swapscan.nextLine();

                System.out.println("second person to swap?");
                Scanner swapscan2 = new Scanner(System.in);
                String swap2 = swapscan2.nextLine();

                roster.swapParticipants(swap1, swap2);

                System.out.println(swap1 + " and " + swap2 +
                        " have been switched");
                break;
            case "6": //this fills the remainder of the roster with pre made participants
                if (roster.sizeOf() == 8) {
                    System.out.println("roster cannot exceed 8 participants");
                    break;
                }

                roster.fill();
                break;
            case "7": //this clears all the participants from the roster but makes sure the user is sure about it
                System.out.println("to confirm you want to clear the roster " +
                        "type CLEAR");
                Scanner confirmclear = new Scanner(System.in);
                String cc = confirmclear.nextLine();

                if (cc.equals("CLEAR")) {
                    roster.clear();
                    System.out.println("roster cleared");
                } else {
                    System.out.println("roster not cleared");
                }
                break;
            case "8": //this randomizes the seeding
                roster.randomizeSeeding();
                System.out.println("here is the new seeding");
                System.out.println("--------------------------");
                roster.viewParticipants();
                System.out.println("--------------------------");
                break;
        }
    }
//this prints out the option so the user knows whatv to do
    public static void printMenu()
    {
        System.out.println("  ");
        System.out.println("0: close game");
        System.out.println("1: start tournament");
        System.out.println("2: view participants");
        System.out.println("3: add participant");
        System.out.println("4: remove participant");
        System.out.println("5: swap participants");
        System.out.println("6: fill roster");
        System.out.println("7: clear roster");
        System.out.println("8: shuffle seeding");
        System.out.print("\nEnter your choice: ");
    }
}