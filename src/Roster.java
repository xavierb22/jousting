import java.util.Random;
import java.util.Scanner;

public class Roster {
    Node head = null;
    Node tail = null;
    Match_sim newmatch = new Match_sim();

    //this method adds a new participant
    public void addParticipant(Participant participant) {
        Node newNode = new Node(participant);

        if (head == null) {
            head = tail = newNode;
            head.prev = tail.next = null;
        } else {
            tail.next = newNode;

            newNode.prev = tail;

            tail = newNode;

            tail.next = null;
        }
        seedParticipants();
    }

    //this method shows the list of participants organized by seed
    public void viewParticipants() {
        Node current = head;
        if (head == null) {
            System.out.println("no one has registered yet");
            return;
        }
        // this seeds participants before printing the list
        seedParticipants();

        while (current != null) {
            System.out.println(current.participant.seed + "- " +
                    current.participant.title + " " + current.participant.name);
            current = current.next;
        }
    }

    //this method deletes a selected participant
    public void deleteParticipant(String deltarget) {
        Node current = head;
        if (current == null) {
            System.out.println("no one has registered yet");
            return;
        }

        if (current.participant.name.equals(deltarget)) {
            head = current.next;
        }

        try {
            while (!current.participant.name.equals(deltarget)) {
                current = current.next;
            }
        } catch (Exception doesntExist) {
            System.out.println("could not find participant " + deltarget);
            return;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        }

        if (current.prev != null) {
            current.prev.next = current.next;
        }

        System.out.println("participant " + deltarget + " has been removed from the" +
                " competition");

    }

    //this method swaps two selected participants
    public void swapParticipants(String swap1, String swap2) {
        Node current = head;
        Node alsocurrent = head;
        seedParticipants();

        if (current == null) {
            System.out.println("no one has registered yet");
        }

        if (swap1.equals(swap2)) {
            System.out.println("you entered the same name twice");
            return;
        }

        //these two loops find the two participants
        try{
        while (current != null && !current.participant.name.equals(swap1)) {
            current = current.next;
        }}catch (Exception doesntExist) {
            System.out.println("could not find participant " + swap1);
            return;
        }

        try{
            while (current != null && !current.participant.name.equals(swap2)) {
                current = current.next;
            }}catch (Exception doesntExist) {
            System.out.println("could not find participant " + swap2);
            return;
        }

        //this swaps the previous nodes of the two participants
        if (current.prev != null) {
            current.prev.next = alsocurrent;
        }
        else{
            head = alsocurrent;
        }

        if (alsocurrent.prev != null) {
            alsocurrent.prev.next = current;
        }
        else{
            head = current;
        }

        //this swaps the next nodes of the two participants completing the swap
        Node temp = current.next;
        current.next = alsocurrent.next;
        alsocurrent.next = temp;
    }

    //this method sets each participant's seed
    public void seedParticipants(){
        Node current = head;

        int i = 1;

        while(current != null){
            current.participant.seed = i;
            i++;
            current = current.next;
        }
    }

    //clears list
    public void clear(){
        head = null;
    }

    //this checks the current size of the linked list
    public int sizeOf(){
        Node current = head;
        int size = 0;

        while(current != null){
            size++;
            current = current.next;
        }
        return size;
    }

    //this finds a participant based on there seeding
    public Participant findSeed(int seedFind){
        Node current = head;

        seedParticipants();

        while(current.participant.seed != seedFind){
            current = current.next;
        }
        return current.participant;
    }

    //this randomizes everyone's seeding by putting all the participants in an array, clearing the linked list than
    //randomly places each participant back into the linked list
    public void randomizeSeeding() {
        Node current = head;

        Participant[] placeHold = new Participant[8];

        for(int i=0; i < sizeOf(); i++){
            placeHold[i] = current.participant;
            current = current.next;
        }

        clear();

        for(int n = 1; sizeOf() < 8;){
            Random rand = new Random();
            int index = rand.nextInt(8);

            if(!nameExist(placeHold[index].name)){
                addParticipant(placeHold[index]);
                n++;
            }
        }
    }

