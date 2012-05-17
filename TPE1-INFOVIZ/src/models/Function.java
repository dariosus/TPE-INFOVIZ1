package models;

public class Function {

	private float lines;
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

	public Function(final float lines, final int called, final int calls,
			final int parameters, final boolean recursive,
			final boolean commented, final String name, final Module module) {
		this.lines = lines;
		this.called = called;
		this.calls = calls;
		this.parameters = parameters;
		this.recursive = recursive;
		this.commented = commented;
		this.name = name;
		this.module = module;
	}

	public int getCalled() {
		return this.called;
	}

	public int getCalls() {
		return this.calls;
	}

	public float getLines() {
		return this.lines;
	}

	public String getName() {
		return this.name;
	}

	public int getParameters() {
		return this.parameters;
	}

	public boolean isCommented() {
		return this.commented;
	}

	public boolean isRecursive() {
		return this.recursive;
	}

	public void setCalled(final int called) {
		this.called = called;
	}

	public void setCalls(final int calls) {
		this.calls = calls;
	}

	public void setCommented(final boolean commented) {
		this.commented = commented;
	}

	public void setLines(final int lines) {
		this.lines = lines;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setParameters(final int parameters) {
		this.parameters = parameters;
	}

	public void setRecursive(final boolean recursive) {
		this.recursive = recursive;
	}

	public Module getModule() {
		return this.module;
	}

	public void setModule(final Module module) {
		this.module = module;
	}

	public boolean equals(final Function func2) {
		// TODO Auto-generated method stub
		return this.name.equals(func2.getName())
				&& this.module.equals(func2.getModule());
	}
}
