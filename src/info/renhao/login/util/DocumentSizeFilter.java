package info.renhao.login.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class DocumentSizeFilter extends DocumentFilter {
    private int maxSize;

    public DocumentSizeFilter(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb,
                             int offset, String string, AttributeSet attr) throws BadLocationException{
        if ((fb.getDocument().getLength() + string.length()) <= maxSize) {
            super.insertString(fb, offset, string, attr);
        }
        else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length,
                        String text, AttributeSet attrs) throws BadLocationException {
        if ((fb.getDocument().getLength() + text.length() - length) <= maxSize) {
            super.replace(fb, offset, length, text, attrs);
        }
        else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