    //this checks if a name is taken by a participant
    public boolean nameExist(String nCheck){
        Node current = head;

        for(int i=1; i <= sizeOf(); i++){
            if(current.participant.name.equals(nCheck)){
                return true;
            }
            else current = current.next;
        }
        return false;
    }

    //this method takes names from an array and puts it into the linked list so the user doesn't have to pick new names
    // if they don't want to
    public void fill(){

        Participant[] fillIns = new Participant[8];

        fillIns[0] = new Participant("Bob",  "Sir");

        fillIns[1] = new Participant("Bill", "Knight");

        fillIns[2] = new Participant("Tim",  "Jester");

        fillIns[3] = new Participant("Cam", "Regent");

        fillIns[4] = new Participant("Doug", "Knight");

        fillIns[5] = new Participant("Sally",  "Lady");

        fillIns[6] = new Participant("Lara", "Knight");

        fillIns[7] = new Participant("Robert", "Sir");

        for(int n = 1; sizeOf() < 8;){
            Random rand = new Random();
            int index = rand.nextInt(8);

            if(!nameExist(fillIns[index].name)){
                addParticipant(fillIns[index]);
                System.out.println(fillIns[index].title + " " +
                        fillIns[index].name + " has been added to " + "the roster");
                n++;
            }
        }
    }

