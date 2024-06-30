import Includes.*;

public class RequestQueue {
    private Node<RequestData> front;
    private Node<RequestData> back;
    private int length = 0;

    public RequestData getFront() {
        return this.front.data;
    }

    public int getLength() {
        /*
         * Your code here.
         */

        return this.length;
    }

    public void push(int ISBN, int UserID) {
        /*
         * Your code here.
         */


        RequestData reqData = new RequestData();

        reqData.ISBN = ISBN;
        reqData.UserID = UserID;

        MyDate anyDate = new MyDate();
        anyDate.month = 1;
        anyDate.year = 2024;

        Node newNode = new Node();
        newNode.data = reqData;


        if (this.length == 0){

            this.front = newNode;
            this.back = newNode;
        }

        else {
            this.back.next = newNode;
            this.back = newNode;
        }

        this.length ++;




        return;
    }

    public void pop() {      // processing needs to be done before popping, 
        /*
         * Your code here.
         */

        if (this.length ==0){
            System.out.println("queue empty");
        }

        else{
            this.front = this.front.next;
        }
        return;
    }

    public String toString(){
        Node<RequestData> temp = front;
        String s = "Length: " + length + "\n";
        while(temp != null){
            s+=temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
}
