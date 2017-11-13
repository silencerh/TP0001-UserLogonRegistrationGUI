package info.renhao.login.frame;

import info.renhao.login.util.CAPTCHALabel;
import info.renhao.login.util.DBHelper;
import info.renhao.login.util.SwingUtil;
import org.apache.commons.lang.RandomStringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = -4655235896173916415L;
    private JPanel contentPane;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JTextField validateTextField;
    private String randomText;

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Throwable e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(()->{
            try {
                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    LoginFrame() {
        setTitle("\u7CFB\u7EDF\u767B\u5F55");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        JPanel usernamePanel = new JPanel();
        contentPane.add(usernamePanel);

        JLabel usernameLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        usernamePanel.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        usernamePanel.add(usernameTextField);
        usernameTextField.setColumns(10);

        JPanel passwordPanel = new JPanel();
        contentPane.add(passwordPanel);

        JLabel passwordLabel = new JLabel("\u5BC6    \u7801\uFF1A");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordPanel.add(passwordField);

        JPanel validatePanel = new JPanel();
        contentPane.add(validatePanel);

        JLabel validateLabel = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
        validateLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        validatePanel.add(validateLabel);

        validateTextField = new JTextField();
        validateTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        validatePanel.add(validateTextField);
        validateTextField.setColumns(5);

        randomText = RandomStringUtils.randomAlphanumeric(4);
        CAPTCHALabel label = new CAPTCHALabel(randomText);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        validatePanel.add(label);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);

        JButton submitButton = new JButton("\u767B\u5F55");
        submitButton.addActionListener((ActionEvent e) -> {
                do_submit_actionPerformed(e);
        });
        submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(submitButton);

        JButton cancelButton = new JButton("\u9000\u51FA");
        cancelButton.addActionListener((ActionEvent e) -> {
                do_cancelButton_actionPerformed(e);
        });
        cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(cancelButton);

        pack();
        setLocation(SwingUtil.centreContainer(getSize()));

    }

    protected void do_submit_actionPerformed(ActionEvent e) {
        String username = usernameTextField.getText().trim();
        char[] password = passwordField.getPassword();
        String validate = validateTextField.getText().trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(new String(password).isEmpty()) {
            JOptionPane.showMessageDialog(this, "密码不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (validate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "验证码不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!DBHelper.exists(username)) {
            JOptionPane.showMessageDialog(this, "用户名不存在！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(!DBHelper.check(username, password)) {
            JOptionPane.showMessageDialog(this, "密码错误！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(!validate.equals(randomText)) {
            JOptionPane.showMessageDialog(this, "验证码错误！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }

        EventQueue.invokeLater(()-> {
            try {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        });
        dispose();
    }

    protected void do_cancelButton_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