    //this is the method that starts the tournament
    public void tournament(){

        Scanner start = new Scanner(System.in);
        if(sizeOf() < 8){
            System.out.println("roster must have exactly 8 participants to start, it currently has " + sizeOf());
            return;
        }

        //this prints out the opening message
        System.out.println("\n\nWelcome!!!! to the annual Jousting tournament where esteemed lancers from across " +
                "the kingdom come to compete for the gold trophy handed out by King Jose himself");
        System.out.println("today should be an interesting competition");
        System.out.println("\nnow lets get a look at how our participants are seeded " +
                "after the qualifiers");
        System.out.println("--------------------------");
        viewParticipants();
        System.out.println("--------------------------");
        System.out.println("                             (press any button to continue)");
        String pause = start.nextLine();

        //shows of the schedule for round one
        System.out.println("now that we've seen the roster lets see our schedule " +
                "for the quarter finals...");

        System.out.println("\n" + findSeed(1).title + " "
                + findSeed(1).name);
        System.out.println("VS");
        System.out.println(findSeed(8).title + " "
                + findSeed(8).name);
        System.out.println("-------------");
        System.out.println(findSeed(4).title + " "
                + findSeed(4).name);
        System.out.println("VS");
        System.out.println(findSeed(5).title + " "
                + findSeed(5).name);
        System.out.println("-------------");
        System.out.println(findSeed(3).title + " "
                + findSeed(3).name);
        System.out.println("VS");
        System.out.println(findSeed(6).title + " "
                + findSeed(6).name);
        System.out.println("-------------");
        System.out.println(findSeed(2).title + " "
                + findSeed(2).name);
        System.out.println("VS");
        System.out.println(findSeed(7).title + " "
                + findSeed(7).name);

        System.out.println("                             (press any button to begin tournament)");
        String pause2 = start.nextLine();

        //before each match there is a slightly different message that shows the odds for the match and introduces the riders
        System.out.println("\nour opening match of the tournament is between the number one seed " + findSeed(1).title
                + " " + findSeed(1).name + " and the number eight seed " + findSeed(8).title
                +  " "  + findSeed(8).name +  ", " +
                findSeed(1).name + " is the favorite with -" +
                Math.abs(oddscalc(1,8)) + " odds to win the match");

        System.out.println("                             (press any button to begin match)");
        String pause3 = start.nextLine();

        Participant Q1 = Match_sim.Match(findSeed(1),
                findSeed(8), oddscalc(1,8));
        System.out.println("                             (press any button to continue)");
        String pause4 = start.nextLine();

        System.out.println("\nour second match is between the number four seed " + findSeed(4).title
                + " " + findSeed(4).name + " and the number five seed " + findSeed(5).title
                +  " "  + findSeed(5).name +  ", " +
                        findSeed(4).name + " is the favorite, but not by much with -" +
                        Math.abs(oddscalc(4,5)) + " odds to win the match");

        System.out.println("                             (press any button to begin)");
        String pause5 = start.nextLine();

        Participant Q2 = Match_sim.Match(findSeed(4),
                findSeed(5), oddscalc(4,5));
        System.out.println("                             (press any button to continue)");
        String pause6 = start.nextLine();

        System.out.println("\nnow that we know our first semi final match will be between " + Q1.name + " and " +
                Q2.name + " lets move on to the other side of the bracket starting with a match is between " +
                "\nthe number three seed " + findSeed(3).title
                + " " + findSeed(3).name + " and the number six seed " + findSeed(6).title +  " "
                + findSeed(6).name +  ", " + findSeed(3).name + " is the favorite with -"
                + Math.abs(oddscalc(3,6)) + " odds to win the match");

        System.out.println("                             (press any button to begin match)");
        String pause7 = start.nextLine();

        Participant Q3 = Match_sim.Match(findSeed(3),
                findSeed(6), oddscalc(3,6));
        System.out.println("                             (press any button to continue)");
        String pause8 = start.nextLine();

        System.out.println("\nand finally we conclude our quarter finals with a match between the number two seed " + findSeed(2).title
                + " " + findSeed(2).name + " and the number seven seed " + findSeed(7).title
                +  " "  + findSeed(7).name +  ", " +
                findSeed(2).name + " is the favorite -" +
                Math.abs(oddscalc(2,7)) + " odds to win the match");

        System.out.println("                             (press any button to begin match)");
        String pause9 = start.nextLine();

        Participant Q4 = Match_sim.Match(findSeed(2),
                findSeed(7), oddscalc(2,7));
        System.out.println("                             (press any button to continue)");
        String pause10 = start.nextLine();

        //semi finals message
        System.out.println("The quarter finals are over and there are only four riders left standing each of which " +
                "is now one step closer to be a champion.\nbut first they will have to get past the semi finals. speaking" +
                " of the semi finals here is the schedule...");

        //semi finals schedule
        System.out.println("\n" + Q1.title + " "
                + Q1.name);
        System.out.println("VS");
        System.out.println(Q2.title + " "
                + Q2.name);
        System.out.println("-------------");
        System.out.println(Q3.title + " "
                + Q3.name);
        System.out.println("VS");
        System.out.println(Q4.title + " "
                + Q4.name);

        System.out.println("                             (press any button to begin semi finals)");
        String pause11 = start.nextLine();

        if(Q1.seed < Q2.seed){
            System.out.println("the first match of the semi finals will be between " + Q1.title + " " + Q1.name + " and "
                    + Q2.title + " " + Q2.name + ", " + Q1.name + " is the favorite with -" +
                    Math.abs(oddscalc(Q1.seed, Q2.seed)) + " odds to win the match");
        }
        else{
            System.out.println("the first match of the semi finals will be between " + Q1.title + " " + Q1.name + " and "
                    + Q2.title + " " + Q2.name + ", " + Q2.name + " is the favorite with -" +
                    Math.abs(oddscalc(Q1.seed, Q2.seed)) + " odds to win the match");
        }
        System.out.println("                             (press any button to begin match)");
        String pause12 = start.nextLine();

        Participant S1 = Match_sim.Match(Q1, Q2,
                oddscalc(Q1.seed, Q2.seed));
        System.out.println("                             (press any button to continue)");
        String pause13 = start.nextLine();

        if(Q3.seed < Q4.seed){
            System.out.println("This next match will decide who gets to face " + S1.name + " in the finals. its between " +
                    Q3.title + " " + Q3.name + " and " + Q4.title + " " + Q4.name + ", " + Q3.name +
                    " is the favorite with -" + Math.abs(oddscalc(Q3.seed, Q4.seed)) + " odds to win the match");
        }
        else{
            System.out.println("This next match will decide who gets to face " + S1.name + " in the finals. its between " +
                    Q3.title + " " + Q3.name + " and " + Q4.title + " " + Q4.name + ", " + Q4.name +
                    " is the favorite with -" + Math.abs(oddscalc(Q3.seed, Q4.seed)) + " odds to win the match");
        }

        System.out.println("                             (press any button to begin match)");
        String pause14 = start.nextLine();

        Participant S2 = Match_sim.Match(Q3, Q4,
                oddscalc(Q3.seed, Q4.seed));
        System.out.println("                             (press any button to continue)");
        String pause15 = start.nextLine();

        //determines who lost so they can play in third place match
        Participant S1L;
        Participant S2L;
        if(Q1 == S1){
            S1L = Q2;
        }
        else{
            S1L = Q1;
        }
        if(Q3 == S2){
            S2L = Q4;
        }
        else{
            S2L = Q3;
        }

        //final round message
        System.out.println("The final round is set where we'll see  " + S1L.title + " " + S1L.name + " and "
                + S2L.title + " " + S2L.name + " compete for the bronze trophy and of course " + S1.title + " " + S1.name
                + " and " + S2.title + " " + S2.name
                + " will compete for the gold trophy.\nnow one last time, here's the schedule...");

        //final round schedule
        System.out.println("\n" + S1L.title + " "
                + S1L.name);
        System.out.println("VS");
        System.out.println(S2L.title + " "
                + S2L.name);
        System.out.println("-------------");
        System.out.println(S1.title + " "
                + S1.name);
        System.out.println("VS");
        System.out.println(S2.title + " "
                + S2.name);
        System.out.println("                             (press any button to begin final round)");
        String pause16 = start.nextLine();

        if(S1L.seed < S2L.seed){
            System.out.println("Here goes our third place match between " + S1L.title + " " + S1L.name + " and "
                    + S2L.title + " " + S2L.name + " third place might not be what these two contestants set out for when" +
                    "\nthey entered this tournament but a bronze trophy is better then nothing. " + S1L.name +
                    " is the favorite with -" + Math.abs(oddscalc(S1L.seed, S2L.seed)) + " odds to win the match");
        }else{
            System.out.println("Here goes our third place match between " + S1L.title + " " + S1L.name + " and "
                    + S2L.title + " " + S2L.name + " third place might not be what these two contestants set out for when" +
                    "\nthey entered this tournament but a bronze trophy is better then nothing. " + S2L.name +
                    " is the favorite with -" + Math.abs(oddscalc(S1L.seed, S2L.seed)) + " odds to win the match");
        }

        System.out.println("                             (press any button to begin match)");
        String pause17 = start.nextLine();
        Participant bronze = Match_sim.Match(S1L, S2L,
                oddscalc(S1L.seed, S2L.seed));
        System.out.println("                             (press any button to continue)");
        String pause18 = start.nextLine();

        if(S1.seed < S2.seed){
            System.out.println("Now that " + bronze.title + " " + bronze.name + " has been declared our third place finalist" +
                    " its time to find out who our second and first place finalist will be. \nboth " + S1.name + " and " + S2.name
                    + " want to be the latter but only one can win it all. " +
                    "will it be the -" + Math.abs(oddscalc(S1.seed, S2.seed)) + " favorite " + S1.title + " " + S1.name
                    + " or will it be the underdog " + S2.title + " " + S2.name + ".\nwho knows? anything can happen in the " +
                    "CHAMPIONSHIP MATCH!!!!");
        }else{
            System.out.println("Now that " + bronze.title + " " + bronze.name + " has been declared our third place finalist" +
                    " its time to find out who our second and first place finalist will be. \nboth " + S1.name + " and " + S2.name
                    + " want to be the later but only one can win it all. " +
                    "will it be the -" + Math.abs(oddscalc(S1.seed, S2.seed)) + " favorite " + S2.title + " " + S2.name
                    + " or will it be the underdog " + S1.title + " " + S1.name + ".\nwho knows? anything can happen in the " +
                    "CHAMPIONSHIP MATCH!!!!");
        }

        System.out.println("                             (press any button to begin championship match)");
        String pause19 = start.nextLine();
        Participant winner = Match_sim.Match(S1, S2,
                oddscalc(S1.seed, S2.seed));
        System.out.println("                             (press any button to see final results)");
        String pause20 = start.nextLine();

        Participant[] firstroundouts = new Participant[4];

        //this checks where everyone finished so ig can be printed out later
        if(findSeed(1) == Q1){
            firstroundouts[0] = findSeed(8);
        }
        else{
            firstroundouts[0] = findSeed(1);
        }
        if(findSeed(4) == Q2){
            firstroundouts[1] = findSeed(5);
        }
        else{
            firstroundouts[1] = findSeed(4);
        }
        if(findSeed(3) == Q3){
            firstroundouts[2] = findSeed(6);
        }
        else{
            firstroundouts[2] = findSeed(3);
        }
        if(findSeed(2) == Q4){
            firstroundouts[3] = findSeed(7);
        }
        else{
            firstroundouts[3] = findSeed(2);
        }
        Participant BL;
        if(bronze == S1L){
            BL = S2L;
        }
        else{
            BL = S1L;
        }
        Participant FL;
        if(winner == S1){
            FL = S2;
        }
        else{
            FL = S1;
        }

        //this prints out the final results
        System.out.println("*********************************************************************");
        System.out.println(winner.title + " " + winner.name
                + " has won the tournament and is the new champion of jousting");
        System.out.println("*********************************************************************");

        System.out.println("\nhere is the final standings for the tournament");
        System.out.println("--------------------------");
        System.out.println("1st(gold): " + winner.title + " " + winner.name);
        System.out.println("2nd(silver): " + FL.title + " " + FL.name);
        System.out.println("3rd(bronze): " + bronze.title + " " + bronze.name);
        System.out.println("4th: " + BL.title + " " + BL.name);
        System.out.println("5th-8th: " + firstroundouts[0].title + " " + firstroundouts[0].name);
        System.out.println("5th-8th: " + firstroundouts[1].title + " " + firstroundouts[1].name);
        System.out.println("5th-8th: " + firstroundouts[2].title + " " + firstroundouts[2].name);
        System.out.println("5th-8th: " + firstroundouts[3].title + " " + firstroundouts[3].name);
        System.out.println("--------------------------");
        System.out.println("                             (press any button to go back to main menu)");
        String pause21 = start.nextLine();
    }
    //this sets the odds for each match based on the difference in both participants seeding
    //random.nextInt is used to add variety to the odds
    int oddscalc(int P1, int P2){
        int difference = P2 - P1;
        Random random = new Random();
        if(difference == 7){
            int oddnum = random.nextInt(400 - 352) + 351;
            oddnum = -oddnum;
            return oddnum;
        }
        if(difference == -7){
            int oddnum = random.nextInt(400 - 352) + 351;
            return oddnum;
        }
        else if(difference == 6){
            int oddnum = random.nextInt(350 - 302) + 301;
            oddnum = -oddnum;
            return oddnum;
        }
        else if(difference == -6){
            int oddnum = random.nextInt(350 - 302) + 301;
            return oddnum;
        }
        else if(difference == 5){
            int oddnum = random.nextInt(300 - 252) + 251;
            oddnum = -oddnum;
            return oddnum;
        }
        else if(difference == -5){
            int oddnum = random.nextInt(300 - 252) + 251;
            return oddnum;
        }
        else if(difference == 4){
            int oddnum = random.nextInt(250 - 202) + 201;
            oddnum = -oddnum;
            return oddnum;
        }
        else if(difference == -4){
            int oddnum = random.nextInt(250 - 202) + 201;
            return oddnum;
        }
        else if(difference == 3){
            int oddnum = random.nextInt(200 - 152) + 151;
            oddnum = -oddnum;
            return oddnum;
        }
        else if(difference == -3){
            int oddnum = random.nextInt(200 - 152) + 151;
            return oddnum;
        }
        else if(difference == 2){
            int oddnum = random.nextInt(150 - 127) + 126;
            oddnum = -oddnum;
            return oddnum;
        }
        else if(difference == -2){
            int oddnum = random.nextInt(150 - 127) + 126;
            return oddnum;
        }
        else{

            if(difference == 1) {
                int oddnum = random.nextInt(125 - 106) + 105;
                oddnum = -oddnum;
                return oddnum;
            }
            else{
                int oddnum = random.nextInt(125 - 106) + 105;
                return oddnum;
            }
        }
    }
}