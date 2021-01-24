package shoonye.dbmvc.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

import shoonye.dbmvc.parser.Parsable;
import shoonye.util.hibernate.AuditedEntity;



@Entity
@Table(name="view_group")
@Audited
public class ViewGroup extends AuditedEntity<Integer> implements Parsable{
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id private Integer id;
	
	@Column(name="screen_name", length=100) private String name;
	@Column(name="view_layout_key", length=50) private String key;
	@Column(name="default_view_key",length=50) private String defaultViewTemplateKey;
	@Column(name="layout",length=4000) private String body;
	@Enumerated(EnumType.STRING) @Column(name="language", length=20) private TemplateLanguage language;

	
	@ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.REFRESH)
	@Fetch(FetchMode.SELECT)
	@JoinTable(name = "view_layout_template_map", 
			joinColumns = { @JoinColumn(name = "view_layout_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "view_template_id") })
	private List<ViewTemplate> viewTemplates;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDefaultViewTemplateKey() {
		return defaultViewTemplateKey;
	}
	public void setDefaultViewTemplateKey(String defaultViewKey) {
		this.defaultViewTemplateKey = defaultViewKey;
	}
		
	@Override
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String getBody() {
		return body;
	}
	public void setBody(String layout) {
		this.body = layout;
	}
	public TemplateLanguage getLanguage() {
		return language;
	}
	public void setLanguage(TemplateLanguage language) {
		this.language = language;
	}
	public void addViewTemplate(ViewTemplate screen){
		if(screen==null) return;
		if(viewTemplates == null) {
			viewTemplates = new LinkedList<ViewTemplate>();
			if(defaultViewTemplateKey==null){
				defaultViewTemplateKey = screen.getKey();
			}
		}
		viewTemplates.add(screen);
	}
	
	public ViewTemplate defaultTemplate(){
		if(viewTemplates!=null){
			if(viewTemplates.size()==1) return viewTemplates.get(0);
			for(ViewTemplate template: viewTemplates){
				if(template.getKey().equalsIgnoreCase(defaultViewTemplateKey)){
					return template;
				}
			}
		}
		return null;
	}
	
	public List<ViewTemplate> getViewTemplates() {
		return viewTemplates;
	}
	
	public void setViewTemplates(List<ViewTemplate> viewTemplates) {
		this.viewTemplates = viewTemplates;
		if(viewTemplates!=null){
			if(defaultViewTemplateKey==null){
				defaultViewTemplateKey = viewTemplates.get(0).getKey();
			}
		}
	}
}
