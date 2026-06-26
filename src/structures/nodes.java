package structures;
import models.item;

public class nodes {
    public item data;
    public nodes prev;
    public nodes next;

    public nodes(item data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
