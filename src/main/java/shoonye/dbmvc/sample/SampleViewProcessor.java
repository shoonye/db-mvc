package shoonye.dbmvc.sample;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import shoonye.dbmvc.ViewProcessor;
import shoonye.dbmvc.entity.ViewGroup;
import shoonye.dbmvc.entity.ViewTemplate;
import shoonye.dbmvc.parser.TemplateParser;
import shoonye.dbmvc.parser.TemplateParserRegistry;
import shoonye.util.StringUtil;


@Component("viewProcessor")
public class SampleViewProcessor implements ViewProcessor<String> {
	
	@Override
	public String process(ViewGroup layout, Map<String, Object> data) {
		if(layout==null) return null;
		
		if(StringUtil.isBlank(layout.getBody())) {
			ViewTemplate template = layout.defaultTemplate();
			TemplateParser parser = TemplateParserRegistry.getParser(template.getLanguage());
			String view = parser.parse(template, data);
			return view;
		}else{
			Map<String, String> viewOutputs = new LinkedHashMap<String, String>(layout.getViewTemplates().size());
			for(ViewTemplate template: layout.getViewTemplates()){
				String view = null;
				try{
					TemplateParser parser = TemplateParserRegistry.getParser(template.getLanguage());
					view = parser.parse(template, data);
					viewOutputs.put(template.getKey(), view);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			TemplateParser parser = TemplateParserRegistry.getParser(layout.getLanguage());
			String view = parser.parse(layout, data);
			return view;
		}		
	}

}
