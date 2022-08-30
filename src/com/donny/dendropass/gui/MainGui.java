package com.donny.dendropass.gui;

import com.donny.dendropass.util.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class MainGui extends JFrame {

    public MainGui() {
        super("Dendrogram Password");

        //Draw Gui
        {
            JLabel a = new JLabel("Length (Bytes)");
            JTextField size = new JTextField();
            AbstractDocument doc = (AbstractDocument) size.getDocument();
            doc.setDocumentFilter(new SizeNumberFilter());
            size.setText("16");
            JRadioButton select = new JRadioButton("Select Characters");
            JRadioButton all = new JRadioButton("All Characters");
            ButtonGroup group = new ButtonGroup();
            group.add(select);
            group.add(all);
            select.setSelected(true);
            JCheckBox lower = new JCheckBox("Lowercase Letters");
            lower.setSelected(true);
            JCheckBox upper = new JCheckBox("Uppercase Letters");
            upper.setSelected(true);
            JCheckBox numb = new JCheckBox("Numbers");
            numb.setSelected(true);
            JCheckBox spec = new JCheckBox("Special Characters");

            JScrollPane allowedPane = DendroFactory.getLongField();
            JTextArea allowed = (JTextArea) allowedPane.getViewport().getView();
            allowed.setText("@#$%^&*_-=+");
            spec.addChangeListener(event -> {
                if (spec.isSelected()) {
                    allowed.setEditable(true);
                    allowed.setBackground(DendroFactory.CONTENT);
                } else {
                    allowed.setEditable(false);
                    allowed.setBackground(DendroFactory.DISABLED);
                }
            });
            select.addChangeListener(event -> {
                if (select.isSelected()) {
                    lower.setEnabled(true);
                    upper.setEnabled(true);
                    numb.setEnabled(true);
                    spec.setEnabled(true);
                    if (spec.isSelected()) {
                        allowed.setEditable(true);
                        allowed.setBackground(DendroFactory.CONTENT);
                    } else {
                        allowed.setEditable(false);
                        allowed.setBackground(DendroFactory.DISABLED);
                    }
                } else {
                    lower.setEnabled(false);
                    upper.setEnabled(false);
                    numb.setEnabled(false);
                    spec.setEnabled(false);
                    allowed.setEditable(false);
                    allowed.setBackground(DendroFactory.DISABLED);
                }
            });
            JScrollPane resultPane = DendroFactory.getScrollField();
            JTextArea result = (JTextArea) resultPane.getViewport().getView();
            result.setEditable(false);
            result.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    result.selectAll();
                }
            });
            JButton generate = new JButton("Generate");
            generate.addActionListener(event -> {
                SecureRandom rand = new SecureRandom();
                int bytes = Integer.parseInt(size.getText());
                CharacterSet set;
                if (select.isSelected()) {
                    ArrayList<CharacterSet.Bracket> brackets = new ArrayList<>();
                    if (lower.isSelected()) {
                        brackets.add(CharacterSet.LOWERCASE_LETTERS);
                    }
                    if (upper.isSelected()) {
                        brackets.add(CharacterSet.CAPITOL_LETTERS);
                    }
                    if (numb.isSelected()) {
                        brackets.add(CharacterSet.NUMBERS);
                    }
                    if (spec.isSelected()) {
                        set = new CharacterSet(brackets, allowed.getText());
                    } else {
                        set = new CharacterSet(brackets);
                    }
                } else {
                    set = new CharacterSet(new CharacterSet.Bracket(0, 35536));
                }
                result.setText(set.getPassword(rand, bytes));
            });

            //Group Layout
            {
                GroupLayout main = new GroupLayout(getContentPane());
                getContentPane().setLayout(main);
                main.setHorizontalGroup(
                        main.createSequentialGroup().addContainerGap().addGroup(
                                main.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                                        main.createSequentialGroup().addComponent(
                                                a, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                        ).addGap(DendroFactory.SMALL_GAP).addComponent(
                                                size, 30, 30, 30
                                        )
                                ).addGroup(
                                        main.createSequentialGroup().addComponent(
                                                select, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                        ).addGap(
                                                DendroFactory.SMALL_GAP, DendroFactory.SMALL_GAP, Short.MAX_VALUE
                                        ).addComponent(
                                                all, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                        )
                                ).addComponent(
                                        lower, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                ).addComponent(
                                        upper, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                ).addComponent(
                                        numb, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                ).addGroup(
                                        main.createSequentialGroup().addComponent(
                                                spec, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                        ).addGap(DendroFactory.SMALL_GAP).addComponent(
                                                allowedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE
                                        )
                                ).addComponent(
                                        generate, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                ).addComponent(
                                        resultPane, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE
                                )
                        ).addContainerGap()
                );
                main.setVerticalGroup(
                        main.createSequentialGroup().addContainerGap().addGroup(
                                main.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(
                                        a, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                ).addComponent(
                                        size, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                )
                        ).addGap(DendroFactory.SMALL_GAP).addGroup(
                                main.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(
                                        select, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                ).addComponent(
                                        all, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                )
                        ).addGap(DendroFactory.SMALL_GAP).addComponent(
                                lower, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                        ).addGap(DendroFactory.SMALL_GAP).addComponent(
                                upper, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                        ).addGap(DendroFactory.SMALL_GAP).addComponent(
                                numb, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                        ).addGap(DendroFactory.SMALL_GAP).addGroup(
                                main.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                                        spec, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                ).addComponent(
                                        allowedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                                )
                        ).addGap(DendroFactory.MEDIUM_GAP).addComponent(
                                generate, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE
                        ).addGap(DendroFactory.MEDIUM_GAP).addComponent(
                                resultPane, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE
                        ).addContainerGap()
                );
            }
            pack();
        }
    }
}
