package br.com.refdiff.example;

import java.io.File;

import refdiff.core.RefDiff;
import refdiff.core.diff.CstDiff;
import refdiff.core.diff.Relationship;
import refdiff.parsers.java.JavaPlugin;

/**
 * Running examples from RefDiff.
 * 
 * @author Osmar Leandro <http://github.com/osmarleandro>
 * @see {@link https://github.com/aserg-ufmg/RefDiff}
 */
public class RefDiffExample {

	public static void main(String[] args) {
		// This is a temp folder to clone or checkout git repositories.
		File tempFolder = new File("temp");

		runJavaExample(tempFolder);

	}

	private static void runJavaExample(File tempFolder) {
		// Now, we use the plugin for Java.
		JavaPlugin javaPlugin = new JavaPlugin(tempFolder);
		RefDiff refDiffJava = new RefDiff(javaPlugin);

		File eclipseThemesRepo = refDiffJava.cloneGitRepository(new File(tempFolder, "eclipse-themes"),
				"https://github.com/icse18-refactorings/eclipse-themes.git");

		printRefactorings("Refactorings found in eclipse-themes 72f61ec",
				refDiffJava.computeDiffForCommit(eclipseThemesRepo, "72f61ec"));
	}

	private static void printRefactorings(String headLine, CstDiff diff) {
		System.out.println(headLine);
		for (Relationship rel : diff.getRefactoringRelationships()) {
			System.out.println(rel.getStandardDescription());
		}
	}

}
