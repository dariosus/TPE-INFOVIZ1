package models;

public abstract class AbstractVisualization {
	
	private String name;
	private String xName;
	private String yName;
	
	public String getName() {
		return name;
	}
	
	public String getxName() {
		return xName;
	}
	
	public String getyName() {
		return yName;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setxName(String xName) {
		this.xName = xName;
	}
	
	public void setyName(String yName) {
		this.yName = yName;
	}
}
