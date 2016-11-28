package io.github.sleroy.code.parameters.syntaxindexer;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.python.util.PythonInterpreter;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * The Class LexicalAnalysisTool performs a lexical analysis using a python
 * library "Pygments"
 */
public class LexicalAnalysisTool {

	/**
	 * Analysis a source code and produces a lexical metrics.
	 *
	 * @param code
	 *            the code
	 */
	public void analysis(String code) {
		try (PythonInterpreter interpreter = new PythonInterpreter()) {

			// Set a variable with the content you want to work with
			interpreter.set("code", code);

			// Simple use Pygments as you would in Python
			interpreter.exec("from pygments import highlight\n" + "from pygments.lexers import PythonLexer\n"
					+ "from pygments.formatters import HtmlFormatter\n"
					+ "\nresult = highlight(code, PythonLexer(), HtmlFormatter())");

			parseXMLAnalysis(interpreter.get("result", String.class));
		}

	}

	private static final Logger LOGGER = LoggerFactory.getLogger(LexicalAnalysisTool.class);

	/**
	 * Parses the XML analysis from Pygments
	 *
	 * @param lexicalAnalysis
	 *            the lexical analysis
	 */
	private void parseXMLAnalysis(String lexicalAnalysis) {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document dDoc = builder.parse(new InputSource(new StringReader(lexicalAnalysis)));
			NodeList nl;

			// OPTION #1
			XPath xPath = XPathFactory.newInstance().newXPath();

			// OPTION #2
			XPathExpression xPathExpression = xPath.compile("/root/device/modelname");
			nl = (NodeList) xPathExpression.evaluate(dDoc, XPathConstants.NODESET);
			printResults(nl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printResults(NodeList nl) {
		for (int x = 0; x < nl.getLength(); x++) {
			System.out.println("the value is: " + nl.item(x).getTextContent());
		}
	}

}
