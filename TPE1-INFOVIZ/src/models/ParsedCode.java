package models;

import java.util.HashMap;
import java.util.Map;

public class ParsedCode implements IParser {

	Map<String, Map<String, Float>> functionsMap = new HashMap<String, Map<String, Float>>();

	public ParsedCode() {
		this.functionsMap = new HashMap<String, Map<String, Float>>();
	}

	public boolean addFunction(final String function) {
		if (this.functionsMap.containsKey(function)) {
			return false;
		} else {
			this.functionsMap.put(function, new HashMap<String, Float>());
		}

		return true;
	}

	public boolean addMetricToFunction(final String function,
			final String metric, final Float value) {

		if (!this.functionsMap.containsKey(function)) {
			this.addFunction(function);
		} else {
			this.functionsMap.get(function).put(metric, value);

		}
		return true;
	}

	public Map<String, Map<String, Float>> getMap() {
		return this.functionsMap;
	}

	public Map<String, Float> getFunction(final String function) {
		if (!this.functionsMap.containsKey(function)) {
			return null;
		}

		return this.functionsMap.get(function);

	}

	public Float getMetric(final String function, final String metric) {
		if (!this.functionsMap.containsKey(function)) {
			return null;
		}

		return this.functionsMap.get(function).get(metric);

	}

	@Override
	public Integer getLinesPerMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLinesPerMethod(final Integer linesPerMethod) {
		// TODO Auto-generated method stub

	}

}
