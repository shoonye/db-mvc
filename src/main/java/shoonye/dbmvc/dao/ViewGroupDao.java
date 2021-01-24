package shoonye.dbmvc.dao;

import shoonye.dbmvc.entity.ViewGroup;
import shoonye.util.hibernate.BaseDao;


public interface ViewGroupDao extends BaseDao<ViewGroup, Integer> {
	ViewGroup findByKey(String key);
	void deleteAll();
}
