package org.karpukhin.swingmvcdemo.ui.group;

/**
 * @author Pavel Karpukhin
 */
public interface GroupController {

    void create(String name, String description);
    
    void save(int id, String name, String description);
    
    void cancel();
}
