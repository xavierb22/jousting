import java.util.Scanner;

public class main {

    static Roster roster = new Roster();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args)
    {
        /*
        todo - add fill()
        */
        /*
        System.out.println("who is the first opponent");

        Scanner names = new Scanner(System.in);
        String name1 = names.nextLine();

        System.out.println("who is the second opponent");

        String name2 = names.nextLine();

        System.out.println("what are " + name1 +
        "'s odds of winning (american odds)");

        Scanner odds = new Scanner(System.in);

        double name1odds = odds.nextDouble();

        Match_sim newmatch = new Match_sim();

        newmatch.Match(name1, name2, name1odds);

        Roster roster = new Roster();

        Participant seed1 = new Participant("bob",  "sir");
        roster.addParticipant(seed1);

        Participant seed2 = new Participant("bill", "knight");
        roster.addParticipant(seed2);

        Participant seed3 = new Participant("tim",  "jester");
        roster.addParticipant(seed3);

        Participant seed4 = new Participant("cam", "regent");
        roster.addParticipant(seed4);

        Participant seed5 = new Participant("doug", "sir");
        roster.addParticipant(seed5);

        Participant seed6 = new Participant("sally",  "lady");
        roster.addParticipant(seed6);

        Participant seed7 = new Participant("dally", "sir");
        roster.addParticipant(seed7);

        Participant seed8 = new Participant("robert", "sir");
        roster.addParticipant(seed8);

        Participant seed9 = new Participant("yim", "sir");
        roster.addParticipant(seed9);

        //roster.deleteParticipant("cam");
        //roster.swapParticipants("bill","robert");
        //roster.clear();
        //roster.viewParticipants();
        //roster.randomizeSeeding();
        //System.out.println(roster.findSeed(2));
*/
        printMenu();
        int input = scan.nextInt();
        while (input != 0)
        {
            dispatch(input);
            printMenu();
            input = scan.nextInt();
        }

    }
    public static void dispatch(int input)
    {
        switch (input) {
            case 1:
                System.out.println("Wednesday");
                break;
            case 2:
                roster.viewParticipants();
                break;
            case 3:

                System.out.println("their name?");
                Scanner names = new Scanner(System.in);
                String name = names.nextLine();

                System.out.println("do they have a title(sir, night, etc...)");
                Scanner titles = new Scanner(System.in);
                String title = titles.nextLine();


                Participant seed = new Participant(name, title);
                roster.addParticipant(seed);

                System.out.println(name + " has been added to " +
                        "the roster");
                break;
            case 4:
                System.out.println("who do you want to remove?");
                Scanner targets = new Scanner(System.in);

                roster.deleteParticipant(targets.nextLine());
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                roster.clear();
                break;
            case 8:
                System.out.println("Sunday");
                break;
        }
    }

    public static void printMenu()
    {
        System.out.println("\n Menu ");
        System.out.println(" ====");
        System.out.println("1: ");
        System.out.println("2: view participants");
        System.out.println("3: add participant");
        System.out.println("4: remove participant");
        System.out.println("5: ");
        System.out.println("6: ");
        System.out.print("\nEnter your choice: ");
    }
}