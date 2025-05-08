package Frames.Staff;

import Components.Sidebar;
import Frames.User.addstaff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class staffdetail extends JFrame {
    private final Color BACKGROUND_COLOR = new Color(234, 233, 228);
    private final Color BUTTON_COLOR = new Color(184, 155, 119);
    private final Color HEADER_COLOR = new Color(70, 70, 80);
    private final Color TEXT_COLOR = new Color(60, 60, 60);
    private final Color CARD_COLOR = Color.WHITE;

    // Simulating local storage with a Map
    private Map<String, String> localStorage = new HashMap<>();

    public staffdetail() {
        // Set frame properties
        setTitle("Staff Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout());

        // Populating initial staff details
        populateInitialStaffDetails();

        // Main panel setup
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(BACKGROUND_COLOR);
        masterPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        add(masterPanel, BorderLayout.CENTER);

        // Header label
        JLabel supplierLabel = new JLabel("Staff Members");
        supplierLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        supplierLabel.setForeground(TEXT_COLOR);
        supplierLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        masterPanel.add(supplierLabel);

        // Add new staff button
        JButton addButton = createStyledButton("+ Add New Staff", BUTTON_COLOR);
        addButton.addActionListener(e -> {
            new addstaff().setVisible(true);
        });
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        masterPanel.add(addButton);
        masterPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Staff cards panel
        JPanel scrollablePanel = new JPanel();
        scrollablePanel.setLayout(new GridLayout(0, 1, 0, 15));
        scrollablePanel.setBackground(BACKGROUND_COLOR);

        // Simulating staff keys for dynamic staff addition
        String[] staffKeys = {"1744800232923", "1744800302055"};

        // Loop through staff keys and display details
        for (String staffKey : staffKeys) {
            String staffName = getStaffName(staffKey);
            JPanel cardPanel = createStaffCard(staffKey, staffName);
            scrollablePanel.add(cardPanel);
        }

        // Scroll pane setup
        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        masterPanel.add(scrollPane);
    }

    private JPanel createStaffCard(String staffKey, String staffName) {
        JPanel cardPanel = new JPanel(new BorderLayout(15, 0));
        cardPanel.setBackground(CARD_COLOR);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Profile picture
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/Resources/images/Male.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(imageIcon);
        cardPanel.add(imageLabel, BorderLayout.WEST);

        // Staff name button
        JButton nameButton = new JButton(staffName);
        nameButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameButton.setForeground(TEXT_COLOR);
        nameButton.setBackground(CARD_COLOR);
        nameButton.setBorderPainted(false);
        nameButton.setHorizontalAlignment(SwingConstants.LEFT);
        nameButton.addActionListener(e -> new member().setVisible(true));
        cardPanel.add(nameButton, BorderLayout.CENTER);

        // Action buttons panel
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setBackground(CARD_COLOR);

        // Edit button
        JButton editButton = createIconButton("src/Resources/images/Edit.png", "Edit", BUTTON_COLOR);
        editButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Edit this staff member?", "Confirmation",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                new update().setVisible(true);
            }
        });
        actionPanel.add(editButton);

        // Delete button
        JButton deleteButton = createIconButton("src/Resources/images/Delete.png", "Delete", new Color(200, 100, 100));
        deleteButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Delete this staff member?", "Confirmation",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Staff member deleted");
            }
        });
        actionPanel.add(deleteButton);

        cardPanel.add(actionPanel, BorderLayout.EAST);

        return cardPanel;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker(), 1),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private JButton createIconButton(String iconPath, String tooltip, Color bgColor) {
        JButton button = new JButton();
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            button.setText(tooltip);
        }

        button.setToolTipText(tooltip);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(40, 40));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    // Method to simulate fetching staff name using the staffKey from local storage (Map)
    private String getStaffName(String staffKey) {
        return localStorage.getOrDefault("Staff_" + staffKey + "_Name", "Unknown Staff");
    }

    // Method to populate initial staff details in the "localStorage"
    private void populateInitialStaffDetails() {
        // Simulating storing staff details in "localStorage"
        localStorage.put("Staff_1744800232923_Name", "Ben Rodric Fernando");
        localStorage.put("Staff_1744800302055_Name", "Alex Rodric Fernando");
        localStorage.put("Staff_1744800232923_Position", "IT");
        localStorage.put("Staff_1744800302055_Position", "Maths");
        localStorage.put("Staff_1744800232923_Address", "112/2, Gamunu street, Dehiwala");
        localStorage.put("Staff_1744800302055_Address", "112/2, flower street, Dehiwala");
        localStorage.put("Staff_1744800232923_Contact Number", "0715986366");
        localStorage.put("Staff_1744800302055_Contact Number", "0715986350");
        localStorage.put("Staff_1744800232923_Email", "benfernando@hotmail.com");
        localStorage.put("Staff_1744800302055_Email", "alexfernando@hotmail.com");
        localStorage.put("Staff_1744800232923_Date of Birth", "05/06/1998");
        localStorage.put("Staff_1744800302055_Date of Birth", "05/08/1998");
        localStorage.put("Staff_1744800232923_Gender", "Male");
        localStorage.put("Staff_1744800302055_Gender", "Male");
        localStorage.put("Staff_1744800232923_Description", "Test");
        localStorage.put("Staff_1744800302055_Description", "Test");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            staffdetail frame = new staffdetail();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });
    }
}