package org.karpukhin.swingmvcdemo.ui.group;

import org.karpukhin.swingmvcdemo.ui.main.MainView;
import org.springframework.context.MessageSource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * @author Pavel Karpukhin
 */
public class GroupView implements GroupModelObserver {

    private GroupController controller;
    private GroupModel model;
    private MessageSource messageSource;

    private JFrame owner;
    private JDialog dialog;
    private JLabel nameLabel = new JLabel();
    private JTextField nameField = new JTextField();
    private JLabel descriptionLabel = new JLabel();
    private JTextField descriptionField = new JTextField();
    private JButton saveButton = new JButton();
    private JButton cancelButton = new JButton();

    public GroupView(GroupController controller, GroupModel model, MainView owner, MessageSource messageSource) {
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
        nameLabel.setText(getMessage("label.name"));
        descriptionLabel.setText(getMessage("label.description"));
        saveButton.setText(getMessage("label.save"));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    if (model.getGroup().getId() == null) {
                        controller.create(nameField.getText(), descriptionField.getText());
                    } else {
                        controller.save(model.getGroup().getId(), nameField.getText(), descriptionField.getText());
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
                        .addComponent(nameLabel)
                        .addComponent(descriptionLabel)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup()
                        .addComponent(nameField)
                        .addComponent(descriptionField)
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
                    .addComponent(nameLabel)
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup()
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
        if (nameField.getText().isEmpty() || descriptionField.getText().isEmpty()) {
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
    public void updateGroup() {
        nameField.setText(model.getGroup().getName());
        descriptionField.setText(model.getGroup().getDescription());
    }

    @Override
    public void updateVisible() {
        dialog.setVisible(model.getVisible());
        nameField.requestFocusInWindow();
    }
}
