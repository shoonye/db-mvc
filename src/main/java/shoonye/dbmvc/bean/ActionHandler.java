package shoonye.dbmvc.bean;

public interface ActionHandler <R extends Object, I extends Object>{
	R execute(I input);
}
