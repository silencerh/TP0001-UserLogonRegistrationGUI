package info.renhao.login.util;

import org.apache.commons.lang.math.RandomUtils;

import javax.swing.*;
import java.awt.*;

public class CAPTCHALabel extends JLabel {
    private static final long serialVersion = -963570191302783615L;
    private String text;
    private Color[] colors = { Color.BLACK, Color.DARK_GRAY, Color.BLUE, Color.CYAN,
            Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
            Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
    };
    public CAPTCHALabel(String text) {
        this.text = text;
        setPreferredSize(new Dimension(60, 36));
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        for (int i = 0; i < text.length(); i++) {
            g.setColor(colors[RandomUtils.nextInt(colors.length)]);
            g.drawString("" + text.charAt(i), + 5 + i*13, 25);
        }
    }
}
