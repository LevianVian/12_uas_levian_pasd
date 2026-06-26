package app;

import models.item;
import models.Transaction;
import structures.bstStructures;
import structures.dllStructures;
import structures.nodes;

import java.util.Scanner;

public class main{
    private static bstStructures inventory = new bstStructures();
    private static dllStructures transactionHistory = new dllStructures();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedInventory();
        boolean running = true;

        while (running) {
            System.out.println("--- Cashier System Menu ---");
            System.out.println("1. Input Transaction");
            System.out.println("2. View All Transactions & Total Income");
            System.out.println("3. Sort Transactions by Item Name (Ascending)");
            System.out.println("4. Exit");
            System.out.print("Choose menu: ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleTransaction();
                    break;
                case "2":
                    displayTransactions();
                    break;
                case "3":
                    sortTransactionsByItemName();
                    break;
                case "4":
                    running = false;
                    System.out.println("Shutting down system...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void seedInventory() {
        inventory.insert(new item("M01", "Mechanical Keyboard", 650000, 10));
        inventory.insert(new item("M02", "Wireless Mouse", 250000, 15));
        inventory.insert(new item("A01", "Monitor Stand", 150000, 5));
        inventory.insert(new item("A02", "Desk Mat", 100000, 20));
    }

    private static void handleTransaction() {
        System.out.println("\n--- Available Inventory ---");
        inventory.displayInOrder(); // print dari BST nya
        System.out.println("---------------------------");

        System.out.print("\nEnter Item Code: ");
        String code = scanner.nextLine();
        
        item selectedItem = inventory.search(code); 
        
        if (selectedItem == null) {
            System.out.println("Transaction failed: Item code not found.");
            return;
        }

        //klaau stocknya habis jadinya ga bisa order
        if (selectedItem.getStock() == 0) {
            System.out.println("Transaction failed: Item is out of stock.");
            return;
        }

        System.out.print("Enter Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());

        if (qty > selectedItem.getStock()) {
            System.out.println("Transaction failed: Not enough stock available.");
            return;
        }

        System.out.print("Is the customer a member? (y/n): ");
        boolean isMember = scanner.nextLine().equalsIgnoreCase("y");

        selectedItem.decreaseStock(qty);
        
        Transaction transaction = new Transaction(selectedItem, qty, isMember); 
        
        transactionHistory.add(transaction);

        // print buat struk
        System.out.println("\n--- Transaction Success ---");
        System.out.println("TRX Code   : TRX-" + transaction.getTransactionCode());
        System.out.println("Item Name  : " + transaction.getItem().getName());
        System.out.println("Unit Price : Rp " + transaction.getItem().getPrice());
        System.out.println("Quantity   : " + transaction.getQuantity());
        System.out.println("Total Paid : Rp " + transaction.getFinalPrice());
    }

    private static void displayTransactions() {
        System.out.println("\n--- Transaction History ---");
        if (transactionHistory.getSize() == 0) {
            System.out.println("No transactions recorded yet.");
            return;
        }

        double totalIncome = 0;
        nodes current = transactionHistory.getHead();
        
        while (current != null) {
            Transaction trx = current.data;
            System.out.println("TRX-" + trx.getTransactionCode() + 
                               " | Item: " + trx.getItem().getName() + 
                               " | Qty: " + trx.getQuantity() + 
                               " | Paid: Rp " + trx.getFinalPrice());
            totalIncome += trx.getFinalPrice();
            current = current.next;
        }
        
        System.out.println("---------------------------------");
        System.out.println("Total Overall Income: Rp " + totalIncome);
    }

    private static void sortTransactionsByItemName() {

        //ini bubble sortnya buat di double linked list
        if (transactionHistory.getSize() <= 1) {
            System.out.println("Not enough transactions to sort.");
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            nodes current = transactionHistory.getHead();
            
            while (current != null && current.next != null) {
                String name1 = current.data.getItem().getName();
                String name2 = current.next.data.getItem().getName();
                
                //kalau yang name1 nya valuenya lebih besar dari yang name2 jadinya nanti swap
                if (name1.compareToIgnoreCase(name2) > 0) {
                    Transaction temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
        
        System.out.println("Transactions successfully sorted by item name! Select menu 2 to view.");
    }
}