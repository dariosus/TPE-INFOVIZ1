package models;

import java.util.Comparator;

public class Comparators {

	public Comparator<Function> getLinesComparator() {
		return new Comparator<Function>() {
			public int compare(Function a, Function b) {
				return a.getLines() < b.getLines() ? -1 : 1;
			}
		};
	}
	
	public Comparator<Function> getParametersComparator() {
		return new Comparator<Function>() {
			public int compare(Function a, Function b) {
				return a.getParameters() < b.getParameters() ? -1 : 1;
			}
		};
	}
}
