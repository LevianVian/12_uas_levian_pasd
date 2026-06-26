package structures;
import models.item;

public class treeNode {
    public item item;
    public treeNode left;
    public treeNode right;

    public treeNode(item item) {
        this.item = item;
        this.left = null;
        this.right = null;
    }
}
