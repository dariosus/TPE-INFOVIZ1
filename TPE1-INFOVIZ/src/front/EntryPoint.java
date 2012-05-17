package front;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import models.Comparators;
import models.Function;
import models.JavaParser;
import models.Module;

public class EntryPoint {
	public static void main(final String[] args) {
		String filename = "test1";
		final int height = 600;
		final int width = 1100;
		final int tolerated = 5;
		final String title = "Java Code Analizer";
		final Comparators comparators = new Comparators();
		final SortedSet<Function> functions = new TreeSet<Function>(
				comparators.getLinesComparator());

		final SortedSet<Function> functionsParameters = new TreeSet<Function>(
				comparators.getLinesComparator());

		final JavaParser javaparser = JavaParser.getInstance();

		final Map<String, Float> commentsPerMethod = javaparser
				.getCommentsPerMethod();

		final Map<String, Float> parametersPerMethod = javaparser
				.getParametersPerMethod();

		for (final Entry<String, Float> entry : commentsPerMethod.entrySet()) {
			functions.add(new Function(entry.getValue(), 41, 40, 39, true,
					true, entry.getKey(), new Module("Module", null)));
		}

		// final Function func1 = new Function(38, 41, 40, 39, true, true,
		// "Test1", new Module("Module", null));
		// functions.add(func1);
		//
		// final Function func2 = new Function(42, 41, 40, 39, true, true,
		// "Test2", new Module("Module", null));
		// functions.add(func2);

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

			out.write("<param name=\"" + "chart_type" + "\" value=\"" + "bar"
					+ "\">\n");
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
					+ "N" + "\">\n");
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
					out.write("00FF00");
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
					+ "Tolerated Lines" + "\">\n");
			out.write("<param name=\"" + "y1_target_color" + "\" value=\""
					+ "0000FF" + "\">\n");

			out.write("</applet>\n");
			out.write("</HTML>");

			out.close();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
