package Frames.Staff;

import Components.Sidebar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class update extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(234, 233, 228);
    private static final Color BUTTON_COLOR = new Color(184, 155, 119);
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField contactField;
    private JTextField positionField;
    private JTextField dobField;
    private JTextField genderField;
    private JTextField statusField;
    private JTextField infoField;

    public update() {
        super("Update Staff Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        Sidebar sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        add(mainPanel, BorderLayout.CENTER);

        // Header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        headerPanel.setBackground(BACKGROUND_COLOR);
        ImageIcon icon = new ImageIcon("src/Resources/images/updateicon.png");
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        headerPanel.add(new JLabel(new ImageIcon(img)));
        JLabel title = new JLabel("Update Details");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        headerPanel.add(title);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;

        // Helper method to add form rows
        nameField = createRow("Name:", formPanel, gbc, 0);
        emailField = createRow("Email:", formPanel, gbc, 1);
        contactField = createRow("Contact Number:", formPanel, gbc, 2);
        positionField = createRow("Position:", formPanel, gbc, 3);
        dobField = createRow("Date of Birth:", formPanel, gbc, 4);
        genderField = createRow("Gender:", formPanel, gbc, 5);
        statusField = createRow("Status:", formPanel, gbc, 6);
        infoField = createRow("Additional Information:", formPanel, gbc, 7);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Action Buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        actionPanel.setBackground(BACKGROUND_COLOR);
        JButton updateBtn = new JButton("Update");
        updateBtn.setPreferredSize(new Dimension(100, 30));
        updateBtn.setBackground(BUTTON_COLOR);
        updateBtn.setForeground(BUTTON_TEXT_COLOR);
        actionPanel.add(updateBtn);
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setPreferredSize(new Dimension(100, 30));
        cancelBtn.setBackground(BUTTON_COLOR);
        cancelBtn.setForeground(BUTTON_TEXT_COLOR);
        actionPanel.add(cancelBtn);
        mainPanel.add(actionPanel, BorderLayout.SOUTH);

        // Load existing data
        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Listeners
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateData();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancelBtn.addActionListener(e -> dispose());
    }

    private JTextField createRow(String labelText, JPanel panel, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        panel.add(label, gbc);

        gbc.gridx = 1;
        JTextField textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(200, 30));
        panel.add(textField, gbc);

        return textField;
    }

    private void readData() throws IOException {
        Properties props = new Properties();
        String path = "C:/Users/Manul Perera/IdeaProjects/Phoenix_Furnitures/Saved_Items/staff.properties";
        File file = new File(path);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                props.load(fis);
            }
        }
        nameField.setText(props.getProperty("Name", ""));
        emailField.setText(props.getProperty("Email", ""));
        contactField.setText(props.getProperty("Contact Number", ""));
        positionField.setText(props.getProperty("Position", ""));
        dobField.setText(props.getProperty("Date of Birth", ""));
        genderField.setText(props.getProperty("Gender", ""));
        statusField.setText(props.getProperty("Address", ""));
        infoField.setText(props.getProperty("Additional Information", ""));
    }

    private void updateData() throws IOException {
        Properties props = new Properties();
        String path = "C:/Users/Manul Perera/IdeaProjects/Phoenix_Furnitures/Saved_Items/staff.properties";
        File file = new File(path);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                props.load(fis);
            }
        }
        props.setProperty("Name", nameField.getText());
        props.setProperty("Email", emailField.getText());
        props.setProperty("Contact Number", contactField.getText());
        props.setProperty("Position", positionField.getText());
        props.setProperty("Date of Birth", dobField.getText());
        props.setProperty("Gender", genderField.getText());
        props.setProperty("Address", statusField.getText());
        props.setProperty("Additional Information", infoField.getText());
        props.store(new java.io.FileOutputStream(path), "Staff Details");
        JOptionPane.showMessageDialog(this, "Data updated successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            update frame = new update();
            frame.setVisible(true);
        });
    }
}
