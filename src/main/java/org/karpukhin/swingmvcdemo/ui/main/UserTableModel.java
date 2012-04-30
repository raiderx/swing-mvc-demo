package org.karpukhin.swingmvcdemo.ui.main;

import org.karpukhin.swingmvcdemo.core.model.User;
import org.springframework.context.MessageSource;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * @author Pavel Karpukhin
 */
public class UserTableModel extends AbstractTableModel {

    private static final int COLUMNS_COUNT = 2;
    private static final String[] columnNames = {"label.last.name", "label.first.name"};

    private List<User> users = new LinkedList<User>();
    private MessageSource messageSource;

    public UserTableModel(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS_COUNT;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return getMessage(columnNames[column]);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getLastName();
            case 1:
                return user.getFirstName();
        }
        return null;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
        fireTableDataChanged();
    }

    public User getUserAt(int rowIndex) {
        return users.get(rowIndex);
    }

    private String getMessage(String code) {
        return messageSource.getMessage(code, null, code, Locale.getDefault());
    }
}
