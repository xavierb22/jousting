import java.util.Random;

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

    public int sizeOf(){
        Node current = head;
        int size = 0;

        while(current != null){
            size++;
            current = current.next;
        }
        return size;
    }


    public Participant findSeed(int seedFind){
        Node current = head;

        seedParticipants();

        while(current.participant.seed != seedFind){
            current = current.next;
        }
        return current.participant;
    }

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

    public void fill(){

        Participant[] fillIns = new Participant[8];

        fillIns[0] = new Participant("bob",  "sir");

        fillIns[1] = new Participant("bill", "knight");

        fillIns[2] = new Participant("tim",  "jester");

        fillIns[3] = new Participant("cam", "regent");

        fillIns[4] = new Participant("doug", "sir");

        fillIns[5] = new Participant("sally",  "lady");

        fillIns[6] = new Participant("dally", "sir");

        fillIns[7] = new Participant("robert", "sir");

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

    public void tournament(){
        if(sizeOf() < 8){
            System.out.println("roster must have exactly 8 participants to start, it currently has " + sizeOf());
            return;
        }

        Participant Q1 = Match_sim.Match(findSeed(1),
                findSeed(8), oddscalc(1,8));

        Participant Q2 = Match_sim.Match(findSeed(4),
                findSeed(5), oddscalc(4,5));

        Participant Q3 = Match_sim.Match(findSeed(3),
                findSeed(6), oddscalc(3,6));

        Participant Q4 = Match_sim.Match(findSeed(2),
                findSeed(7), oddscalc(2,7));
        
        Participant S1 = Match_sim.Match(Q1, Q2,
                oddscalc(Q1.seed, Q2.seed));

        Participant S2 = Match_sim.Match(Q3, Q4,
                oddscalc(Q3.seed, Q4.seed));

        Participant winner = Match_sim.Match(S1, S2,
                oddscalc(S1.seed, S2.seed));

        System.out.println("\n" + winner.title + " " + winner.name
                + " has won the tournament");
    }
    int oddscalc(int P1, int P2){
        int difference = P2 - P1;
        if(difference == 7){
            return -400;
        }
        if(difference == -7){
            return +400;
        }
        else if(difference == 6){
            return -350;
        }
        else if(difference == -6){
            return +350;
        }
        else if(difference == 5){
            return -300;
        }
        else if(difference == -5){
            return +300;
        }
        else if(difference == 4){
            return -250;
        }
        else if(difference == -4){
            return +250;
        }
        else if(difference == 3){
            return -200;
        }
        else if(difference == -3){
            return +200;
        }
        else if(difference == 2){
            return -150;
        }
        else if(difference == -2){
            return +150;
        }
        else{
            //this generates a random number from 0 to 100
            Random random = new Random();
            double double_random = random.nextDouble();
            double picked_number = double_random * 100;

            if(picked_number > 10 && difference == 1){
                return -125;
            }
            else if(picked_number > 10 && difference == -1){
                return +125;
            }
            else{
                return -100;
            }
        }
    }
}