package org.karpukhin.swingmvcdemo.ui.main;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.karpukhin.swingmvcdemo.core.model.User;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Pavel Karpukhin
 */
public class MainView implements MainObserver {

    private MainController controller;
    private MainModel model;
    private DefaultTreeModel treeModel;
    private DefaultTableModel tableModel;

    private JFrame frame = new JFrame("MVC Demo");
    private JSplitPane splitPane = new JSplitPane();
    private JTree tree = new JTree();
    private JTable table = new JTable();
    private JPopupMenu rootTreePopupMenu;
    private JPopupMenu leafTreePopupMenu;

    public MainView(MainController controller, MainModel model) {
        this.controller = controller;
        this.model = model;
        initComponents();
        createLayout();
        setLocation();
        this.model.registerObserver(this);
        frame.setVisible(true);
    }

    private void initComponents() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);

        splitPane.setLeftComponent(createLeftPanel());
        splitPane.setRightComponent(createRightPanel());
    }

    /**
     * Initializes tree, popup menu
     * @return tree
     */
    private JComponent createLeftPanel() {
        treeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Группы"));
        updateGroups();
        tree.setModel(treeModel);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                if (node != null && node.getUserObject() instanceof TreeNodeObject) {
                    TreeNodeObject nodeObject = (TreeNodeObject) node.getUserObject();
                    controller.selectGroup(nodeObject.getId());
                }
            }
        });
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    if (path == null) {
                        return;
                    }
                    tree.setSelectionPath(path);
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    if (node != null) {
                        if (node.getUserObject() instanceof TreeNodeObject) {
                            leafTreePopupMenu.show(tree, e.getX(), e.getY());
                        } else if (node.getUserObject() instanceof String) {
                            rootTreePopupMenu.show(tree, e.getX(), e.getY());
                        }
                    }
                }
            }
        });

        leafTreePopupMenu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Добавить пользователя");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
                if (node != null && node.getUserObject() instanceof TreeNodeObject) {
                    controller.addUserToGroup(((TreeNodeObject) node.getUserObject()).getId());
                }
            }
        });
        leafTreePopupMenu.add(menuItem);
        menuItem = new JMenuItem("Переименовать группу");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
                if (node != null && node.getUserObject() instanceof TreeNodeObject) {
                    controller.editGroup(((TreeNodeObject) node.getUserObject()).getId());
                }
            }
        });
        leafTreePopupMenu.add(menuItem);
        menuItem = new JMenuItem("Удалить группу");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
                if (node != null && node.getUserObject() instanceof TreeNodeObject) {
                    controller.removeGroup(((TreeNodeObject) node.getUserObject()).getId());
                }
            }
        });
        leafTreePopupMenu.add(menuItem);

        rootTreePopupMenu = new JPopupMenu();
        menuItem = new JMenuItem("Добавить группу");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
                if (node != null && node.getUserObject() instanceof String) {
                    controller.addGroup();
                }
            }
        });
        rootTreePopupMenu.add(menuItem);

        return tree;
    }

    /**
     * Creates right panel with table inside and its layout
     * @return panel just created
     */
    private JComponent createRightPanel() {
        updateUsers();
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel();

        GroupLayout layout = new GroupLayout(panel);

        GroupLayout.Group horGroup = layout.createParallelGroup()
            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, Integer.MAX_VALUE);
        GroupLayout.Group verGroup = layout.createSequentialGroup()
            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 150, Integer.MAX_VALUE);

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

        panel.setLayout(layout);
        return panel;
    }

    /**
     * Creates layout of main frame
     */
    private void createLayout() {
        GroupLayout layout = new GroupLayout(frame.getContentPane());

        GroupLayout.Group horGroup = layout.createParallelGroup()
            .addComponent(splitPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE);
        GroupLayout.Group verGroup = layout.createSequentialGroup()
            .addComponent(splitPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE);

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

        frame.getContentPane().setLayout(layout);
    }

    private void setLocation() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
            frame.setLocation(
                (int)(d.getWidth() - frame.getSize().getWidth()) / 2,
                (int)(d.getHeight() - frame.getSize().getHeight()) / 2);
    }

    @Override
    public void updateGroups() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Группы");
        for (Group group : model.getGroups()) {
            root.add(new DefaultMutableTreeNode(new TreeNodeObject(group.getId(), group.getName())));
        }
        treeModel.setRoot(root);
    }

    @Override
    public void updateUsers() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] {"Фамилия", "Имя"});
        int row = -1;
        for (User user : model.getUsers()) {
            tableModel.insertRow(++row, new String[] { user.getLastName(), user.getFirstName()});
        }
        table.setModel(tableModel);
    }

    public JFrame getFrame() {
        return frame;
    }

    private static class TreeNodeObject {

        private int id;
        private String value;

        public TreeNodeObject(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
