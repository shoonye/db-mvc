package shoonye.dbmvc.sample;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shoonye.dbmvc.ActionProcessor;
import shoonye.dbmvc.MVCSettings;
import shoonye.dbmvc.ViewProcessor;
import shoonye.dbmvc.bean.ActionHandler;
import shoonye.dbmvc.bean.ActionResult;
import shoonye.dbmvc.bean.ActionResultType;
import shoonye.dbmvc.dao.ActionHandlerConfigDao;
import shoonye.dbmvc.dao.ViewGroupDao;
import shoonye.dbmvc.entity.ActionHandlerConfig;
import shoonye.dbmvc.entity.ViewGroup;


@Component("actionProcessor")
@Transactional(readOnly=false)
public class SampleActionProcessor implements ActionProcessor<String, Object> {
	static final Logger logger = LoggerFactory.getLogger(SampleActionProcessor.class);
	
	@Autowired ActionHandlerConfigDao actionHandlerConfigDao;
	@Autowired ViewGroupDao viewGroupDao;
	@Autowired MVCSettings mvcSettings;
	@Autowired ViewProcessor<String> viewProcessor;
	
	@Override
	public String process(String actionName, Object input) {
		boolean failure = false;
		ViewGroup group = null;
		ActionResult result=null;
		
		try {
			result = executeAction(actionName, input);
			group = viewGroupDao.findByKey(result.getKey());
			while(result.hasNext() && group==null){
				group = viewGroupDao.findByKey(result.nextKey());
			}
		} catch (Exception e) {
			failure = true;
			logger.error(e.getMessage(), e);
		}
		Map<String,Object> data = getGlobalData();
		if (result!=null && result.getData()!=null) data.putAll(result.getData());

		if(failure){
			group = viewGroupDao.findByKey(mvcSettings.globalErrorKey);
			data.put("msg","Server encountered fatal error, please contact your administrator.");
		}
	
		String output = viewProcessor.process(group, data);
		return compress(output);
	}

	
	private Map<String, Object> getGlobalData() {
		return new HashMap<String, Object>();
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ActionResult executeAction(String actionName, Object data) throws Exception{
		
		ActionHandlerConfig hc = actionHandlerConfigDao.findByKey(actionName);
		if(hc==null){
			throw new Exception(" Could not find action for " + actionName);
		}
		Object inputBean;
		try {
			inputBean = hc.createInputBean();
		} catch (Exception e) {
			logger.error("Failed creation of Action Handler Input: {} Bean Name:{} ", hc.getKey(), hc.getInputFQCN());
			throw e;
		}
		
		
		logger.debug("Input params {} ",inputBean.toString());
		
		ActionHandler handler;
		try {
			handler = hc.createHandler();
		} catch (Exception e) {
			logger.error("Failed creation of Action Handler : {} Bean Details : {}, {} ", hc.getKey(),hc.getHandlerFQCN(), hc.getHandlerSBN());
			throw e;
		}
		ActionResult result = (ActionResult)(handler.execute(inputBean));
		
		if(ActionResultType.ACTION.equals(result.getType())){
			return executeAction(result.getKey(), data);
		}else{
			result = processResult(result, actionName);
			return result;
		}
	}

	
	private String compress(String text) {
		return text;
	}
	
	//TODO we can put a full parser and processor here
	private ActionResult processResult(ActionResult result, String actionName) {
		if("${same}".equalsIgnoreCase(result.getKey())){
			result.setKey(actionName);
		}
		return result;
	}

}
