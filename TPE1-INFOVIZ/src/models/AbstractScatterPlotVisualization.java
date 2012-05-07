package models;

public abstract class AbstractScatterPlotVisualization extends AbstractVisualization {

	private int xLimit;
	private int yLimit;
	
	public int getxLimit() {
		return xLimit;
	}
	
	public int getyLimit() {
		return yLimit;
	}
	
	public void setxLimit(int xLimit) {
		this.xLimit = xLimit;
	}
	
	public void setyLimit(int yLimit) {
		this.yLimit = yLimit;
	}
}
