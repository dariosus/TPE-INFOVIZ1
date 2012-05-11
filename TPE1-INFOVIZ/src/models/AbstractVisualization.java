package models;

public abstract class AbstractVisualization {

	private String name;
	private String xName;
	private String yName;

	public String getName() {
		return this.name;
	}

	public String getxName() {
		return this.xName;
	}

	public String getyName() {
		return this.yName;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setxName(final String xName) {
		this.xName = xName;
	}

	public void setyName(final String yName) {
		this.yName = yName;
	}
}
