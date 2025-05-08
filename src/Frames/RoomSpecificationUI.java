package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomSpecificationUI extends JFrame {
    private JTextField roomNumberField;
    private JTextField widthField;
    private JTextField heightField;
    private JTextField depthField;
    private JComboBox<String> shapeComboBox;
    private JButton floorColorButton, leftWallColorButton, frontWallColorButton, rightWallColorButton;
    private Color floorColor = Color.LIGHT_GRAY, leftWallColor = Color.LIGHT_GRAY,
            frontWallColor = Color.LIGHT_GRAY, rightWallColor = Color.LIGHT_GRAY;

    public RoomSpecificationUI() {
        setTitle("Room Designer");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Set application icon (with error handling)
        try {
            Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icon.png"));
            setIconImage(icon);
        } catch (Exception e) {
            System.out.println("Could not load application icon: " + e.getMessage());
        }

        // Main background panel with solid color
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(234, 233, 228));  // New background color
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        JLabel headerLabel = new JLabel("Room Specification Form");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerLabel.setForeground(new Color(50, 70, 90));
        headerPanel.add(headerLabel);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);

        // Main form panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 210, 220), 1),
                BorderFactory.createEmptyBorder(25, 40, 25, 40)
        ));
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Form fields
        addStyledLabeledField(mainPanel, gbc, 0, "Room Number:", roomNumberField = createStyledTextField());
        addStyledLabeledField(mainPanel, gbc, 1, "Width (cm):", widthField = createStyledTextField());
        addStyledLabeledField(mainPanel, gbc, 2, "Height (cm):", heightField = createStyledTextField());
        addStyledLabeledField(mainPanel, gbc, 3, "Depth (cm):", depthField = createStyledTextField());

        // Shape selection
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(createFormLabel("Shape:"), gbc);
        gbc.gridx = 1;
        shapeComboBox = new JComboBox<>(new String[]{"Square", "Rectangle"});
        shapeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        shapeComboBox.setBackground(Color.WHITE);
        shapeComboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 190, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(shapeComboBox, gbc);

        // Color buttons
        floorColorButton = createModernColorButton("Floor", floorColor);
        leftWallColorButton = createModernColorButton("Left Wall", leftWallColor);
        frontWallColorButton = createModernColorButton("Front Wall", frontWallColor);
        rightWallColorButton = createModernColorButton("Right Wall", rightWallColor);

        gbc.gridx = 0; gbc.gridy = 5; mainPanel.add(createFormLabel("Floor Color:"), gbc);
        gbc.gridx = 1; mainPanel.add(floorColorButton, gbc);
        gbc.gridx = 0; gbc.gridy = 6; mainPanel.add(createFormLabel("Left Wall Color:"), gbc);
        gbc.gridx = 1; mainPanel.add(leftWallColorButton, gbc);
        gbc.gridx = 0; gbc.gridy = 7; mainPanel.add(createFormLabel("Front Wall Color:"), gbc);
        gbc.gridx = 1; mainPanel.add(frontWallColorButton, gbc);
        gbc.gridx = 0; gbc.gridy = 8; mainPanel.add(createFormLabel("Right Wall Color:"), gbc);
        gbc.gridx = 1; mainPanel.add(rightWallColorButton, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JButton nextButton = createModernButton("Next", new Color(70, 130, 180));
        JButton cancelButton = createModernButton("Cancel", new Color(220, 80, 80));

        nextButton.addActionListener(e -> handleNext());
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(nextButton);
        buttonPanel.add(cancelButton);

        // Add components to background panel
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(mainPanel);
        backgroundPanel.add(centerWrapper, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(70, 90, 110));
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 190, 200)),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        field.setBackground(Color.WHITE);
        return field;
    }

    private void addStyledLabeledField(JPanel panel, GridBagConstraints gbc, int y, String label, JTextField field) {
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(createFormLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private JButton createModernColorButton(String label, Color initialColor) {
        JButton button = new JButton(label);
        button.setBackground(initialColor);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 190, 200), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> {
            // Create a modal JDialog containing the JColorChooser
            JColorChooser colorChooser = new JColorChooser(button.getBackground());
            JDialog dialog = JColorChooser.createDialog(
                    this, // parent component
                    "Choose " + label + " Color", // title
                    true, // modal
                    colorChooser,
                    evt -> { // OK button handler
                        Color chosenColor = colorChooser.getColor();
                        button.setBackground(chosenColor);
                        if (button == floorColorButton) floorColor = chosenColor;
                        else if (button == leftWallColorButton) leftWallColor = chosenColor;
                        else if (button == frontWallColorButton) frontWallColor = chosenColor;
                        else if (button == rightWallColorButton) rightWallColor = chosenColor;
                    },
                    null // Cancel button handler (null means just close)
            );
            dialog.setVisible(true);
        });
        return button;
    }

    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 45));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), 150), 1),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effects
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

    private void handleNext() {
        try {
            int roomNumber = Integer.parseInt(roomNumberField.getText());
            int width = Integer.parseInt(widthField.getText());
            int height = Integer.parseInt(heightField.getText());
            int depth = Integer.parseInt(depthField.getText());
            String shape = (String) shapeComboBox.getSelectedItem();

            DesignRoom roomDesigner = new DesignRoom(width, height, depth, roomNumber, shape,
                    floorColor, leftWallColor, frontWallColor, rightWallColor);
            roomDesigner.setVisible(true);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values for room dimensions and number.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Could not set system look and feel: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            RoomSpecificationUI frame = new RoomSpecificationUI();
            frame.setVisible(true);
        });
    }
}