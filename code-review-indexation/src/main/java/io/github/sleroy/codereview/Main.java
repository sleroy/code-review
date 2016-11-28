package io.github.sleroy.codereview;

import static org.junit.Assert.assertNotNull;

import com.beust.jcommander.JCommander;

public class Main {

	public static void main(String[] args) {
		CodeReviewArgs opts = new CodeReviewArgs();
		String[] argv = { "-debug", "-src", "." };
		JCommander jCommander = new JCommander(opts, argv);
		jCommander.usage();


		System.out.println(opts.projectFolder);
		assertNotNull(opts.projectFolder);

		CodeReviewTool codeReviewTool = new CodeReviewTool(opts);
		codeReviewTool.indexSourceFiles();

	}

}
