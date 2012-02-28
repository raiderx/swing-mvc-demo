package org.karpukhin.swingmvcdemo.ui.group;

/**
 * @author Pavel Karpukhin
 */
public interface GroupController {
    
    void save(String name, String description);
    
    void cancel();
}
