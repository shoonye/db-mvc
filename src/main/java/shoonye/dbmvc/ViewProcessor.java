package shoonye.dbmvc;

import java.util.Map;

import shoonye.dbmvc.entity.ViewGroup;


public interface ViewProcessor <T> {
	public T process(ViewGroup viewLayout, Map<String, Object> map);
}
