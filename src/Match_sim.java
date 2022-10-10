import java.util.Random;
import java.util.Scanner;

public class Match_sim
{
    static void Match(String name1,String name2,double name1odds)
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

        // this prints out a message before the match introducing both players and
        // announcing the odds
        if (percentage_odds > 50)
        {
            int toldodds = (int) Math.round(name1odds);
            System.out.println("\nTodays match is between sir " + name1 + " and Sir "
                    + name2 +  ", " + name1 + " is the favorite with " + toldodds +
                    " odds to win the match");
        }
        else if(percentage_odds < 50)
        {
            int toldodds = (int) Math.round(name1odds);
            System.out.println("\nTodays match is between sir " + name1 + " and Sir "
                    + name2 +  ", " + name2 + " is the favorite with -" + toldodds +
                    " odds to win the match");
        }
        else
        {
            System.out.println("\nTodays match is between sir " + name1 + " and Sir "
                    + name2 +  ", the odds are EVEN for both opponents");
        }

        // this prompts the user to press enter so the match can simulate
        System.out.println("(press enter to simulate)");
        Scanner start = new Scanner(System.in);
        String check = start.nextLine();

        // this generates a random score. the winner can be given up to
        // 12 points and the loser can have up to 11,but it has to be lower than
        // winning score
        int upperbound = 12;
        int winner_score = random.nextInt(upperbound) + 1;
        int upperboundL = winner_score;
        int loser_score = random.nextInt(upperboundL);

        // this part calculates the winner of the match. if the number generated
        // above is higher than the percentage odds for first player to win then
        // the second player wins otherwise the first player wins
        if (picked_number <= percentage_odds)
        {
            System.out.println("\nsir " + name1 + " wins the match by a score of "
                    + winner_score + " - " + loser_score);
        }
        else
        {
            System.out.println("\nSir " + name2 + " wins the match by a score of "
                    + winner_score + " - " + loser_score);
        }
    }
}
