package models;

import java.util.Map;

public interface IParser {

	public Map<String, Integer> getLinesPerFile();

	public void setLinesPerFile(String file, Integer lines);

	public Map<String, Integer> getParametersPerMethod();

	public void setParameterPerMethod(String Method, Integer lines);

	public Map<String, Integer> getCommentsPerMethod();

	public void setCommentsPerMethod(String Method, Integer lines);

	public Map<String, Integer> getLinesAvereagePerMethodPerFile();

	public void setLinesAvereagePerMethodPerFile(String file, Integer lines);

	public Map<String, Integer> getDependencyPerFile();

	public void setDependencyPerFile(String file, Integer lines);

	public Map<String, Integer> getDepencePerFile();

	public void setDependencePerFile(String file, Integer lines);

}
