package Frames;

import javax.swing.border.Border;
import java.awt.*;
import javax.swing.*;

public class CustomBorderFactory {

    public static Border createRoundedBorder(int radius) {
        return new RoundedBorder(radius);
    }

    private static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.GRAY);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}
