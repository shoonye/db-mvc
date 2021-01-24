package shoonye.dbmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shoonye.dbmvc.bean.ActionHandler;
import shoonye.util.hibernate.AuditedEntity;
import shoonye.util.spring.SpringContext;


@Audited
@Entity
@Table(name="action_handler_config")
public class ActionHandlerConfig extends AuditedEntity<Integer>{
	private static final long serialVersionUID = 1L;
	static final Logger logger = LoggerFactory.getLogger(ActionHandlerConfig.class);

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id private Integer id;
	
	@Column(name="action_key", length=50) private String key;
	@Column(name="name", length=200) private String name;
	
	@Column(name="handler_full_class_name",length=200) private String handlerFQCN;
	@Column(name="handler_spring_bean_name",length=50) private String handlerSBN;
	@Column(name="input_full_class_name",length=200) private String inputFQCN;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHandlerFQCN() {
		return handlerFQCN;
	}
	public void setHandlerFQCN(String handlerFQCN) {
		this.handlerFQCN = handlerFQCN;
	}
	public String getHandlerSBN() {
		return handlerSBN;
	}
	public void setHandlerSBN(String handlerSBN) {
		this.handlerSBN = handlerSBN;
	}
	public String getInputFQCN() {
		return inputFQCN;
	}
	public void setInputFQCN(String inputFQCN) {
		this.inputFQCN = inputFQCN;
	}
	
	public Object createInputBean() throws Exception{
		Class<?> inputClass = Class.forName(getInputFQCN());
		Object inputBean = inputClass.newInstance();
		return inputBean;
	}
	
	@SuppressWarnings("rawtypes")
	public ActionHandler createHandler()  throws Exception{
		Class<?> handlerClass = Class.forName(getHandlerFQCN());
		Object handlerBean = null;
		
		try{
			handlerBean  = SpringContext.getBean(getHandlerSBN(), handlerClass);
		}catch(Exception e){
			logger.warn("Could not find bean by name: "+getHandlerFQCN() +" Trying to instantiate by class name");
		}
		
		if(handlerBean==null)handlerBean = handlerClass.newInstance();
		return (ActionHandler)handlerBean;
	}
}
