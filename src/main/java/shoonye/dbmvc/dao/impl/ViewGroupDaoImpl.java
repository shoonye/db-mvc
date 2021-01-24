package shoonye.dbmvc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import shoonye.dbmvc.dao.ViewGroupDao;
import shoonye.dbmvc.entity.ViewGroup;
import shoonye.util.hibernate.HibernateBaseDao;


@Component("viewGroupDao")
public class ViewGroupDaoImpl extends HibernateBaseDao<ViewGroup, Integer> implements ViewGroupDao {

	@Override
	public ViewGroup findByKey(String key) {
		Criteria cirteria = session().createCriteria(ViewGroup.class);
		cirteria.add(Restrictions.eq("key", key));
		return (ViewGroup)cirteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
    @Override
    public void deleteAll() {
        Criteria cirteria = session().createCriteria(ViewGroup.class);
        List<ViewGroup> all = cirteria.list();
        for(ViewGroup vg : all){
            vg.getViewTemplates();
            session().delete(vg);
        }
    }
}
