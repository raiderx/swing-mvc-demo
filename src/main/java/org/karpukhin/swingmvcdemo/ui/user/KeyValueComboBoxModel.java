package org.karpukhin.swingmvcdemo.ui.user;

import javax.swing.*;
import java.util.*;

/**
 * @author Pavel Karpukhin
 */
public class KeyValueComboBoxModel extends AbstractListModel
	implements ComboBoxModel {

	private Map<String, String> values = new TreeMap<String, String>();
	private Map.Entry<String, String> selectedItem = null;

    /**
     * {@inheritDoc}
     */
    @Override
	public int getSize() {
		return values.size();
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public Object getElementAt(int index) {
		List<Map.Entry<String, String>> list =
			new LinkedList<Map.Entry<String, String>>(values.entrySet());
		return list.get(index);

	}

    /**
     * {@inheritDoc}
     */
    @Override
	public void setSelectedItem(Object anItem) {
		selectedItem = (Map.Entry<String, String>) anItem;
		fireContentsChanged(this, -1, -1);
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public Object getSelectedItem() {
		return selectedItem;
	}
    
	public void put(String key, String value) {
		values.put(key, value);
	}

	public void clear() {
		values.clear();
	}

    public void setSelectedItemWithKey(String key) {
        if (values.containsKey(key)) {
            setSelectedItem(new AbstractMap.SimpleEntry<String, String>(key, values.get(key)));
        } else {
            throw new IllegalArgumentException("ComboBoxModel doesn't contain element with key " + key);
        }
    }
}
