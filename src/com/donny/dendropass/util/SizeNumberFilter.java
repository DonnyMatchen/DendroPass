package com.donny.dendropass.util;

import javax.swing.text.*;
import java.awt.Toolkit;

public class SizeNumberFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
        Document doc = fb.getDocument();
        try {
            if ((doc.getLength() + str.length()) <= 3) {
                String cur = doc.getText(0, doc.getLength());
                int check = Integer.parseInt(cur.substring(0, offs) + str + cur.substring(offs));
                if (check <= 0) {
                    doc.remove(0, doc.getLength());
                    super.insertString(fb, 0, "16", a);
                } else {
                    if (check <= 256) {
                        super.insertString(fb, offs, str, a);
                    } else {
                        doc.remove(0, doc.getLength());
                        super.insertString(fb, 0, "256", a);
                    }
                }
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        } catch (NumberFormatException e) {
            doc.remove(0, doc.getLength());
            super.insertString(fb, 0, "16", a);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
        Document doc = fb.getDocument();
        try {
            if ((doc.getLength() - length + str.length()) <= 3) {
                String cur = doc.getText(0, doc.getLength());
                int check = Integer.parseInt(cur.substring(0, offs) + str + cur.substring(offs + length));
                if (check <= 0) {
                    doc.remove(0, doc.getLength());
                    super.insertString(fb, 0, "16", a);
                } else {
                    if (check <= 256) {
                        super.replace(fb, offs, length, str, a);
                    } else {
                        doc.remove(0, doc.getLength());
                        super.insertString(fb, 0, "256", a);
                    }
                }
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        } catch (NumberFormatException e) {
            doc.remove(0, doc.getLength());
            super.insertString(fb, 0, "16", a);
        }
    }
}