package shoonye.dbmvc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import shoonye.dbmvc.dao.ActionHandlerConfigDao;
import shoonye.dbmvc.entity.ActionHandlerConfig;
import shoonye.util.hibernate.HibernateBaseDao;


@Component("actionHandlerConfigDao")
public class ActionHandlerConfigDaoImpl extends HibernateBaseDao<ActionHandlerConfig, Integer> implements ActionHandlerConfigDao {

	@Override
	public ActionHandlerConfig findByKey(String key) {
		Criteria cirteria = session().createCriteria(ActionHandlerConfig.class);
		cirteria.add(Restrictions.eq("key", key));
		return (ActionHandlerConfig)cirteria.uniqueResult();
	}

    @SuppressWarnings("unchecked")
    @Override
    public void deleteAll() {
        Criteria cirteria = session().createCriteria(ActionHandlerConfig.class);
        List<ActionHandlerConfig> all = cirteria.list();
        for(ActionHandlerConfig ahc : all){
            session().delete(ahc);
        }
    }

}
