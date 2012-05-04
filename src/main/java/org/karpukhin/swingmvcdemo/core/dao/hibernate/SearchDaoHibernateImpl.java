package org.karpukhin.swingmvcdemo.core.dao.hibernate;

import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.karpukhin.swingmvcdemo.core.dao.SearchDao;
import org.karpukhin.swingmvcdemo.core.model.User;

import java.util.List;

/**
 * @author Pavel Karpukhin
 */
public class SearchDaoHibernateImpl implements SearchDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected FullTextSession getFullTextSession() {
        Session session = sessionFactory.getCurrentSession();
        return Search.getFullTextSession(session);
    }

    @Override
    public List<User> searchUsers(String text) {
        QueryBuilder qb = getFullTextSession().getSearchFactory().buildQueryBuilder()
                .forEntity(User.class).get();
        Query query = qb.keyword().wildcard().onField("firstName").andField("lastName")
                .matching(text + "*").createQuery();
        return getFullTextSession().createFullTextQuery(query).list();
    }

    @Override
    public void rebuildIndex() {
        getFullTextSession().createIndexer(User.class).start();
    }
}
