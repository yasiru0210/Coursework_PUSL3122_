package Frames;

import Frames.FurniturePage.FunitureDetails;
import Frames.Staff.staffdetail;
import Frames.Suppliers.add;
import Frames.User.userdetails;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class HomeFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel sidebar;
    private final Color BACKGROUND_COLOR = new Color(234, 233, 228);
    private final Color BUTTON_COLOR = new Color(184, 155, 119);
    private final Color SIDEBAR_COLOR = new Color(70, 70, 80); // Darker sidebar for contrast
    private final Color TEXT_COLOR = new Color(60, 60, 60);

    public HomeFrame() {
        setTitle("ABC Furniture - Home");
        setSize(1077, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(BACKGROUND_COLOR);

        sidebar = createSidebar();
        initializeContentPanels();

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "Home");
    }

    private void initializeContentPanels() {
        AppliancesFrame appliancesFrame = new AppliancesFrame();
        contentPanel.add(appliancesFrame.getContentPane(), "Home");

        RoomSpecificationUI roomSpecificationUI = new RoomSpecificationUI();
        contentPanel.add(roomSpecificationUI.getContentPane(), "RoomDesigner");

        staffdetail staff = new staffdetail();
        contentPanel.add(staff.getContentPane(), "Staff");

        FunitureDetails funitureDetails = new FunitureDetails();
        contentPanel.add(funitureDetails.getContentPane(), "Furnitures");

        HistoryFrame historyFrame = new HistoryFrame();
        contentPanel.add(historyFrame.getContentPane(), "History");


        LoginFrame loginFrame = new LoginFrame();
        contentPanel.add(loginFrame.getContentPane(), "Logout");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BorderLayout());
        sidebar.setBackground(SIDEBAR_COLOR);
        sidebar.setPreferredSize(new Dimension(220, 650));

        // Top logo section
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setBackground(SIDEBAR_COLOR);

        ImageIcon logoIcon = scaleImageIcon(new ImageIcon(getClass().getResource("/Resources/images/logo.jpg")), 50, 50);
        JLabel logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 10));

        JLabel nameLabel = new JLabel("<html><div style='text-align:center;'>ABC<br/>Furniture</div></html>");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel logoAndNamePanel = new JPanel(new BorderLayout());
        logoAndNamePanel.setBackground(SIDEBAR_COLOR);
        logoAndNamePanel.add(logoLabel, BorderLayout.WEST);
        logoAndNamePanel.add(nameLabel, BorderLayout.CENTER);

        logoPanel.add(logoAndNamePanel, BorderLayout.NORTH);
        logoPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 10));
        sidebar.add(logoPanel, BorderLayout.NORTH);

        // Navigation buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(SIDEBAR_COLOR);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        String[] labels = {
                "Home", "RoomDesigner", "Furnitures", "History", "Staff", "Logout"
        };
        String[] icons = {
                "/Resources/images/icon-home.png",
                "/Resources/images/icon-roomdesigner.png",
                "/Resources/images/icon-furniture.png",
                "/Resources/images/history.png",
                "/Resources/images/icon-staff.png",
                "/Resources/images/supplierIcon.png",
                "/Resources/images/icon-account.png",
                "/Resources/images/icon-logout.png"
        };

        for (int i = 0; i < labels.length; i++) {
            JButton button = createSidebarButton(labels[i], icons[i]);
            final String label = labels[i];

            if (label.equals("Logout")) {
                button.addActionListener(e -> {
                    dispose();
                    new LoginFrame().setVisible(true);
                });
            } else {
                button.addActionListener(e -> cardLayout.show(contentPanel, label));
            }

            buttonsPanel.add(Box.createVerticalStrut(12));
            buttonsPanel.add(button);
        }

        sidebar.add(buttonsPanel, BorderLayout.CENTER);
        return sidebar;
    }

    private JButton createSidebarButton(String text, String iconPath) {
        final int ICON_SIZE = 24;
        ImageIcon icon = new ImageIcon(new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB));

        URL iconURL = getClass().getResource(iconPath);
        if (iconURL != null) {
            icon = new ImageIcon(new ImageIcon(iconURL).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH));
        }

        JButton button = new JButton(text, icon);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 45));
        button.setPreferredSize(new Dimension(200, 45));
        button.setForeground(Color.WHITE);
        button.setBackground(SIDEBAR_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(15);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
                button.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(SIDEBAR_COLOR);
                button.setForeground(Color.WHITE);
            }
        });

        return button;
    }

    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            HomeFrame homeFrame = new HomeFrame();
            homeFrame.setVisible(true);
        });
    }
}