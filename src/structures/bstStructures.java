package structures;

import models.item;

public class bstStructures {
    private treeNode root;

    public bstStructures() {
        this.root = null;
    }

    public void insert(item item) {
        root = insertRec(root, item);
    }

    private treeNode insertRec(treeNode root, item item) {
        if (root == null) {
            root = new treeNode(item);
            return root;
        }

        if (item.getCode().compareToIgnoreCase(root.item.getCode()) < 0) {
            root.left = insertRec(root.left, item);
        } else if (item.getCode().compareToIgnoreCase(root.item.getCode()) > 0) {
            root.right = insertRec(root.right, item);
        }

        return root;
    }

    public item search(String code) {
        treeNode result = searchRec(root, code);
        return result != null ? result.item : null;
    }

    private treeNode searchRec(treeNode root, String code) {
        if (root == null || root.item.getCode().equalsIgnoreCase(code)) {
            return root;
        }

        if (root.item.getCode().compareToIgnoreCase(code) > 0) {
            return searchRec(root.left, code);
        }

        return searchRec(root.right, code);
    }

    public void displayInOrder() {
        displayRec(root);
    }

    private void displayRec(treeNode root) {
        if (root != null) {
            displayRec(root.left);
            System.out.println(root.item.getCode() + " - " + root.item.getName() + " (Stock: " + root.item.getStock() + ")");
            displayRec(root.right);
        }
    }
}