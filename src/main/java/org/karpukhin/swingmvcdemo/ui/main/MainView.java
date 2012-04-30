package org.karpukhin.swingmvcdemo.ui.main;

import org.karpukhin.swingmvcdemo.core.model.Group;
import org.springframework.context.MessageSource;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

/**
 * @author Pavel Karpukhin
 */
public class MainView implements MainModelObserver {

    private MainController controller;
    private MainModel model;
    private MessageSource messageSource;

    private DefaultTreeModel treeModel;
    private UserTableModel tableModel = new UserTableModel();

    private JFrame frame = new JFrame();
    private JSplitPane splitPane = new JSplitPane();
    private JTree tree = new JTree();
    private JTable table = new JTable();
    private JPopupMenu rootTreePopupMenu;
    private JPopupMenu leafTreePopupMenu;
    private JPopupMenu tablePopupMenu;

    public MainView(MainController controller, MainModel model, MessageSource messageSource) {
        this.controller = controller;
        this.model = model;
        this.messageSource = messageSource;
    }

    public void init() {
        initComponents();
        createLayout();
        setLocation();
        model.registerObserver(this);
        frame.setVisible(true);
    }

    private void initComponents() {
        frame.setTitle(getMessage("mainView.title"));
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
        treeModel = new DefaultTreeModel(new DefaultMutableTreeNode(getMessage("label.groups")));
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
        JMenuItem menuItem = new JMenuItem(getMessage("label.add.user"));
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
        menuItem = new JMenuItem(getMessage("label.rename.group"));
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
        menuItem = new JMenuItem(getMessage("label.remove.group"));
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
        menuItem = new JMenuItem(getMessage("label.add.group"));
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
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(tableModel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    controller.editUser(tableModel.getUserAt(table.getSelectedRow()).getId());
                }
                if (e.isPopupTrigger() && table.getSelectedRow() != -1) {
                    tablePopupMenu.show(table, e.getX(), e.getY());
                }
            }
        });
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

        tablePopupMenu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem(getMessage("label.add.user"));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addUserToGroup(model.getSelectedGroup());
            }
        });
        tablePopupMenu.add(menuItem);
        menuItem = new JMenuItem(getMessage("label.edit.user"));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.editUser(tableModel.getUserAt(table.getSelectedRow()).getId());
            }
        });
        tablePopupMenu.add(menuItem);
        menuItem = new JMenuItem(getMessage("label.remove.user"));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.removeUser(tableModel.getUserAt(table.getSelectedRow()).getId());
            }
        });
        tablePopupMenu.add(menuItem);
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
                    (int) (d.getWidth() - frame.getSize().getWidth()) / 2,
                    (int) (d.getHeight() - frame.getSize().getHeight()) / 2);
    }

    private String getMessage(String code) {
        return messageSource.getMessage(code, null, code, Locale.getDefault());
    }

    @Override
    public void updateGroups() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(getMessage("label.groups"));
        for (Group group : model.getGroups()) {
            root.add(new DefaultMutableTreeNode(new TreeNodeObject(group.getId(), group.getName())));
        }
        treeModel.setRoot(root);
    }

    @Override
    public void updateUsers() {
        tableModel.setUsers(model.getUsers());
    }

    @Override
    public void updateSelectedGroup() {
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
