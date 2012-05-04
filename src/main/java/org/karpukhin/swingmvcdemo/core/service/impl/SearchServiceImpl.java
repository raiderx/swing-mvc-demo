package org.karpukhin.swingmvcdemo.core.service.impl;

import org.apache.commons.lang.StringUtils;
import org.karpukhin.swingmvcdemo.core.dao.SearchDao;
import org.karpukhin.swingmvcdemo.core.model.User;
import org.karpukhin.swingmvcdemo.core.service.SearchService;

import java.util.Collections;
import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class SearchServiceImpl implements SearchService {

    private SearchDao searchDao;

    public SearchServiceImpl(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    @Override
    public List<User> searchUsers(String text) {
        if (!StringUtils.isEmpty(text)) {
            return searchDao.searchUsers(text);
        }
        return Collections.emptyList();
    }

    @Override
    public void rebuildIndex() {
        searchDao.rebuildIndex();
    }
}
