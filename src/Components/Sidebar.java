package Components;

import Frames.*;
import Frames.Staff.staffdetail;

import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(234, 233, 228);
    private static final Color BUTTON_COLOR = new Color(184, 155, 119);
    private static final Color SIDEBAR_COLOR = new Color(70, 70, 80);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 16);

    private static JLabel createIconLabel(ImageIcon icon) {
        JLabel label = new JLabel(icon);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return label;
    }

    private static JButton createButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(SIDEBAR_COLOR);
        button.setPreferredSize(new Dimension(width, height));
        button.setBorderPainted(false);
        button.setFont(BUTTON_FONT);
        return button;
    }

    JButton home, interior, kitchen, sell, user, account, logout, history;
    JPanel brandName, navPanel;
    JLabel nameLabel, imageLabel1, nameLabel2;

    public Sidebar() {
        setLayout(new BorderLayout());
        setBackground(SIDEBAR_COLOR);
        setPreferredSize(new Dimension(200, 650));

        // Brand-name panel
        brandName = new JPanel(new GridLayout(1, 2));
        brandName.setBackground(SIDEBAR_COLOR);

        ImageIcon image1 = new ImageIcon("src/Resources/images/newLogo.png");
        Image scaledImage = image1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        nameLabel = new JLabel("ABC");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(BUTTON_FONT);
        nameLabel2 = new JLabel("Furniture");
        nameLabel2.setForeground(Color.WHITE);
        nameLabel2.setFont(BUTTON_FONT);

        JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
        labelsPanel.setBackground(SIDEBAR_COLOR);
        labelsPanel.add(nameLabel);
        labelsPanel.add(nameLabel2);
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        imageLabel1 = new JLabel(scaledIcon);

        brandName.add(imageLabel1);
        brandName.add(labelsPanel);

        // Nav panel
        navPanel = new JPanel(new BorderLayout());
        navPanel.setBackground(SIDEBAR_COLOR);

        // Create buttons
        home = createButton("Home", 90, 30);
        interior = createButton("Room", 80, 30);
        kitchen = createButton("Furniture", 90, 30);
        history = createButton("History", 80, 30);
        user = createButton("Staff", 80, 30);
        logout = createButton("Logout", 80, 30);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(8, 1));
        buttonPanel.setBackground(SIDEBAR_COLOR);
        buttonPanel.add(home);
        buttonPanel.add(interior);
        buttonPanel.add(kitchen);
        buttonPanel.add(user);
        buttonPanel.add(history);
        buttonPanel.add(logout);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, -44, 20, 0));

        // Icons
        ImageIcon[] icons = {
                new ImageIcon("src/Resources/images/Home.png"),
                new ImageIcon("src/Resources/images/Interior.png"),
                new ImageIcon("src/Resources/images/Kitchen.png"),
                new ImageIcon("src/Resources/images/User.png"),

                new ImageIcon("src/Resources/images/history.png"),

                new ImageIcon("src/Resources/images/Logout.png")
        };

        JPanel iconPanel = new JPanel(new GridLayout(8, 1));
        iconPanel.setBackground(SIDEBAR_COLOR);
        iconPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        for (ImageIcon icon : icons) {
            Image scaledIconImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            iconPanel.add(createIconLabel(new ImageIcon(scaledIconImage)));
        }

        // Combine icons and buttons
        JPanel navContent = new JPanel(new GridLayout(1, 2));
        navContent.setBackground(SIDEBAR_COLOR);
        navContent.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        navContent.add(iconPanel);
        navContent.add(buttonPanel);

        navPanel.add(navContent);

        add(brandName, BorderLayout.NORTH);
        add(navPanel, BorderLayout.CENTER);

        // Action listeners
        home.addActionListener(e -> new HomeFrame().setVisible(true));
        interior.addActionListener(e -> new RoomDesigner().setVisible(true));
        kitchen.addActionListener(e -> new Furniture().setVisible(true));
        history.addActionListener(e -> new HistoryFrame().setVisible(true));
        logout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });
    }
}
