package org.karpukhin.swingmvcdemo.ui.user;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.ui.main.MainView;
import org.springframework.context.MessageSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.Map;

/**
 * @author Pavel Karpukhin
 */
public class UserView implements UserModelObserver {

    private UserController controller;
    private UserModel model;
    private MessageSource messageSource;

    private JFrame owner;
    private JDialog dialog;
    private JLabel firstNameLabel = new JLabel();
    private JTextField firstNameField = new JTextField();
    private JLabel lastNameLabel = new JLabel();
    private JTextField lastNameField = new JTextField();
    private JLabel groupLabel = new JLabel();
    private JComboBox groupCombo = new JComboBox();
    private JButton saveButton = new JButton();
    private JButton cancelButton = new JButton();

    private KeyValueComboBoxModel<String, String> comboModel = new KeyValueComboBoxModel();

    public UserView(UserController controller, UserModel model, MainView owner, MessageSource messageSource) {
        this.controller = controller;
        this.model = model;
        this.owner = owner.getFrame();
        this.messageSource = messageSource;
    }

    public void init() {
        dialog = new JDialog(owner, model.getTitle(), true);
        initComponents();
        createLayout();
        dialog.setSize(300, 200);
        setLocation(owner);
        model.registerObserver(this);
    }

    private void initComponents() {
        firstNameLabel.setText(getMessage("label.first.name"));
        lastNameLabel.setText(getMessage("label.last.name"));
        groupLabel.setText(getMessage("label.group"));

        groupCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Map.Entry) {
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) value;
                    value = entry.getValue();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        groupCombo.setModel(comboModel);
        saveButton.setText(getMessage("label.save"));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    String key = comboModel.getSelectedKey();
                    if (model.getUser().getId() == null) {
                        controller.create(firstNameField.getText(), lastNameField.getText(), Integer.parseInt(key));
                    } else {
                        controller.save(model.getUser().getId(), firstNameField.getText(), lastNameField.getText(), Integer.parseInt(key));
                    }
                }
            }
        });
        cancelButton.setText(getMessage("label.cancel"));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.cancel();
            }
        });
    }

    private void createLayout() {
        GroupLayout layout = new GroupLayout(dialog.getContentPane());

        GroupLayout.Group horGroup = layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(firstNameLabel)
                        .addComponent(lastNameLabel)
                        .addComponent(groupLabel)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup()
                        .addComponent(firstNameField)
                        .addComponent(lastNameField)
                        .addComponent(groupCombo)
                )
            )
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Integer.MAX_VALUE)
                .addComponent(saveButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                )
            ;
        GroupLayout.Group verGroup = layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                    .addComponent(firstNameLabel)
                    .addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup()
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup()
                .addComponent(groupLabel)
                .addComponent(groupCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(0, 0, Integer.MAX_VALUE)
            .addGroup(layout.createParallelGroup()
                .addComponent(saveButton)
                .addComponent(cancelButton)
            );

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(horGroup)
                        .addContainerGap()
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(verGroup)
                .addContainerGap()
            );

        dialog.getContentPane().setLayout(layout);
    }

    private void setLocation(JFrame owner) {
        if (dialog.getSize().getWidth() < owner.getSize().getWidth() &&
            dialog.getSize().getHeight() < owner.getSize().getHeight()) {
            dialog.setLocation(
                owner.getX() + (int)(owner.getSize().getWidth() - dialog.getSize().getWidth()) / 2,
                owner.getY() + (int)(owner.getSize().getHeight() - dialog.getSize().getHeight()) / 2);
        } else {
            dialog.setLocation(
                owner.getX() + (int)owner.getSize().getWidth() / 2,
                owner.getY() + (int)owner.getSize().getHeight() / 2);
        }
    }

    private boolean validate() {
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || groupCombo.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }

    private String getMessage(String code) {
        return messageSource.getMessage(code, null, code, Locale.getDefault());
    }

    @Override
    public void updateTitle() {
        dialog.setTitle(model.getTitle());
    }

    @Override
    public void updateGroups() {
        comboModel.clear();
        comboModel.put("", "");
        for (Group group : model.getGroups()) {
            comboModel.put(Integer.toString(group.getId()), group.getName());
        }
    }

    @Override
    public void updateUser() {
        firstNameField.setText(model.getUser().getFirstName());
        lastNameField.setText(model.getUser().getLastName());
        comboModel.setSelectedItemWithKey(Integer.toString(model.getUser().getGroup().getId()));
    }

    @Override
    public void updateVisible() {
        dialog.setVisible(model.getVisible());
        firstNameField.requestFocusInWindow();
    }
}
