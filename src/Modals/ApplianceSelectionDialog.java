package Modals;

import javax.swing.*;
import java.awt.*;

public class ApplianceSelectionDialog extends JDialog {
    private JToggleButton[] toggleButtons;

    public ApplianceSelectionDialog(JFrame parentFrame) {
        super(parentFrame, "Select the category", true);
        setLayout(new BorderLayout());
        setSize(600, 400);
        setLocationRelativeTo(parentFrame);
        initializeUI();
    }

    private void initializeUI() {
        // Panel that will contain the grid of toggle buttons
        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String[] imagePaths = {
                "src/main/resources/images/Appliances/chair.png",
                "src/main/resources/images/Appliances/bed.png",
                "src/main/resources/images/Appliances/cupboard.png",

        };
        toggleButtons = new JToggleButton[imagePaths.length];

        // Create toggle buttons for appliance selection
        for (int i = 0; i < imagePaths.length; i++) {
            ImageIcon icon = new ImageIcon(imagePaths[i]);
            Image image = icon.getImage();

            Image newimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            toggleButtons[i] = new JToggleButton(icon);
//            toggleButtons[i] = new JToggleButton(icon);
//            toggleButtons[i].setPreferredSize(new Dimension(100, 100)); // Adjust as needed
            gridPanel.add(toggleButtons[i]);
        }

        // Add the grid panel to the dialog
        add(gridPanel, BorderLayout.CENTER);

        // Create the done button to close the dialog
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(e -> {
            // You can process selected items here
            setVisible(false);
            dispose();
        });
        add(doneButton, BorderLayout.SOUTH);
    }

    // Method to show the dialog and return the selected items
    public boolean[] showDialog() {
        setVisible(true);
        boolean[] selectedItems = new boolean[toggleButtons.length];
        for (int i = 0; i < toggleButtons.length; i++) {
            selectedItems[i] = toggleButtons[i].isSelected();
        }
        return selectedItems;
    }
}
