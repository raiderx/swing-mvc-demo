package org.karpukhin.swingmvcdemo.ui.user;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.ui.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * @author Pavel Karpukhin
 */
public class UserView implements UserModelObserver {

    private UserController controller;
    private UserModel model;

    private JDialog dialog;
    private JLabel firstNameLabel = new JLabel("Имя");
    private JTextField firstNameField = new JTextField();
    private JLabel lastNameLabel = new JLabel("Фамилия");
    private JTextField lastNameField = new JTextField();
    private JLabel groupLabel = new JLabel("Группа");
    private JComboBox groupCombo = new JComboBox();
    private JButton saveButton = new JButton("Сохранить");
    private JButton cancelButton = new JButton("Отмена");

    private KeyValueComboBoxModel comboModel = new KeyValueComboBoxModel();

    public UserView(UserControllerImpl controller, UserModel model, MainView owner) {
        this.controller = controller;
        this.model = model;
        dialog = new JDialog(owner.getFrame(), model.getTitle(), true);
        initComponents();
        createLayout();
        dialog.setSize(300, 200);
        setLocation(owner.getFrame());
        model.registerObserver(this);
    }

    private void initComponents() {
        groupCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Map.Entry) {
                    Map.Entry<String,String> entry = (Map.Entry<String, String>) value;
                    value = entry.getValue();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        groupCombo.setModel(comboModel);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    String key = ((Map.Entry<String, String>)comboModel.getSelectedItem()).getKey();
                    controller.save(firstNameField.getText(), lastNameField.getText(), Integer.parseInt(key));
                }
            }
        });
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
                )
            ;

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

    public void show() {
        dialog.setVisible(true);
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

    public void hide() {
        dialog.setVisible(false);
    }
}
