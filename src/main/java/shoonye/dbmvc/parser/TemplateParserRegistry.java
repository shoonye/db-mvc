package shoonye.dbmvc.parser;

import java.util.HashMap;

import shoonye.dbmvc.entity.TemplateLanguage;


public class TemplateParserRegistry {
	private static HashMap<TemplateLanguage, TemplateParser> map;
	static {
		map = new HashMap<TemplateLanguage, TemplateParser>();
		map.put(TemplateLanguage.FREEMARKER, new FMTemplateParser());
		map.put(TemplateLanguage.TEXT, new TextTemplateParser());
	}
	public static TemplateParser getParser(TemplateLanguage lang){
		return map.get(lang);
	}
}
