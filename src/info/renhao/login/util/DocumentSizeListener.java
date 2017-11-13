package info.renhao.login.util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;

public class DocumentSizeListener implements DocumentListener {
    private JLabel tipLabel;
    private int maxSize;

    public DocumentSizeListener(JLabel tipLabel, int maxSize) {
        this.tipLabel = tipLabel;
        this.maxSize = maxSize;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        setTipText(e);
    }
    @Override
    public void removeUpdate(DocumentEvent e) {
        setTipText(e);
    }
    @Override
    public void changedUpdate(DocumentEvent e) {
        setTipText(e);
    }

    private void setTipText(DocumentEvent e) {
        Document doc = e.getDocument();
        tipLabel.setForeground(Color.BLACK);
        if (doc.getLength() > (maxSize *4 /5)) {
            tipLabel.setForeground(Color.RED);
        }
        else {
            tipLabel.setForeground(Color.BLACK);
        }
        tipLabel.setText("提示信息"+doc.getLength() + "/" + maxSize);
    }
}
