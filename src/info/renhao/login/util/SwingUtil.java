package info.renhao.login.util;

import java.awt.*;

public class SwingUtil {
    public static Point centreContainer(Dimension size) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - size.width)/2;
        int y = (screenSize.height - size.height)/2;
        return new Point(x, y);
    }
}
