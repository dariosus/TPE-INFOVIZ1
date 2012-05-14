package models;

import java.util.Map;

public interface IParser {

	public Map<String, Float> getLinesPerFile();

	public Map<String, Float> getParametersPerMethod();

	public Map<String, Float> getCommentsPerMethod();

	public Map<String, Float> getLinesAvereagePerMethodPerFile();

	public Map<String, Float> getDependencyPerPackage();

	public Map<String, Float> getDepencePerPackage();

}
