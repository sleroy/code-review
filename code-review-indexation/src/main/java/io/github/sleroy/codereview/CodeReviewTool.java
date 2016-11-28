package io.github.sleroy.codereview;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeReviewTool {

	private CodeReviewArgs codeReviewArgs;

	public CodeReviewTool(CodeReviewArgs opts) {
		this.codeReviewArgs = opts;

	}

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeReviewTool.class);

	public void indexSourceFiles() {
		for (String extension : codeReviewArgs.extensions.split(",")) {
			Collection<File> listFiles = FileUtils.listFiles(codeReviewArgs.projectFolder, new AndFileFilter(CanReadFileFilter.CAN_READ, new SuffixFileFilter(extension)), TrueFileFilter.INSTANCE);
			LOGGER.info("Number of files found with extension {}", listFiles.size());
		}

	}

}
