package shoonye.dbmvc.dao;

import shoonye.dbmvc.entity.ViewTemplate;
import shoonye.util.hibernate.BaseDao;


public interface ViewTemplateDao extends BaseDao<ViewTemplate, Integer> {
	ViewTemplate findByKey(String key);	
	void deleteAll();
}
