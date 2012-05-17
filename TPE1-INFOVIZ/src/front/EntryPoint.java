package front;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import models.CallsValue;
import models.Comparators;
import models.Function;
import models.JavaParser;
import models.Module;

public class EntryPoint {

	public static void main(final String[] args) {

		final String filenameLines = "linesCompartor";
		final String filenameCompartor = "parametersCompartor";

		// final int height = Integer.parseInt(args[0]);
		// final int width = Integer.parseInt(args[1]);
		final int width = 1000;
		final int WIDTH = 25;
		final int toleratedLines = Integer.parseInt(args[0]);
		final int limitedLines = Integer.parseInt(args[1]);
		final int toleratedParameters = Integer.parseInt(args[2]);
		final int limitedParameters = Integer.parseInt(args[3]);

		final String title = "Java Code Analizer";
		final String titleParameters = "Java Code Parameters";
		final Comparators comparators = new Comparators();

		final SortedSet<Function> functionsLines = new TreeSet<Function>(
				comparators.getLinesComparator());

		final SortedSet<Function> functionsParameters = new TreeSet<Function>(
				comparators.getLinesComparator());

		final SortedSet<Function> functionsBoxPlot = new TreeSet<Function>(
				comparators.getLinesComparator());

		final JavaParser javaparser = JavaParser.getInstance();

		final Map<String, Float> LinesPerMethod = javaparser.getLinesPerFile();

		final Map<String, Float> parametersPerMethod = javaparser
				.getParametersPerMethod();

		final Map<String, Float> parametersCalls = javaparser
				.getDependencyPerPackage();

		final Map<String, Float> parametersCalled = javaparser
				.getDepencePerPackage();

		final Map<String, CallsValue> mapDependency = new HashMap<String, CallsValue>();

		for (final Entry<String, Float> entry : parametersCalls.entrySet()) {
			if (!mapDependency.containsKey(entry.getKey())) {

				mapDependency.put(entry.getKey(),
						new CallsValue(entry.getValue()));
			}
		}

		for (final Entry<String, Float> entry : parametersCalled.entrySet()) {
			if (!mapDependency.containsKey(entry.getKey())) {
				mapDependency.put(entry.getKey(),
						new CallsValue(0, entry.getValue()));
			} else {
				mapDependency.get(entry.getKey()).setCalled(entry.getValue());
			}
		}

		for (final Entry<String, CallsValue> entry : mapDependency.entrySet()) {

			functionsBoxPlot.add(new Function(20, entry.getValue().getCalled(),
					entry.getValue().getCalls(), 39, true, true,
					entry.getKey(), new Module("Module", null)));

		}

		for (final Entry<String, Float> entry : LinesPerMethod.entrySet()) {
			if (entry.getValue() > limitedLines) {
				functionsLines
						.add(new Function(entry.getValue(), 41, 40, 39, true,
								true, entry.getKey(),
								new Module("Module", null)));
			}

		}

		for (final Entry<String, Float> entry2 : parametersPerMethod.entrySet()) {
			if (entry2.getValue() > limitedParameters) {
				functionsParameters.add(new Function(entry2.getValue(), 41, 40,
						39, true, true, entry2.getKey(), new Module("Module",
								null)));
			}

		}

		final int heightLines = functionsLines.size() * WIDTH;
		final int heightParameters = functionsParameters.size() * WIDTH;

		generateVisualization(filenameLines, heightLines, width,
				toleratedLines, title, functionsLines, "lines");
		generateVisualization(filenameCompartor, heightParameters, width,
				toleratedParameters, titleParameters, functionsParameters,
				"parameters");

		generateVisualizationBoxplot("functionsBoxPlot", 700, 700, 10, 20,
				"TITULO", functionsBoxPlot);

	}

