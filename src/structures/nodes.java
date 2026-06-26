package structures;
import models.Transaction;

public class nodes {
    public Transaction data;
    public nodes prev;
    public nodes next;

    public nodes(Transaction data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
