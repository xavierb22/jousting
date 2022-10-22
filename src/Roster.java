import java.util.Random;

public class Roster {
    Node head = null;
    Node tail = null;

    //this method adds a new participant
    public void addParticipant(Participant particapant) {
        Node newNode = new Node(particapant);

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

        System.out.println("here is the current roster");
        System.out.println("--------------------------");

        while (current != null) {
            System.out.println(current.participant.seed + "- " +
                    current.participant.title + " " + current.participant.name);
            current = current.next;
        }
        System.out.println("--------------------------");
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
            while (current.participant.name != deltarget) {
                current = current.next;
            }
        } catch (Exception doesntexist) {
            System.out.println("could not find participant " + deltarget);
            return;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        }

        if (current.prev != null) {
            current.prev.next = current.next;
        }

        return;
    }

    //this method swaps two selected participants
    public void swapParticipants(String swap1, String swap2) {
        Node current = head;
        Node alsocurrent = head;
        seedParticipants();

        if (current == null) {
            System.out.println("no one has registered yet");
        }

        if (swap1 == swap2) {
            return;
        }

        //these two loops find the two participants
        while (current != null && current.participant.name != swap1) {
            current = current.next;
        }

        while (alsocurrent != null && alsocurrent.participant.name != swap2) {
            alsocurrent = alsocurrent.next;
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

    public String findSeed(int seedFind){
        Node current = head;

        seedParticipants();

        while(current.participant.seed != seedFind){
            current = current.next;
        }
        return current.participant.name;
    }
/*
    public void randomizeSeeding() {
        for(int x = 1; x < 8; x++){
            Random rand = new Random();
            int index = rand.nextInt(8);
            index++;

            String indexnum = findSeed(index);
            String xnum = findSeed(x);

            System.out.println(xnum);
            System.out.println(indexnum);

            swapParticipants(xnum, indexnum);
            viewParticipants();
            System.out.println("-----------");

        }

        System.out.println("here's the shuffled list... ");

        viewParticipants();
    }*/

    public boolean nameExist(String nCheck){
        Node current = head;

        for(int i=1; i <= sizeOf(); i++){
            if(current.participant.name == nCheck){
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

        System.out.println(sizeOf());
        for(int n = 1; sizeOf() < 8;){
            Random rand = new Random();
            int index = rand.nextInt(8);

            if(nameExist(fillIns[index].name) != true){
                addParticipant(fillIns[index]);
                System.out.println(fillIns[index].title + " " +
                        fillIns[index].name + " has been added to " + "the roster");
                n++;
            }
        }
    }
}