package Frames;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

public class HistoryFrame extends JFrame {
    private JTable historyTable;
    private DefaultTableModel tableModel;
    private final Color BACKGROUND_COLOR = new Color(234, 233, 228);
    private final Color BUTTON_COLOR = new Color(184, 155, 119);
    private final Color HEADER_COLOR = new Color(70, 70, 80);
    private final Color TEXT_COLOR = new Color(60, 60, 60);

    public HistoryFrame() {
        super("Room History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        // Toolbar with title and buttons
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBackground(BACKGROUND_COLOR);
        toolBar.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        JLabel titleLabel = new JLabel("Room History");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(HEADER_COLOR);  // Updated to use (70, 70, 80)
        toolBar.add(titleLabel);
        toolBar.add(Box.createHorizontalStrut(30));

        JButton viewBtn = createStyledButton("View Design", BUTTON_COLOR);
        viewBtn.addActionListener(this::onViewDesign);
        toolBar.add(viewBtn);

        toolBar.add(Box.createHorizontalGlue());

        JButton editBtn = createIconButton("/Resources/images/Edit.png", "Edit", BUTTON_COLOR);
        editBtn.addActionListener(e -> onEditSelected());
        toolBar.add(editBtn);

        toolBar.add(Box.createHorizontalStrut(10));

        JButton deleteBtn = createIconButton("/Resources/images/Delete.png", "Delete", new Color(200, 100, 100));
        deleteBtn.addActionListener(e -> onDeleteSelected());
        toolBar.add(deleteBtn);

        add(toolBar, BorderLayout.NORTH);

        // Table setup
        String[] columns = {"Preview", "Room #", "Designer", "Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        historyTable = new JTable(tableModel);
        historyTable.setRowHeight(80);
        historyTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        historyTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        historyTable.getTableHeader().setBackground(HEADER_COLOR);
        historyTable.getTableHeader().setForeground(Color.WHITE);
        historyTable.setSelectionBackground(BUTTON_COLOR);
        historyTable.setSelectionForeground(Color.WHITE);
        historyTable.setGridColor(new Color(200, 200, 200));
        historyTable.setBackground(Color.WHITE);
        historyTable.setFillsViewportHeight(true);
        historyTable.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());

        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // Load data
        loadSavedRooms();

        // Double-click row to view design
        historyTable.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) onViewDesign(null);
            }
        });
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

    private JButton createIconButton(String resourcePath, String tooltip, Color bgColor) {
        JButton btn;
        URL resUrl = getClass().getResource(resourcePath);

        if (resUrl != null) {
            ImageIcon icon = new ImageIcon(resUrl);
            Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            btn = new JButton(new ImageIcon(img));
        } else {
            btn = new JButton(tooltip);
        }

        btn.setToolTipText(tooltip);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(true);
        btn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btn.setPreferredSize(new Dimension(50, 50));
        btn.setFocusPainted(false);

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor);
            }
        });

        return btn;
    }

    private void loadSavedRooms() {
        // TODO: replace with real parsing logic
        for (int i = 1; i <= 5; i++) {
            ImageIcon icon = loadIcon("/Resources/images/room" + i + ".png", 100, 60);
            Vector<Object> row = new Vector<>();
            row.add(icon);
            row.add("Room " + i);
            row.add("Admin");
            row.add("01/01/2024");
            tableModel.addRow(row);
        }
    }

    private ImageIcon loadIcon(String resourcePath, int w, int h) {
        try {
            URL resUrl = getClass().getResource(resourcePath);
            if (resUrl != null) {
                Image img = ImageIO.read(resUrl).getScaledInstance(w, h, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
        } catch (IOException ignored) {}
        // fallback to empty icon if resource missing
        return new ImageIcon(new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB));
    }

    private void onViewDesign(ActionEvent e) {
        int row = historyTable.getSelectedRow();
        if (row >= 0) {
            new EditRoomDesigner(null).setVisible(true);
        }
    }

    private void onEditSelected() {
        int row = historyTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a room first.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            new EditRoomDesigner(null).setVisible(true);
        }
    }

    private void onDeleteSelected() {
        int row = historyTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a room first.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (JOptionPane.showConfirmDialog(this,
                "Delete selected room?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            tableModel.removeRow(row);
        }
    }

    // Renders the preview image
    private static class ImageRenderer extends JLabel implements TableCellRenderer {
        public ImageRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setIcon((ImageIcon) value);
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new HistoryFrame().setVisible(true);
        });
    }
}