package models;

import java.util.HashMap;
import java.util.Map;

public class ParsedCode {

	Map<String, Map<String, Float>> functionsMap;

	public ParsedCode() {
		this.functionsMap = new HashMap<String, Map<String, Float>>();
	}

	public boolean addFunction(final String function) {
		if (!this.functionsMap.containsKey(function)) {
			this.functionsMap.put(function, new HashMap<String, Float>());
			return true;
		}
		return false;
	}

	public boolean addMetricToFunction(final String function,
			final String metric, final Float value) {

		if (!this.functionsMap.containsKey(function)) {
			this.addFunction(function);
			this.functionsMap.get(function).put(metric, value);
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

	public Map<String, Float> getBuilder(final String parameter) {
		final Map<String, Float> resp = new HashMap<String, Float>();
		for (final Map.Entry<String, Map<String, Float>> entry : this.functionsMap
				.entrySet()) {
			for (final Map.Entry<String, Float> values : entry.getValue()
					.entrySet()) {
				if (values.getKey().equals(parameter)
						&& !entry.getKey().equals("model")
						&& entry.getKey().contains("java")) {
					resp.put(entry.getKey(), values.getValue());
				}
			}
		}

		return resp;
	}

	public Map<String, Float> getPackageBuilder(final String parameter) {
		final Map<String, Float> resp = new HashMap<String, Float>();
		for (final Map.Entry<String, Map<String, Float>> entry : this.functionsMap
				.entrySet()) {
			for (final Map.Entry<String, Float> values : entry.getValue()
					.entrySet()) {
				if (values.getKey().equals(parameter)
						&& !entry.getKey().equals("model")
						&& !entry.getKey().contains("java")) {
					resp.put(entry.getKey(), values.getValue());
				}
			}
		}

		return resp;
	}

}
