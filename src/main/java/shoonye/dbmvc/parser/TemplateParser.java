package shoonye.dbmvc.parser;

import java.util.Map;


public interface TemplateParser {
	String parse(Parsable template, Map<String,Object> data);
}
