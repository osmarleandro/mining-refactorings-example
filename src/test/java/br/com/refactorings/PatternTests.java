package br.com.refactorings;

import br.com.util.ExtractPattern;

public class PatternTests {

	public static void main(String[] args) throws Exception {
		String gitUrl = "https://github.com/icse18-refactorings/eclipse-themes.git";
		System.out.println(ExtractPattern.findRepositoryName(gitUrl));

		gitUrl = "https://github.com/osmarleandro/refactoring-toy-example";
		System.out.println(ExtractPattern.findRepositoryName(gitUrl));
	}

}
