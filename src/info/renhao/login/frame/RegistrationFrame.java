package info.renhao.login.frame;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Pattern;

import info.renhao.login.model.User;
import info.renhao.login.util.DBHelper;
import info.renhao.login.util.DocumentSizeFilter;
import info.renhao.login.util.DocumentSizeListener;
import info.renhao.login.util.SwingUtil;

public class RegistrationFrame extends JFrame{
    private static final long serialVersionUID = 2491294229716316338L;
    private JPanel contentPane;
    private JTextField usernameTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField emailTextField;
    private JLabel tipLabel = new JLabel();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Throwable e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(()->{
            try {
                RegistrationFrame frame = new RegistrationFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RegistrationFrame() {
        setTitle("\u7528\u6237\u6CE8\u518C");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        JPanel usernamePanel = new JPanel();
        contentPane.add(usernamePanel);

        JLabel usernameLabel = new JLabel("\u7528  \u6237  \u540D\uFF1A");
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        usernamePanel.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setToolTipText("\u8BF7\u8F93\u51655~15\u4E2A\u7531\u5B57" +
                "\u6BCD\u6570\u5B57\u4E0B\u5212\u7EBF\u7EC4\u6210\u7684\u5B57\u7B26\u4E32");
        AbstractDocument doc = (AbstractDocument) usernameTextField.getDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(15));
        doc.addDocumentListener(new DocumentSizeListener(tipLabel,15));
        usernameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        usernamePanel.add(usernameTextField);
        usernameTextField.setColumns(10);

        JPanel passwordPanel1 = new JPanel();
        contentPane.add(passwordPanel1);

        JLabel passwordLabel1 = new JLabel("\u8F93\u5165\u5BC6\u7801\uFF1A");
        passwordLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordPanel1.add(passwordLabel1);

        passwordField1 = new JPasswordField();
        doc = (AbstractDocument) passwordField1.getDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(20));
        doc.addDocumentListener(new DocumentSizeListener(tipLabel, 20));
        passwordField1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordField1.setColumns(10);
        passwordPanel1.add(passwordField1);

        JPanel passwordPanel2 = new JPanel();
        contentPane.add(passwordPanel2);

        JLabel passwordLabel2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
        passwordLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordPanel2.add(passwordLabel2);

        passwordField2 = new JPasswordField();
        doc = (AbstractDocument) passwordField2.getDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(20));
        doc.addDocumentListener(new DocumentSizeListener(tipLabel, 20));
        passwordField2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordField2.setColumns(10);
        passwordPanel2.add(passwordField2);

        JPanel emailPanel = new JPanel();
        contentPane.add(emailPanel);

        JLabel emailLabel = new JLabel("\u7535\u5B50\u90AE\u7BB1\uFF1A");
        emailLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        emailPanel.add(emailLabel);

        emailTextField = new JTextField();
        doc = (AbstractDocument) emailTextField.getDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(45));
        doc.addDocumentListener(new DocumentSizeListener(tipLabel, 45));
        emailTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        emailPanel.add(emailTextField);
        emailTextField.setColumns(10);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);

        JButton submitButton = new JButton("\u63D0\u4EA4");
        submitButton.addActionListener((ActionEvent e)->{
                do_submitButton_actionPerformed(e);
        });
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        tipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(tipLabel);

        Component glue = Box.createGlue();
        buttonPanel.add(glue);
        submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(submitButton);

        JButton cancelButton = new JButton("\u53D6\u6D88");
        cancelButton.addActionListener((ActionEvent e)-> {
            do_cancelButton_actionPerformed(e);
        });
        cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(cancelButton);

        pack();
        setLocation(SwingUtil.centreContainer(getSize()));
    }

    protected void do_submitButton_actionPerformed(ActionEvent e) {
        String username = usernameTextField.getText().trim();
        char[] password1 = passwordField1.getPassword();
        char[] password2 = passwordField2.getPassword();
        String email = emailTextField.getText().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(new String(password1).isEmpty()) {
            JOptionPane.showMessageDialog(this, "密码不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(new String(password2).isEmpty()) {
            JOptionPane.showMessageDialog(this, "确认密码不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "电子邮箱不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!Pattern.matches("\\w{5,15}", username)) {
            JOptionPane.showMessageDialog(this, "请输入合法的用户名！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!Arrays.equals(password1, password2)) {
            JOptionPane.showMessageDialog(this, "两次输入的密码不匹配！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!Pattern.matches("\\w+@\\w+\\.\\w+", email)) {
            JOptionPane.showMessageDialog(this, "请输入合法的电子邮箱！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (DBHelper.exists(username)) {
            JOptionPane.showMessageDialog(this, "用户名已存在！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(new String(password1));
        user.setEmail(email);
        Arrays.fill(password1, '0');
        Arrays.fill(password2, '0');
        if (DBHelper.save(user)) {
            JOptionPane.showMessageDialog(this, "用户注册成功！", "警告信息", JOptionPane.WARNING_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(this, "用户注册失败！", "警告信息", JOptionPane.WARNING_MESSAGE);
        }
    }
    protected void do_cancelButton_actionPerformed(ActionEvent e) {
        dispose();
    }
}
