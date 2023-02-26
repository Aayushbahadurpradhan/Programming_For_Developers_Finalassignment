package QuestionNo9.Views;

import QuestionNo9.Controller.UserController;
import QuestionNo9.Model.Task;
import QuestionNo9.Model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTask extends JFrame implements ActionListener {
    private JLabel taskIdLabel;
    private JLabel taskNameLabel;
    private JTextField taskIdField;
    private JTextField taskNameField;
    private JButton addTaskButton;
    private JButton goBackButton;

    UserController userController;
    User user;

    public AddTask() {
        this.userController = new UserController();
        this.user = userController.fetchLoggedInCustomer();

        // Set the background color
        getContentPane().setBackground(new Color(51, 51, 51));

        // Set the layout and constraints
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Add the task ID label
        taskIdLabel = new JLabel("Task ID:");
        taskIdLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(taskIdLabel, constraints);

        // Add the task ID field
        taskIdField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(taskIdField, constraints);

        // Add the task name label
        taskNameLabel = new JLabel("Task Name:");
        taskNameLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(taskNameLabel, constraints);

        // Add the task name field
        taskNameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(taskNameField, constraints);

        // Add the "Add Task" button
        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(this);
        addTaskButton.setBackground(new Color(59, 89, 182));
        addTaskButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(addTaskButton, constraints);

        // Add the "Go Back" button
        goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(this);
        goBackButton.setBackground(new Color(255, 255, 255));
        goBackButton.setForeground(Color.BLACK);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(goBackButton, constraints);

        // Set the title, size, and close operation of the window
        setTitle("Add Task");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addTaskButton) {
            int taskId = Integer.parseInt(taskIdField.getText());
            String taskName = taskNameField.getText();
            Task task = new Task(taskId, taskName);
            UserController userController1 = new UserController();
            int result = userController1.taskCustomerPreparedStatement(task);
            if (result == 1) {
                JOptionPane.showMessageDialog(this, "task added");
            } else {
                JOptionPane.showMessageDialog(this, "try again!");
            }
        } else if (event.getSource() == goBackButton) {
            // Go back to Dashboard logic
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
            this.dispose();
        }
    }
}
