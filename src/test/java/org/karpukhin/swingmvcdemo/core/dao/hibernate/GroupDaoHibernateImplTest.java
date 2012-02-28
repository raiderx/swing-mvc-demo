package org.karpukhin.swingmvcdemo.core.dao.hibernate;

import org.junit.Before;
import org.junit.Test;
import org.karpukhin.swingmvcdemo.core.dao.GroupDao;
import org.karpukhin.swingmvcdemo.core.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * @author Pavel Karpukhin
 */
@ContextConfiguration({"classpath:/org/karpukhin/swingmvcdemo/core/dao/applicationContext-dao.xml"})
@Transactional
public class GroupDaoHibernateImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private GroupDao groupDao;
    
    private Group group;

    @Before
    public void setUp() {
        group = new Group("some group", "some group description");
        groupDao.saveOrUpdate(group);
    }

    @Test
    public void testCreateGroup() {
        Group group = new Group("name", "description");
        groupDao.saveOrUpdate(group);
        assertNotSame(0, group.getId());
    }

    @Test
    public void testDeleteGroup() {
        groupDao.delete(group);
    }

    @Test
    public void testGetAllGroups() {
        List<Group> groups = groupDao.getGroups();
        assertEquals(1, groups.size());
    }
}
