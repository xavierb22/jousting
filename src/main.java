import java.util.Scanner;

public class main {

    static Roster roster = new Roster();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args)
    {
        /*
        todo - add wasn't found to swap, add are you sure to clear
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
                System.out.println("this will start tournament");
                break;
            case 2:
                System.out.println("here is the current roster");
                System.out.println("--------------------------");
                roster.viewParticipants();
                System.out.println("--------------------------");
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

                System.out.println(title + " " + name + " has been added to " +
                        "the roster");
                break;
            case 4:
                System.out.println("who do you want to remove?");
                Scanner targets = new Scanner(System.in);
                String del = targets.nextLine();

                roster.deleteParticipant(del);
                break;
            case 5:
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
            case 6:
                roster.fill();
                break;
            case 7:
                roster.clear();
                break;
            case 8:
                roster.randomizeSeeding();
                System.out.println("here is the new seeding");
                System.out.println("--------------------------");
                roster.viewParticipants();
                System.out.println("--------------------------");
                break;
        }
    }

    public static void printMenu()
    {
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