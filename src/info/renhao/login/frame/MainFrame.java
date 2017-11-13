package info.renhao.login.frame;

import info.renhao.login.util.SwingUtil;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = -7360122511568073830L;
    private JPanel contentPane;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Throwable e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater( ()-> {
            try {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel tipLabel = new JLabel("恭喜您成功登陆系统！");
        tipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        contentPane.add(tipLabel, BorderLayout.CENTER);

        setLocation(SwingUtil.centreContainer(getSize()));
    }
}
