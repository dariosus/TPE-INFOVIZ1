package models;

import java.util.SortedSet;

public class BarVisualization extends AbstractVisualization{

	private int xLimit;
	private SortedSet<Function> functions;
	
	public BarVisualization() {
		// TODO Auto-generated constructor stub
	}
	
	public BarVisualization(int xLimit, SortedSet<Function> functions, 
			String name, String xName, String yName) {
		this.xLimit = xLimit;
		this.functions = functions;
		setName(name);
		setxName(xName);
		setyName(yName);
	}
	
	public int getxLimit() {
		return xLimit;
	}
	
	public SortedSet<Function> getFunctions() {
		return functions;
	}
	
	public void setxLimit(int xLimit) {
		this.xLimit = xLimit;
	}
	
	public void setFunctions(SortedSet<Function> functions) {
		this.functions = functions;
	}
	
	public void addFunction(Function func) {
		functions.add(func);
	}
	
	public boolean containsFunction(Function func) {
		return functions.contains(func);
	}
}
