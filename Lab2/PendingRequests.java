import Includes.*;

public class PendingRequests {
    private int length = 0;
    private Node<RequestData> front;
    private Node<RequestData> back;

    public boolean insert(Node<RequestData> insnode) {
        /*
         * Your code here.
         */
        if(this.back == null){
            this.front = insnode;
            this.back = insnode;
            this.length++;
        }
        else{
            this.back.next = insnode;
            insnode.previous = this.back;
            this.back = insnode;
            this.length++;
        }
        return true;
    }

    public boolean delete(Node<RequestData> delnode) {
        /*
         * Your code here.
         */
        if(this.back==null){
            // do nothing
            return false;
        }
        if(delnode == null){
            return false;
        }
        if(this.front==delnode){
            if(this.front.next ==null){
                this.front=null;
                this.back=null;
            }
            else {
                this.front = this.front.next;
                this.front.previous = null;
            }
            this.length--;
        }
        else if(this.back == delnode){
            this.back = this.back.previous;
            this.back.next = null;
            this.length--;
        }
        else{
            delnode.previous.next = delnode.next;
            delnode.next.previous = delnode.previous;
            this.length--;
        }
        return true;
    }

    public Node<RequestData> find(int ISBN) {
        /*
         * Your code here.
         */

        Node<RequestData> nrd = this.front;
        while(nrd!=null){
            if(nrd.data.ISBN==ISBN){
                return nrd;
            }
            nrd = nrd.next;
        }
        return null;
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
