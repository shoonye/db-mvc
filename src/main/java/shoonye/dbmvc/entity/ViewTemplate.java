package shoonye.dbmvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import shoonye.dbmvc.parser.Parsable;
import shoonye.util.hibernate.AuditedEntity;


@Audited
@Entity
@Table(name="view_template")
public class ViewTemplate extends AuditedEntity<Integer> implements Parsable{
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id private Integer id;
	
	@Column(name="screen_key", length=50) private String key;
	@Column(name="name", length=200) private String name;
	@Enumerated(EnumType.STRING) @Column(name="language", length=20) private TemplateLanguage language;
	@Column(name="template_text",length=4000) private String body;
	
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
	public TemplateLanguage getLanguage() {
		return language;
	}
	public void setLanguage(TemplateLanguage language) {
		this.language = language;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}