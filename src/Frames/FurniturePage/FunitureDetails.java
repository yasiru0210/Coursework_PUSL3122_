package Frames.FurniturePage;

import javax.swing.*;
import java.awt.*;

public class FunitureDetails extends JFrame {
    private JLabel nameValue, dateValue, priceValue, sizeValue, categoryValue, descValue;
    private final Color backgroundColor = new Color(234, 233, 228);
    private final Color buttonColor = new Color(184, 155, 119);

    public FunitureDetails() {
        super("Furniture Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top toolbar with actions
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(backgroundColor);

        JButton editBtn = makeIconButton("/Resources/images/Edit.png", "Edit");
        editBtn.addActionListener(e -> {
            new EditPage().setVisible(true);
            dispose();
        });
        toolBar.add(editBtn);

        JButton deleteBtn = makeIconButton("/Resources/images/Delete.png", "Delete");
        deleteBtn.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete?", "Confirm",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Item deleted.");
                dispose();
            }
        });
        toolBar.add(deleteBtn);

        add(toolBar, BorderLayout.NORTH);

        // Split pane: image on left, details on right
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setBackground(backgroundColor);
        splitPane.setDividerLocation(350);

        // Image panel
        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setBackground(backgroundColor);
        ImageIcon prodIcon = new ImageIcon(getClass().getResource("/Resources/images/chair2.png"));
        Image prodImg = prodIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel prodLabel = new JLabel(new ImageIcon(prodImg));
        prodLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgPanel.add(prodLabel, BorderLayout.CENTER);
        splitPane.setLeftComponent(imgPanel);

        // Details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(backgroundColor);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameValue = addDetail(detailsPanel, "Name:", "Wood Chair");
        dateValue = addDetail(detailsPanel, "Date:", "23/04/2023");
        priceValue = addDetail(detailsPanel, "Price:", "Rs 50,000");
        sizeValue = addDetail(detailsPanel, "Size:", "6 x 4 ft");
        categoryValue = addDetail(detailsPanel, "Category:", "Chair");
        descValue = addDetail(detailsPanel, "Description:", "Comfortable chair.");

        // Wrap details in scroll pane
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(backgroundColor);
        splitPane.setRightComponent(scrollPane);

        add(splitPane, BorderLayout.CENTER);
    }

    private JLabel addDetail(JComponent parent, String labelText, String valueText) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(backgroundColor);
        JLabel label = new JLabel(labelText);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        JLabel value = new JLabel(valueText);
        row.add(label, BorderLayout.WEST);
        row.add(value, BorderLayout.EAST);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        parent.add(row);
        parent.add(Box.createRigidArea(new Dimension(0, 10)));
        return value;
    }

    private JButton makeIconButton(String path, String tooltip) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton btn = new JButton(new ImageIcon(img));
        btn.setToolTipText(tooltip);
        btn.setBackground(buttonColor);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(60, 60));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FunitureDetails().setVisible(true));
    }
}
