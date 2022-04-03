package br.com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractPattern {
	
	public static String findRepositoryName(String gitUrl) throws Exception {
		if (!gitUrl.endsWith(".git"))
			throw new Exception("Git repository pattern not found. The git url should be ends with .git");

		Pattern p = Pattern.compile("/(\\w|\\-)+(\\.git)");
		Matcher m = p.matcher(gitUrl);

		while (m.find()) {
			String repoName = m.group();

			p = Pattern.compile("\\.git");
			String[] splited = p.split(repoName);

			if (splited.length > 1)
				throw new Exception("Git repository name out of the pattern not found.");

			return splited[0].substring(1);
		}

		throw new Exception("Git repository name not found.");

	}
}
