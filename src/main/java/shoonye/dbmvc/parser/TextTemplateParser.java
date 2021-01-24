package shoonye.dbmvc.parser;

import java.util.Map;


public class TextTemplateParser implements TemplateParser{

	@Override
	public String parse(Parsable template, Map<String, Object> data) {
		return template.getBody();
	}

}
