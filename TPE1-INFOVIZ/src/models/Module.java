package models;

import java.util.Set;

public class Module {
	
	public String name;
	public Set<Function> functions;
	
	public Module() {
		// TODO Auto-generated constructor stub
	}
	
	public Module(String name, Set<Function> functions){
		this.name = name;
		this.functions = functions;
	}
	
	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Function> getFunctions() {
		return functions;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Module mod2) {
		return name.equals(mod2.getName());
		
	}
	
	public void addFunction(Function func) {
		functions.add(func);
	}
	
	public boolean containsFunc(Function func) {
		return functions.contains(func);
	}
}
