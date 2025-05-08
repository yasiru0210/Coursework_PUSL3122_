package Frames.FurniturePage;

import Components.Sidebar;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Furniture extends JFrame {
    private final Color BACKGROUND_COLOR = new Color(234, 233, 228);
    private final Color BUTTON_COLOR = new Color(184, 155, 119);
    private final Color CARD_COLOR = Color.WHITE;
    private final Color TEXT_COLOR = new Color(60, 60, 60);

    private File selectedImageFile;
    private String enteredName;
    private JLabel imagePreviewLabel;
    private JComboBox<String> categoryField;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField sizeField;
    private JTextField dateField;
    private JTextArea descField;

    public Furniture() {
        setTitle("Furniture Management");
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Add sidebar
        Sidebar sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(BACKGROUND_COLOR);
        JLabel titleLabel = new JLabel("Add New Furniture");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_COLOR);
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Form container
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Form card
        JPanel formCard = new JPanel(new GridBagLayout());
        formCard.setBackground(CARD_COLOR);
        formCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        // Form fields
        addFormField(formCard, "Category", 0);
        String[] categories = {"Chair", "Table", "Sofa", "Bed", "Desk"};
        categoryField = createStyledComboBox(categories);
        addComponent(formCard, categoryField, 1, 0);

        addFormField(formCard, "Name", 1);
        nameField = createStyledTextField();
        addComponent(formCard, nameField, 1, 1);

        addFormField(formCard, "Price", 2);
        priceField = createStyledTextField();
        addComponent(formCard, priceField, 1, 2);

        addFormField(formCard, "Size", 3);
        sizeField = createStyledTextField();
        addComponent(formCard, sizeField, 1, 3);

        addFormField(formCard, "Date", 4);
        dateField = createStyledTextField();
        addComponent(formCard, dateField, 1, 4);

        addFormField(formCard, "Description", 5);
        descField = new JTextArea(3, 20);
        descField.setLineWrap(true);
        descField.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descField);
        descScroll.setPreferredSize(new Dimension(300, 80));
        descScroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        addComponent(formCard, descScroll, 1, 5);

        // Image upload section
        addFormField(formCard, "Image", 6);
        JPanel imageUploadPanel = new JPanel(new BorderLayout(10, 10));
        imageUploadPanel.setBackground(CARD_COLOR);

        JButton uploadBtn = createStyledButton("Upload Image", BUTTON_COLOR);
        uploadBtn.setPreferredSize(new Dimension(180, 40));

        imagePreviewLabel = new JLabel("", SwingConstants.CENTER);
        imagePreviewLabel.setPreferredSize(new Dimension(250, 250));
        imagePreviewLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        imagePreviewLabel.setBackground(new Color(240, 240, 240));
        imagePreviewLabel.setOpaque(true);

        imageUploadPanel.add(uploadBtn, BorderLayout.NORTH);
        imageUploadPanel.add(imagePreviewLabel, BorderLayout.CENTER);
        addComponent(formCard, imageUploadPanel, 1, 6);

        // Add form card to container
        gbc.gridx = 0;
        gbc.gridy = 0;
        formContainer.add(formCard, gbc);
        mainPanel.add(formContainer, BorderLayout.CENTER);

        // Action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        JButton saveBtn = createActionButton("Save", BUTTON_COLOR);
        JButton deleteBtn = createActionButton("Clear", new Color(200, 100, 100));

        buttonPanel.add(saveBtn);
        buttonPanel.add(deleteBtn);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        // Event handlers
        uploadBtn.addActionListener(e -> handleImageUpload());
        saveBtn.addActionListener(e -> handleSave());
        deleteBtn.addActionListener(e -> handleClear());

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void addFormField(JPanel panel, String label, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 5, 15);

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(TEXT_COLOR);
        panel.add(lbl, gbc);
    }

    private void addComponent(JPanel panel, JComponent comp, int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(comp, gbc);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(300, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setPreferredSize(new Dimension(300, 35));
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return combo;
    }

    private JButton createActionButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 45));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker(), 1),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
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

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker(), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
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

    private void handleImageUpload() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = fileChooser.getSelectedFile();
            imagePreviewLabel.setText("");
            imagePreviewLabel.setIcon(new ImageIcon(
                    new ImageIcon(selectedImageFile.getPath()).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)
            ));
        }
    }

    private void handleSave() {
        enteredName = nameField.getText();
        try {
            saveData();
            JOptionPane.showMessageDialog(this, "Furniture saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving furniture: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleClear() {
        int option = JOptionPane.showConfirmDialog(this,
                "Clear all fields?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            categoryField.setSelectedIndex(0);
            nameField.setText("");
            priceField.setText("");
            sizeField.setText("");
            dateField.setText("");
            descField.setText("");
            imagePreviewLabel.setIcon(null);
            imagePreviewLabel.setText("");
            selectedImageFile = null;
        }
    }

    private void saveData() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("name", nameField.getText());
        properties.setProperty("category", (String) categoryField.getSelectedItem());
        properties.setProperty("price", priceField.getText());
        properties.setProperty("size", sizeField.getText());
        properties.setProperty("date", dateField.getText());
        properties.setProperty("description", descField.getText());

        File file = new File("furniture.properties");
        try (FileOutputStream out = new FileOutputStream(file)) {
            properties.store(out, "Furniture Details");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Furniture::new);
    }
}
