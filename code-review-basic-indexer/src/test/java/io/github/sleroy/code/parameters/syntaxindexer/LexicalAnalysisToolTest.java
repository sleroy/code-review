package io.github.sleroy.code.parameters.syntaxindexer;

import org.junit.Test;

public class LexicalAnalysisToolTest {

	@Test
	public void testAnalysis() throws Exception {
		LexicalAnalysisTool lexicalAnalysisTool = new LexicalAnalysisTool();
		lexicalAnalysisTool.analysis("public static class A {}");
	}

}
