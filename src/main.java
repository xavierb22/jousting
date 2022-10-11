public class main {
    public static void main(String[] args)
    {
        /*
        todo - add clear(), add fill()
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

         */

        Roster roster = new Roster();

        Participant seed1 = new Participant("bob",  "sir");
        roster.addParticipant(seed1);

        Participant seed2 = new Participant("bill", "knight");
        roster.addParticipant(seed2);

        Participant seed3 = new Participant("tim",  "jester");
        roster.addParticipant(seed3);

        Participant seed4 = new Participant("cam",  "regent");
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

        roster.deleteParticipant("cam");
        roster.swapParticipants("tim","sally");
        roster.viewParticipants();

    }
}