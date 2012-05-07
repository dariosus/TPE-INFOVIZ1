package models;

import java.util.Set;

public class ScatterPlotModuleVisualization extends AbstractScatterPlotVisualization{
	public Set<Module> modules;
	
	public ScatterPlotModuleVisualization() {
		// TODO Auto-generated constructor stub
	}
	
	public ScatterPlotModuleVisualization(int xLimit,
			int yLimit, Set<Module> modules, String name,
			String xName, String yName) {
		this.modules = modules;
		setName(name);
		setxLimit(xLimit);
		setxName(xName);
		setyLimit(yLimit);
		setyName(yName);
	}
	
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	
	public Set<Module> getModules() {
		return modules;
	}
	
	public void addModule(Module mod) {
		modules.add(mod);
	}
	
	public boolean containsModules(Module mod) {
		return modules.contains(mod);
	}
}
