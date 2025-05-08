package Frames.FurniturePage;

import Components.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EditPage extends JFrame {

    private JTextField nameField, priceField, sizeField, dateField, descField;

    EditPage() {
        Sidebar sidebar = new Sidebar();

        // Colors
        Color backgroundColor = new Color(234, 233, 228);
        Color buttonColor = new Color(184, 155, 119);

        // Panel containing top heading
        JPanel buttonpanel = new JPanel(new BorderLayout());
        buttonpanel.setPreferredSize(new Dimension(9000, 1000));
        buttonpanel.setBackground(backgroundColor);

        JPanel hiPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        hiPanel.setBackground(backgroundColor);
        JLabel upLabel = new JLabel("Edit Furniture");
        upLabel.setFont(upLabel.getFont().deriveFont(Font.BOLD, 24f));
        hiPanel.add(upLabel);


        buttonpanel.add(hiPanel, BorderLayout.NORTH);

        // Image and Buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.setBackground(backgroundColor);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 70)));

        ImageIcon editIcon = new ImageIcon(getClass().getResource("/Resources/images/edit1.png"));
        Image editImg = editIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JButton addbtn = new JButton(new ImageIcon(editImg));
        addbtn.setPreferredSize(new Dimension(700, 200));
        addbtn.setOpaque(false);
        addbtn.setContentAreaFilled(false);
        addbtn.setBorderPainted(false);
        buttonsPanel.add(addbtn);

        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 90)));

        JButton savebtn = new JButton("Update");
        savebtn.setPreferredSize(new Dimension(250, 60));
        savebtn.setBackground(buttonColor);
        savebtn.setForeground(Color.WHITE);
        savebtn.setFont(savebtn.getFont().deriveFont(19f));
        buttonsPanel.add(savebtn);

        JButton cancelbtn = new JButton("Cancel");
        cancelbtn.setPreferredSize(new Dimension(250, 60));
        cancelbtn.setBackground(buttonColor);
        cancelbtn.setForeground(Color.WHITE);
        cancelbtn.setFont(cancelbtn.getFont().deriveFont(19f));
        buttonsPanel.add(cancelbtn);

        buttonpanel.add(buttonsPanel, BorderLayout.CENTER);
        getContentPane().add(buttonpanel, BorderLayout.CENTER);

        // Details form panel
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 20, 10, 50);
        detailsPanel.setBackground(backgroundColor);

        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(50);
        nameField.setPreferredSize(new Dimension(200, 30));
        detailsPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        detailsPanel.add(new JLabel("Price:"), gbc);

        gbc.gridx = 1;
        priceField = new JTextField(50);
        priceField.setPreferredSize(new Dimension(200, 30));
        detailsPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        detailsPanel.add(new JLabel("Size:"), gbc);

        gbc.gridx = 1;
        sizeField = new JTextField(50);
        sizeField.setPreferredSize(new Dimension(200, 30));
        detailsPanel.add(sizeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        detailsPanel.add(new JLabel("Date:"), gbc);

        gbc.gridx = 1;
        dateField = new JTextField(50);
        dateField.setPreferredSize(new Dimension(200, 30));
        detailsPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        detailsPanel.add(new JLabel("Description:"), gbc);

        gbc.gridx = 1;
        descField = new JTextField(50);
        descField.setPreferredSize(new Dimension(300, 30));
        detailsPanel.add(descField, gbc);

        detailsPanel.setPreferredSize(new Dimension(800, 100));
        getContentPane().add(detailsPanel, BorderLayout.EAST);

        // Sidebar
        this.add(sidebar, BorderLayout.WEST);

        // Read existing data
        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save button action
        savebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateData();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Cancel button closes the window
        cancelbtn.addActionListener(e -> dispose());
    }

    private void readData() throws IOException {
        Properties properties = new Properties();
        String filePath = "C:/Users/Manul Perera/IdeaProjects/Phoenix_Furnitures/Saved_Items/furniture.properties";
        File file = new File(filePath);

        if (file.exists()) {
            try (FileInputStream fileInput = new FileInputStream(file)) {
                properties.load(fileInput);
            }
        }

        nameField.setText(properties.getProperty("Name", ""));
        priceField.setText(properties.getProperty("Price", ""));
        sizeField.setText(properties.getProperty("Size", ""));
        dateField.setText(properties.getProperty("Date", ""));
        descField.setText(properties.getProperty("Description", ""));
    }

    private void updateData() throws IOException {
        Properties properties = new Properties();
        String filePath = "C:/Users/Manul Perera/IdeaProjects/Phoenix_Furnitures/Saved_Items/furniture.properties";
        File file = new File(filePath);

        if (file.exists()) {
            try (FileInputStream fileInput = new FileInputStream(file)) {
                properties.load(fileInput);
            }
        }

        properties.setProperty("Name", nameField.getText());
        properties.setProperty("Price", priceField.getText());
        properties.setProperty("Size", sizeField.getText());
        properties.setProperty("Date", dateField.getText());
        properties.setProperty("Description", descField.getText());

        try (FileOutputStream fileOutput = new FileOutputStream(filePath)) {
            properties.store(fileOutput, "Furniture Details");
            JOptionPane.showMessageDialog(this, "Data updated successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EditPage editPage = new EditPage();
        editPage.setTitle("Phoenix Furniture");
        editPage.setSize(990, 950);
        editPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editPage.setLocationRelativeTo(null);
        editPage.setVisible(true);
    }
}