	public static void generateVisualization(String filename, final int height,
			final int width, final int tolerated, final String title,
			final Set<Function> functions, final String criteria) {

		filename = filename + ".htm";
		File file;
		file = new File(filename);
		try {
			file.createNewFile();
			final FileWriter fileStream = new FileWriter(filename);
			final BufferedWriter out = new BufferedWriter(fileStream);

			out.write("<HTML>\n");
			out.write("<applet code=\"swiftchart.class\" width=\"" + width
					+ "\" height=\"" + height + "\">\n");

			out.write("<param name=\"" + "chart_type" + "\" value=\""
					+ "horbar" + "\">\n");
			out.write("<param name=\"" + "chart_border_display" + "\" value=\""
					+ "N" + "\">\n");
			out.write("<param name=\"" + "1x_axis_font_orientation"
					+ "\" value=\"" + "LEFT" + "\">\n");
			out.write("<param name=\"" + "applet_bg" + "\" value=\"" + "CCDDFF"
					+ "\">\n");
			out.write("<param name=\"" + "chart_bg" + "\" value=\"" + "FFFFFF"
					+ "\">\n");
			out.write("<param name=\"" + "title_font_size" + "\" value=\"" + 0
					+ "\">\n");
			out.write("<param name=\"" + "title_sub1_text" + "\" value=\""
					+ title + "\">\n");
			out.write("<param name=\"" + "title_sub1_font_size" + "\" value=\""
					+ 20 + "\">\n");
			out.write("<param name=\"" + "x_axis_font_color" + "\" value=\""
					+ "000000" + "\">\n");
			out.write("<param name=\"" + "x_axis_font_size" + "\" value=\""
					+ 12 + "\">\n");
			out.write("<param name=\"" + "y_axis_font_color" + "\" value=\""
					+ "000000" + "\">\n");
			out.write("<param name=\"" + "y_axis_font_size" + "\" value=\""
					+ 12 + "\">\n");
			out.write("<param name=\"" + "y_axis_text_align" + "\" value=\""
					+ "LEFT" + "\">\n");
			out.write("<param name=\"" + "x_axis_value_display" + "\" value=\""
					+ "Y" + "\">\n");
			out.write("<param name=\"" + "legend_position" + "\" value=\""
					+ "NONE" + "\">\n");
			out.write("<param name=\"" + "legend_font_color" + "\" value=\""
					+ "000000" + "\">\n");
			out.write("<param name=\"" + "legend_border_color" + "\" value=\""
					+ "CCDDFF" + "\">\n");
			out.write("<param name=\"" + "legend_font_size" + "\" value=\""
					+ 10 + "\">\n");
			out.write("<param name=\"" + "data_value" + "\" value=\"" + "AUTO"
					+ "\">\n");
			out.write("<param name=\"" + "data_value_font_color"
					+ "\" value=\"" + "000000" + "\">\n");
			out.write("<param name=\"" + "data_value_font_size" + "\" value=\""
					+ 12 + "\">\n");
			out.write("<param name=\"" + "grid_line_hor" + "\" value=\"" + "N"
					+ "\">\n");
			out.write("<param name=\"" + "grid_line_ver" + "\" value=\"" + "N"
					+ "\">\n");
			out.write("<param name=\"" + "x_value" + "\" value=\"");
			int i = 1;

			for (final Function func : functions) {
				out.write(func.getName());
				if (i < functions.size()) {
					out.write(',');
				}
				i++;
			}

			out.write("\">\n");

			i = 1;
			out.write("<param name=\"" + "s" + 1 + "_value" + "\" value=\"");

			for (final Function func : functions) {
				out.write("" + func.getLines());
				if (i < functions.size()) {
					out.write(',');
				}
				i++;
			}

			out.write("\">\n");
			out.write("<param name=\"s1_label\" value=\"asd\">\n");
			out.write("<param name=\"" + "s" + 1 + "_color" + "\" value=\"");
			i = 1;
			for (final Function func : functions) {
				if (func.getLines() > tolerated) {
					out.write("FF0000");
				} else {
					out.write("6E6E6E");
				}
				if (i < functions.size()) {
					out.write(',');
				}
				i++;
			}
			out.write("\">\n");
			out.write("<param name=\"" + "s" + 1 + "_bar_fill" + "\" value=\""
					+ 21 + "\">\n");
			out.write("<param name=\"" + "y1_target" + "\" value=\""
					+ tolerated + "\">\n");
			out.write("<param name=\"" + "y1_target_label" + "\" value=\""
					+ "Tolerated " + criteria + " = " + tolerated + "\">\n");
			out.write("<param name=\"" + "y1_target_color" + "\" value=\""
					+ "FF0000" + "\">\n");
			out.write("<param name=\"" + "y1_target_line_weight"
					+ "\" value=\"" + "3" + "\">\n");

			out.write("</applet>\n");
			out.write("</HTML>");

			out.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void generateVisualizationBoxplot(String filename,
			final int height, final int width, final int toleratedCalls,
			final int toleratedCalled, final String title,
			final Set<Function> functions) {

		filename = filename + ".htm";
		File file;
		file = new File(filename);
		try {
			file.createNewFile();
			final FileWriter fileStream = new FileWriter(filename);
			final BufferedWriter out = new BufferedWriter(fileStream);

			out.write("<HTML>\n");
			out.write("<applet code=\"swiftchart.class\" width=\"" + width
					+ "\" height=\"" + height + "\">\n");

			out.write("<param name=\"" + "chart_type" + "\" value=\"" + "xy"
					+ "\">\n");
			out.write("<param name=\"" + "applet_bg" + "\" value=\"" + "EEEEEE"
					+ "\">\n");
			out.write("<param name=\"" + "chart_bg" + "\" value=\"" + "FFFFCC"
					+ "\">\n");
			out.write("<param name=\"" + "title_font_size" + "\" value=\"" + 0
					+ "\">\n");
			out.write("<param name=\"" + "title_sub1_text" + "\" value=\""
					+ title + "\">\n");
			out.write("<param name=\"" + "title_sub1_font_size" + "\" value=\""
					+ 20 + "\">\n");
			out.write("<param name=\"" + "title_sub2_text" + "\" value=\""
					+ "Cantidad de llamadas a la funcion toleradas: " + 
					toleratedCalled  + ". Cantidad de llamadas que puede " +
					"hacer una funcion: " + toleratedCalls + "\">\n");
			out.write("<param name=\"" + "title_sub2_font_size" + "\" value=\""
					+ 10 + "\">\n");
			out.write("<param name=\"" + "x_axis_font_color" + "\" value=\""
					+ "000000" + "\">\n");
			out.write("<param name=\"" + "x_axis_font_size" + "\" value=\""
					+ 12 + "\">\n");
			out.write("<param name=\"" + "y_axis_font_color" + "\" value=\""
					+ "000000" + "\">\n");
			out.write("<param name=\"" + "y_axis_font_size" + "\" value=\""
					+ 12 + "\">\n");
			out.write("<param name=\"" + "y_axis_text_align" + "\" value=\""
					+ "LEFT" + "\">\n");
			out.write("<param name=\"" + "x_axis_value_display" + "\" value=\""
					+ "Y" + "\">\n");
			out.write("<param name=\"" + "legend_position" + "\" value=\""
					+ "RIGHT" + "\">\n");
			out.write("<param name=\"" + "legend_font_color" + "\" value=\""
					+ "000000" + "\">\n");
			out.write("<param name=\"" + "legend_border_color" + "\" value=\""
					+ "CCDDFF" + "\">\n");
			out.write("<param name=\"" + "legend_font_size" + "\" value=\""
					+ 10 + "\">\n");
			out.write("<param name=\"" + "grid_line_hor" + "\" value=\"" + "Y"
					+ "\">\n");
			out.write("<param name=\"" + "grid_line_ver" + "\" value=\"" + "Y"
					+ "\">\n");
			out.write("<param name=\"" + "grid_line_hor_type" + "\" value=\""
					+ 0 + "\">\n");
			out.write("<param name=\"" + "grid_line_ver_type" + "\" value=\""
					+ 0 + "\">\n");
			

			out.write("<param name=\"" + "s1_line_marker_type" + "\" value=\""
					+ 1 + "\">\n");
			out.write("<param name=\"" + "s1_color" + "\" value=\"" + "00CC00"
					+ "\">\n");
			out.write("<param name=\"" + "s1_line_marker" + "\" value=\""
					+ "ONLY" + "\">\n");
			out.write("<param name=\"" + "s1_label" + "\" value=\"" + "Son poco llamadas y hacen pocas llamadas"
					+ "\">\n");

			out.write("<param name=\"" + "s2_line_marker_type" + "\" value=\""
					+ 8 + "\">\n");
			out.write("<param name=\"" + "s2_color" + "\" value=\"" + "CC0000"
					+ "\">\n");
			out.write("<param name=\"" + "s2_line_marker" + "\" value=\""
					+ "ONLY" + "\">\n");
			out.write("<param name=\"" + "s2_label" + "\" value=\"" + "Son muy llamadas pero hacen pocas llamadas"
					+ "\">\n");

			out.write("<param name=\"" + "s3_line_marker_type" + "\" value=\""
					+ 7 + "\">\n");
			out.write("<param name=\"" + "s3_color" + "\" value=\"" + "0000CC"
					+ "\">\n");
			out.write("<param name=\"" + "s3_line_marker" + "\" value=\""
					+ "ONLY" + "\">\n");
			out.write("<param name=\"" + "s3_label" + "\" value=\"" + "Son poco llamadas y hacen muchas llamadas"
					+ "\">\n");

			out.write("<param name=\"" + "s4_line_marker_type" + "\" value=\""
					+ 3 + "\">\n");
			out.write("<param name=\"" + "s4_color" + "\" value=\"" + "00CCCC"
					+ "\">\n");
			out.write("<param name=\"" + "s4_line_marker" + "\" value=\""
					+ "ONLY" + "\">\n");
			out.write("<param name=\"" + "s4_label" + "\" value=\"" + "Son muy llamadas y hacen muchas llamadas"
					+ "\">\n");

			out.write("<param name=\"" + "xy_pairs" + "\" value=\"" + "Y"
					+ "\">\n");

			int cant;

			out.write("<param name=\"" + "xy1_value" + "\" value=\"");
			cant = 0;
			System.out.println("Funciones que son poco llamadas y hacen pocas llamadas");
			for (final Function func : functions) {
				if (func.getCalled() < toleratedCalled
						&& func.getCalls() < toleratedCalls) {
					if (cant != 0) {
						out.write(",");
					}
					out.write(func.getCalled() + ":" + func.getCalls());
					System.out.println(func.getName() + ": Llamadas a ella = " 
							+ func.getCalled() + ". Llamadas hechas = " 
							+ func.getCalls() + ".");
					cant++;
				}
			}
			out.write("\">\n");

			out.write("<param name=\"" + "xy2_value" + "\" value=\"");
			System.out.println("Funciones que son muy llamadas y hacen pocas llamadas");
			cant = 0;
			for (final Function func : functions) {
				if (func.getCalled() >= toleratedCalled
						&& func.getCalls() < toleratedCalls) {
					if (cant != 0) {
						out.write(",");
					}
					out.write(func.getCalled() + ":" + func.getCalls());
					System.out.println(func.getName() + ": Llamadas a ella = " 
							+ func.getCalled() + ". Llamadas hechas = " 
							+ func.getCalls() + ".");
					cant++;
				}
			}
			out.write("\">\n");

			out.write("<param name=\"" + "xy3_value" + "\" value=\"");
			System.out.println("Funciones que son poco llamadas pero hacen muchas llamadas");
			cant = 0;
			for (final Function func : functions) {
				if (func.getCalled() < toleratedCalled
						&& func.getCalls() >= toleratedCalls) {
					if (cant != 0) {
						out.write(",");
					}
					out.write(func.getCalled() + ":" + func.getCalls());
					System.out.println(func.getName() + ": Llamadas a ella = " 
							+ func.getCalled() + ". Llamadas hechas = " 
							+ func.getCalls() + ".");
					cant++;
				}
			}
			out.write("\">\n");

			out.write("<param name=\"" + "xy4_value" + "\" value=\"");
			System.out.println("Funciones que son muy llamadas y hacen muchas llamadas");
			cant = 0;
			for (final Function func : functions) {
				if (func.getCalled() >= toleratedCalled
						&& func.getCalls() >= toleratedCalls) {
					if (cant != 0) {
						out.write(",");
					}
					out.write(func.getCalled() + ":" + func.getCalls());
					System.out.println(func.getName() + ": Llamadas a ella = " 
							+ func.getCalled() + ". Llamadas hechas = " 
							+ func.getCalls() + ".");
					cant++;
				}
			}

			out.write("\">\n");

			out.write("<param name=\"" + "x_axis_title" + "\" value=\""
					+ "Y" + "\">\n");
			out.write("<param name=\"" + "y_axis_title" + "\" value=\""
					+ "Y" + "\">\n");
			out.write("<param name=\"" + "x_axis_title_text" + "\" value=\""
					+ "Cantidad de llamadas a la funcion" + "\">\n");
			out.write("<param name=\"" + "y_axis_title_text" + "\" value=\""
					+ "Cantidad de veces que la funcion es llamada" + "\">\n");
			
			out.write("</applet>\n");
			out.write("</HTML>");

			out.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
