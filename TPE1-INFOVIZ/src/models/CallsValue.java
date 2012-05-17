package models;

public class CallsValue {

	float calls;
	float called;

	public CallsValue() {
	}

	public CallsValue(final float calls) {
		this.calls = calls;
		this.called = (float) 0.0;
	}

	public CallsValue(final float calls, final float called) {
		this.calls = calls;
		this.called = called;
	}

	public float getCalls() {
		return this.calls;
	}

	public void setCalls(final float calls) {
		this.calls = calls;
	}

	public float getCalled() {
		return this.called;
	}

	public void setCalled(final float called) {
		this.called = called;
	}

}