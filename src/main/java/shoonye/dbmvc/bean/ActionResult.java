package shoonye.dbmvc.bean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ActionResult {
	private ActionResultType type;
	private String key;
	private List<String> fallbackKeys;
	private Map<String, Object> data;
	
	public ActionResult(){}
	
	public ActionResult(ActionResultType type, String key){
		this(type,key,null);
	}
	
	public ActionResult(ActionResultType type, String key, Map<String, Object> data) {
		super();
		this.type = type;
		this.key = key;
		this.data = data;
	}
	
	public ActionResultType getType() {
		return type;
	}
	public void setType(ActionResultType type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public void addData(String key, Object value){
		if(data==null)  data = new HashMap<String, Object>();
		data.put(key, value);
	}
	
	public void addFallbackKey(String fallbackKey){
		if(fallbackKeys==null) fallbackKeys = new LinkedList<String>();
		fallbackKeys.add(fallbackKey);
	}
	public boolean hasNext(){
		return !(fallbackKeys==null || fallbackKeys.isEmpty() ) ;

	}
	public String nextKey(){
		return fallbackKeys.remove(0);
	}
}
