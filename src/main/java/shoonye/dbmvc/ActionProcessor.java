package shoonye.dbmvc;



public interface ActionProcessor<O extends Object, I extends Object> {
	public O process(String actionName, I data);
}
