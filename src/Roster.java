public class Roster {
    Node head = null;
    Node tail = null;

    public void addParticipant(Participant particapant){
        Node newNode =new Node(particapant);

        if(head == null){
            head = tail = newNode;
           head.previous = tail.next = null;
        }
        else{
            tail.next = newNode;

            newNode.previous = tail;

            tail = newNode;

            tail.next = null;
        }
    }

    public void viewParticipants(){
        Node current = head;
        if(head == null)
        {
            System.out.println("no one has registered yet");
            return;
        }

        while(current !=  null){
            System.out.println(current.participant.seed + ": " +
                    current.participant.title + " " + current.participant.name);
            current = current.next;
        }
    }
}
