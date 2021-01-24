package shoonye.dbmvc.dao;

import shoonye.dbmvc.entity.ActionHandlerConfig;
import shoonye.util.hibernate.BaseDao;


public interface ActionHandlerConfigDao extends BaseDao<ActionHandlerConfig, Integer> {
	ActionHandlerConfig findByKey(String actionName);
	void deleteAll();
}
