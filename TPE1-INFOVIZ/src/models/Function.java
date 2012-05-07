package models;

public class Function {
	
	private int lines;
	private int called;
	private int calls;
	private boolean recursive;
	private boolean commented;
	private String name;
	private int parameters;
	private Module module;
	
	public Function() {
		// TODO Auto-generated constructor stub
	}
	
	public Function(int lines, int called, int calls, 
			int parameters, boolean recursive, boolean commented,
			String name, Module module) {
		this.lines = lines;
		this.called = called;
		this.calls = calls;
		this.parameters = parameters;
		this.recursive = recursive;
		this.commented = commented;
		this.module = module;
	}
	
	public int getCalled() {
		return called;
	}
	
	public int getCalls() {
		return calls;
	}
	
	public int getLines() {
		return lines;
	}
	
	public String getName() {
		return name;
	}
	
	public int getParameters() {
		return parameters;
	}
	
	public boolean isCommented() {
		return commented;
	}
	
	public boolean isRecursive() {
		return recursive;
	}
	
	public void setCalled(int called) {
		this.called = called;
	}
	
	public void setCalls(int calls) {
		this.calls = calls;
	}
	
	public void setCommented(boolean commented) {
		this.commented = commented;
	}
	
	public void setLines(int lines) {
		this.lines = lines;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setParameters(int parameters) {
		this.parameters = parameters;
	}
	
	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}
	
	public Module getModule() {
		return module;
	}
	
	public void setModule(Module module) {
		this.module = module;
	}
	
	public boolean equals(Function func2) {
		// TODO Auto-generated method stub
		return name.equals(func2.getName()) && module.equals(func2.getModule()) ;
	}
}
