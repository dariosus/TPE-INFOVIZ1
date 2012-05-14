package models;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class JavaParser implements IParser {

	static JavaParser parserInstance;
	ParsedCode parsedCodeimpl;

	// Cableado
	private static String path = "MetricResults.xml";

	public static void main(final String[] args) {
		System.out.println("HOLA");
		final JavaParser instance = JavaParser.getInstance();
		System.out.println("CHAU");
		Map<String, Float> map = new HashMap<String, Float>();
		map = instance.getLinesPerFile();
		System.out.println(map.toString());
		map = instance.getCommentsPerMethod();
		System.out.println(map.toString());
		map = instance.getDepencePerPackage();
		System.out.println(map.toString());
		map = instance.getDependencyPerPackage();
		System.out.println(map.toString());
		map = instance.getLinesAvereagePerMethodPerFile();
		System.out.println(map.toString());
		map = instance.getParametersPerMethod();
		System.out.println(map.toString());
	}

	public JavaParser() {
		this.parsedCodeimpl = this.parseXML(path);
	}

	public static JavaParser getInstance() {

		if (parserInstance == null) {
			parserInstance = new JavaParser();
		}

		return parserInstance;
	}

	private ParsedCode parseXML(final String path) {

		final ParsedCode parsedCode = new ParsedCode();

		try {
			final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			final DocumentBuilder docBuilder = docBuilderFactory
					.newDocumentBuilder();
			final Document doc = docBuilder.parse(new File(path));

			// normalize text representation
			doc.getDocumentElement().normalize();
			System.out.println("Root element of the doc is "
					+ doc.getDocumentElement().getNodeName());

			final NodeList listOfmetricScoperesult = doc
					.getElementsByTagName("metric-result-scope");

			for (int s = 0; s < listOfmetricScoperesult.getLength(); s++) {

				final Node firstMetricNode = listOfmetricScoperesult.item(s);
				final Element scopeElement = (Element) firstMetricNode;

				final NodeList listOfmetricResults = firstMetricNode
						.getChildNodes();

				final String functionName = scopeElement.getAttribute("scope");
				System.out.println("NODO NOMBRE:        " + functionName);

				// Agrego la function
				parsedCode.addFunction(functionName);

				for (int n = 0; n < listOfmetricResults.getLength(); n++) {

					if (!(n % 2 == 0)) {// No me preguntes porque es modulo dos,
										// escapa de mi conocimiento jaja.
						final Node metricNode = listOfmetricResults.item(n);
						final Element metricElement = (Element) metricNode;

						final String metricName = metricElement
								.getAttribute("name");
						final String valueName = metricElement
								.getAttribute("value");
						final Float value;

						if (valueName.contains("%")) {
							final String valuesName[] = valueName.split("%");
							value = Float.valueOf(valuesName[0]);
						} else if (valueName.contains(",")) {
							value = (float) 0;
						} else {
							value = Float.valueOf(valueName);
						}

						parsedCode.addMetricToFunction(functionName,
								metricName, value);

						System.out.println(metricName + ": " + value);
					}

				}

			}// end of for loop with s var

		} catch (final SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (final SAXException e) {
			final Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (final Throwable t) {
			t.printStackTrace();
		}

		return parsedCode;

	}

	@Override
	public Map<String, Float> getLinesPerFile() {
		return this.parsedCodeimpl.getBuilder("Lines of Code");
	}

	@Override
	public Map<String, Float> getParametersPerMethod() {
		return this.parsedCodeimpl.getBuilder("Average Number of Parameters");
	}

	@Override
	public Map<String, Float> getCommentsPerMethod() {
		return this.parsedCodeimpl.getBuilder("Number of Comments");
	}

	@Override
	public Map<String, Float> getLinesAvereagePerMethodPerFile() {
		return this.parsedCodeimpl
				.getBuilder("Average Lines Of Code Per Method");
	}

	@Override
	public Map<String, Float> getDependencyPerPackage() {
		return this.parsedCodeimpl.getPackageBuilder("Efferent Couplings");
	}

	@Override
	public Map<String, Float> getDepencePerPackage() {
		return this.parsedCodeimpl.getPackageBuilder("Afferent Couplings");
	}

}
