package shoonye.dbmvc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import shoonye.dbmvc.dao.ViewTemplateDao;
import shoonye.dbmvc.entity.ViewTemplate;
import shoonye.util.hibernate.HibernateBaseDao;


@Component("viewTemplateDao")
public class ViewTemplateDaoImpl extends HibernateBaseDao<ViewTemplate, Integer> implements ViewTemplateDao {

	@Override
	public ViewTemplate findByKey(String key) {
		Criteria cirteria = session().createCriteria(ViewTemplate.class);
		cirteria.add(Restrictions.eq("key", key));
		return (ViewTemplate)cirteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
    @Override
    public void deleteAll() {
        Criteria cirteria = session().createCriteria(ViewTemplate.class);
        List<ViewTemplate> all = cirteria.list();
        for(ViewTemplate vt : all){
            session().delete(vt);
        }
    }
}
