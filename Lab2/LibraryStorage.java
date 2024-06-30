import java.util.*;

import Includes.*;

public class LibraryStorage {
    // HashMap
    // process return
    private HashMap<Integer, BookData> storage;
    private RequestQueue rqQueue;
    private PendingRequests prLinkedList;

    public LibraryStorage() {
        storage = new HashMap<Integer, BookData>();
        for (int i = 100001; i < 100011; i++) {
            BookData book = new BookData();
            MyDate dateor = new MyDate();
            dateor.month = 11;
            dateor.year = 2024;
            book.BorrowedStatus = false;
            book.BorrowedByUserID = 0;
            book.ISBN = i;
            book.Publisher = "publisher";
            book.Author = "author";
            book.DateOfReturn = dateor;
            storage.put(i, book);
        }

        rqQueue = new RequestQueue();
        prLinkedList = new PendingRequests();
    }

    public void push(int ISBN, int UserID) {
        rqQueue.push(ISBN, UserID);
        return;
    }

    public boolean processQueue() {
        
        RequestData temp = rqQueue.getFront();
        if (this.storage.containsKey(temp.ISBN)) {
            // System.out.println(this.storage.containsKey(temp.ISBN));
            if (this.storage.get(temp.ISBN).BorrowedStatus == false) {
                this.storage.get(temp.ISBN).BorrowedStatus = true;
                this.storage.get(temp.ISBN).BorrowedByUserID = temp.UserID;

                // System.out.println(temp.RequestDate);
                if(temp.RequestDate!=null){
                    if ((temp.RequestDate.month) == 12) {
                        this.storage.get(temp.ISBN).DateOfReturn.month = 1;
                        this.storage.get(temp.ISBN).DateOfReturn.year++;
                    } else {
                        this.storage.get(temp.ISBN).DateOfReturn.month++;
                    }
                }
                rqQueue.pop();
                return true;
            } 
            else {
                Node<RequestData> temp1 = new Node<RequestData>();
                temp1.data = temp;
                prLinkedList.insert(temp1);
                rqQueue.pop();
                return false;
            }
        }
        else {
            Node<RequestData> temp1 = new Node<RequestData>();
            temp1.data = temp;
            prLinkedList.insert(temp1);
            rqQueue.pop();
            return false;
        }
    }

    public boolean processReturn(BookData book) { // I have assumed this takes BookData object as argument, can also
                                                  // work with ISBN perhaps
        /*
         * Your code here.
         */
        if (this.storage.containsKey(book.ISBN)) {
            if (this.storage.get(book.ISBN).BorrowedStatus == true) {
                this.storage.get(book.ISBN).BorrowedStatus = false;
                this.storage.get(book.ISBN).BorrowedByUserID = -1;
                return true;
            }
        }
        return false;
    }

    public String rqQueueToString() {
        return rqQueue.toString();
    }

    public String prLinkedListToString() {
        return prLinkedList.toString();
    }

}
