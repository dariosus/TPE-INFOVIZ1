package models;

import java.util.Set;

public class ScatterPlotFunctionVisualization extends AbstractScatterPlotVisualization{
	public Set<Function> functions;
	
	public ScatterPlotFunctionVisualization() {
		// TODO Auto-generated constructor stub
	}
	
	public ScatterPlotFunctionVisualization(int xLimit,
			int yLimit, Set<Function> functions, String name,
			String xName, String yName) {
		this.functions = functions;
		setName(name);
		setxLimit(xLimit);
		setxName(xName);
		setyLimit(yLimit);
		setyName(yName);
	}
		
	public Set<Function> getFunctions() {
		return functions;
	}
	
	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}
	
	public void addFunction(Function func) {
		functions.add(func);
	}
	
	public boolean containsFunction(Function func){ 
		return functions.contains(func);
	}
}
