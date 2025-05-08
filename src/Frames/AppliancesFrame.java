package Frames;

import Components.ButtonUtils;
import Frames.FurniturePage.FunitureDetails;
import Frames.FurniturePage.Furniture;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppliancesFrame extends JFrame {

    private Furniture furnitureFrame = null;

    public AppliancesFrame() {
        super("ABC Furnitures - Appliances");;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🌿 Soft cozy background
        getContentPane().setBackground(new Color(234, 233, 228));

        // 🔝 Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(222, 217, 205));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Home");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(new Color(74, 74, 71));

        headerPanel.add(titleLabel, BorderLayout.WEST);

        // ➕ Add Button
        JButton addFurnitureButton = createRoundedButton("+ Add Furniture", new Color(184, 155, 119), Color.WHITE);
        addFurnitureButton.addActionListener(e -> {
            if (furnitureFrame == null || !furnitureFrame.isDisplayable()) {
                furnitureFrame = new Furniture();
                furnitureFrame.setVisible(true);
            } else {
                furnitureFrame.toFront();
                furnitureFrame.requestFocus();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setBackground(headerPanel.getBackground());
        buttonPanel.add(addFurnitureButton);
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // 🧱 Grid Panel
        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        gridPanel.setBackground(new Color(234, 233, 228));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] imagePaths = {
                "/Resources/images/chairwood.jpg",
                "/Resources/images/diningtable.jpg",
                "/Resources/images/mediumbed.jpg",
                "/Resources/images/cupboard1.jpg",
                "/Resources/images/Sofa.jpg",
                "/Resources/images/TVstand.jpg"
        };

        String[] labels = {"Wood chair", "Table", "Bed", "Cupboard", "Sofa", "TV"};

        for (int i = 0; i < imagePaths.length; i++) {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePaths[i]));

            JButton imageButton = ButtonUtils.createImageButton(icon, null, e -> {
                FunitureDetails details = new FunitureDetails();
                details.setVisible(true);
            });

            imageButton.setBackground(new Color(247, 246, 242));
            imageButton.setBorder(CustomBorderFactory.createRoundedBorder(10));

            JLabel label = new JLabel(labels[i], SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.PLAIN, 14));
            label.setForeground(new Color(74, 74, 71));

            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(new Color(247, 246, 242));
            itemPanel.setBorder(CustomBorderFactory.createRoundedBorder(10));
            itemPanel.add(imageButton, BorderLayout.CENTER);
            itemPanel.add(label, BorderLayout.SOUTH);

            gridPanel.add(itemPanel);
        }

        add(gridPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);

    }

    private JButton createRoundedButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorder(CustomBorderFactory.createRoundedBorder(12));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppliancesFrame frame = new AppliancesFrame();
            frame.setVisible(true);  // Only set visible here
        });
    }

}
