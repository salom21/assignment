
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// class representing a single task
class Task {
    private String title;
    private String description;
    private boolean completed;
// constructor to intialize the task with a title and description
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }
// Getter for the title
    public String getTitle() {
        return title;
    }
// getter for the task descripition
    public String getDescription() {
        return description;
    }
// method to check if the task is complete
    public boolean isCompleted() {
        return completed;
    }
// method to mark the task as completed
    public void markCompleted() {
        completed = true;
    }
}
// Node class to be used in the linked list each node contains a task
class Node {
    Task task;
    Node next;
// constructor to create a node with a given task
    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}
// class representing a linked list of tasks
class ToDoList {
    private Node head;
// method to add a task to the to-do list
    public void addToDo(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {//if the list is empty set the new node as a header
                current = current.next;
            }
            current.next = newNode;
        }
    }
//Metjod to mark a task as completed
    public void markToDoAsCompleted(String title) {
        Node current = head;
        while (current != null) {
            if (current.task.getTitle().equals(title)) {
                current.task.markCompleted();
                break;
            }
            current = current.next;//Mark the task as completed
        }
    }
//Method to display the to do list in a new window
    public void viewToDoList() {
        JFrame frame = new JFrame("To-Do List App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Node current = head;
        while (current != null) {
            String status = current.task.isCompleted() ? "Completed" : "Pending";
            JLabel label = new JLabel(current.task.getTitle() + " - " + current.task.getDescription() + " (" + status + ")");
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            panel.add(label);
            current = current.next;
        }

        frame.add(panel);
        frame.setVisible(true);
    }
}

public class ToDoListAppGUI {
    private ToDoList toDoList;
//Constructor to set up the GUI
    public ToDoListAppGUI() {
        toDoList = new ToDoList();

        JFrame frame = new JFrame("To-Do List App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel buttonPanel = new JPanel();

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBackground(Color.GREEN);
        addTaskButton.setPreferredSize(new Dimension(120, 30));

        JButton markCompletedButton = new JButton("Mark Completed");
        markCompletedButton.setBackground(Color.ORANGE);
        markCompletedButton.setPreferredSize(new Dimension(120, 30));

        JButton viewListButton = new JButton("View List");
        viewListButton.setBackground(Color.CYAN);
        viewListButton.setPreferredSize(new Dimension(120, 30));

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter task title:");
                String description = JOptionPane.showInputDialog("Enter task description:");
                Task task = new Task(title, description);
                toDoList.addToDo(task);
                JOptionPane.showMessageDialog(null, "Task added successfully!");
            }
        });

        markCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter task title");
                toDoList.markToDoAsCompleted(title);
                JOptionPane.showMessageDialog(null, "Task marked as completed!");
            }
        });
//Action listener for viewing the to do list
        viewListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoList.viewToDoList();
            }
        });
//Add button to the pannel
        buttonPanel.add(addTaskButton);
        buttonPanel.add(markCompletedButton);
        buttonPanel.add(viewListButton);
//Add button to the frame
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListAppGUI());
    }
}
