package structures;

import models.Transaction;

public class dllStructures {
    private nodes head;
    private nodes tail;
    private int size;

    public dllStructures(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(Transaction data){ {
        nodes newNode = new nodes(data);
        
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }}

    public int getSize() {
        return size;
    }

    public nodes getHead() {
        return head;
    }
}
