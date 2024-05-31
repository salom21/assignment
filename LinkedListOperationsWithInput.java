import java.util.Scanner;
// Node class represents each element in the linked list
class Node {
    int data;
    Node next;
// constructor to intialize a new mode
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
// linkedList class to handle linked list operations
class LinkedList {
    Node head;
// method to insert a node at a specified position
    public void insertAtPos(int pos, int data) {
        Node newNode = new Node(data);
        if (pos == 0) {
            // insert at the head of the list
            newNode.next = head;
            head = newNode;
        } else {
            //Transverse to the position before where the new node will be inserted
            Node current = head;
            for (int i = 0; i < pos - 1 && current != null; i++) {
                current = current.next;
            }
            // insert new node
            if (current != null) {
                newNode.next = current.next;
                current.next = newNode;
            } else {
                System.out.println("Position out of bounds.");
            }
        }
    }
// method to delete at specified position
    public void deleteAtPosition(int pos) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (pos == 0) {
            // delet the head node
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < pos - 1 && current != null; i++) {
                current = current.next;
            }
            if (current != null && current.next != null) {
                current.next = current.next.next;
            } else {
                System.out.println("Position out of bounds.");
            }
        }
    }

    public void deleteAfterNode(int data) {
        Node current = head;
        while (current != null && current.data != data) {
            current = current.next;
        }
        
        if (current != null && current.next != null) {
            current.next = current.next.next;
        } else {
            System.out.println("Node not found or no node to delete after.");
        }
    }

    public boolean searchNode(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

class Stack {
    private Node top;

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

public class LinkedListOperationsWithInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList linkedList = new LinkedList();

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Insert at Position");
            System.out.println("2. Delete at Position");
            System.out.println("3. Delete After Node");
            System.out.println("4. Search Node");
            System.out.println("5. Print List");
            System.out.println("6. Stack Operations");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                // insert a node at specified position 
                    System.out.print("Enter position: ");
                    int pos = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter data: ");
                    int data = scanner.nextInt();
                    scanner.nextLine();
                    linkedList.insertAtPos(pos, data);
                    break;

                case 2:
                // dlete at specified position
                    System.out.print("Enter position: ");
                    pos = scanner.nextInt();
                    scanner.nextLine();
                    linkedList.deleteAtPosition(pos);
                    break;

                case 3:
                //search for a node with a specified data value
                    System.out.print("Enter data of node after which to delete: ");
                    data = scanner.nextInt();
                    scanner.nextLine();
                    linkedList.deleteAfterNode(data);
                    break;

                case 4:
                    System.out.print("Enter data to search: ");
                    data = scanner.nextInt();
                    scanner.nextLine();
                    if (linkedList.searchNode(data)) {
                        System.out.println("Node found.");
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;

                case 5:
                    System.out.println("Linked List: ");
                    linkedList.printList();
                    break;

                case 6:
                // handles stack operations
                    Stack stack = new Stack();
                    while (true) {
                        System.out.println("\nChoose a Stack Operation:");
                        System.out.println("1. Push");
                        System.out.println("2. Pop");
                        System.out.println("3. Peek");
                        System.out.println("4. Check Empty");
                        System.out.println("5. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int stackChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (stackChoice) {
                            case 1:
                                System.out.print("Enter data to push: ");
                                data = scanner.nextInt();
                                scanner.nextLine();
                                stack.push(data);
                                break;
                            case 2:
                            // pop an element from the stack
                                System.out.println("Popped element: " + stack.pop());
                                break;
                            case 3:
                            // peek at the top element
                                System.out.println("Top element: " + stack.peek());
                                break;
                            case 4:
                            //check if the stsck is empty
                                if (stack.isEmpty()) {
                                    System.out.println("Stack is empty.");
                                } else {
                                    System.out.println("Stack is not empty.");
                                }
                                break;
                            case 5:
                                break; // Go back to main menu
                            default:
                                System.out.println("Invalid stack choice.");
                        }

                        if (stackChoice == 5) { // Back to main menu
                            break;
                        }
                    }
                    break;

                case 7:
                // exit the program
                    scanner.close();
                    System.out.println("Exiting program.");
                    return;
// handle invalid choice
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}