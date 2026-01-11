import java.util.Random;

public class Match_sim
{ //sims a match
    static Participant Match(Participant name1, Participant name2, double name1odds)
    {
        double percentage_odds = 0;

        //changes american odds to a percentage number that represents the
        // likelihood the first name entered wins
        if (name1odds < 0)
        {
            percentage_odds = Math.abs(name1odds)/(Math.abs(name1odds) + 100) * 100;
        }
        else if(name1odds > 0)
        {
            percentage_odds = 100/(name1odds + 100) * 100;
        }
        else
        {
            System.out.println("invalid input, start over");
        }

        //this generates a random number from 0 to 100
        Random random = new Random();
        double double_random = random.nextDouble();
        double picked_number = double_random * 100;

        // this generates a random score. the winner can be given up to
        // 12 points and the loser can have up to 11,but it has to be lower than
        // winning score
        int winner_score = random.nextInt(12) + 1; // this has to be twelve then plus 1 to avoid getting 0
        int loser_score = random.nextInt(winner_score);

        // this part calculates the winner of the match. if the number generated
        // above is higher than the percentage odds for first player to win then
        // the second player wins otherwise the first player wins
        if (picked_number <= percentage_odds)
        {
            System.out.println("\n " + name1.title + " " + name1.name +
                    " wins the match by a score of "
                    + winner_score + " - " + loser_score);
            return name1;
        }
        else
        {
            System.out.println("\n " + name2.title + " " + name2.name +
                    " wins the match by a score of "
                    + winner_score + " - " + loser_score);
            return name2;
        }
    }
}
