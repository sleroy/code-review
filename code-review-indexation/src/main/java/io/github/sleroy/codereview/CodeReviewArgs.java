package io.github.sleroy.codereview;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;

public class CodeReviewArgs {
	@Parameter
	List<String> parameters = new ArrayList<>();

	@Parameter(names = "-src", description = "Project folder to scan the source files.", arity=1, required=true)
	File projectFolder;

	@Parameter(names = "-ext", description = "Comma-separated list of extensions to be run.")
	String extensions = "java, gradle";


	@Parameter(names = "-debug", description = "Debug mode")
	boolean debug = false;
}
