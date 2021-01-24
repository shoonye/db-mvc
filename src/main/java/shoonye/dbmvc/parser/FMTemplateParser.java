package shoonye.dbmvc.parser;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shoonye.dbmvc.sample.SampleActionProcessor;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FMTemplateParser implements TemplateParser {
	static final Logger logger = LoggerFactory.getLogger(SampleActionProcessor.class);
	
	@Override
	public String parse(Parsable template, Map<String, Object> data) {
		StringWriter writer = new StringWriter();
		try {
			Configuration cfg = new Configuration();
			StringTemplateLoader stringLoader = new StringTemplateLoader();
			String name = template.getName()==null? template.getName(): template.getKey();
			stringLoader.putTemplate(name, template.getBody());
			cfg.setTemplateLoader(stringLoader);
			
			Template tmplt = new Template(name, new StringReader(template.getBody()),cfg);
			tmplt.process(data, writer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		return writer.toString();
	}

}
