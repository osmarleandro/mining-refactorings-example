package br.com.refdiff.example;

import java.io.File;

import br.com.util.ExtractPattern;
import refdiff.core.RefDiff;
import refdiff.core.diff.CstDiff;
import refdiff.core.diff.Relationship;
import refdiff.parsers.java.JavaPlugin;

/**
 * Running examples from RefDiff.
 * 
 * @author Osmar Leandro <https://github.com/osmarleandro>
 * @see {@link https://github.com/aserg-ufmg/RefDiff}
 */
public class RefDiffExample {

	public static void main(String[] args) throws Exception {
		// This examples checkout the repository to temp folder
		String cloneUrl = "https://github.com/osmarleandro/refactoring-toy-example.git";
		String commitHash = "0d3a06c";
		runJavaExample(cloneUrl, commitHash);

		// This examples we analyze a local repo
		String path = "/mnt/HGST400/home/olds/git/msc/refactoring-toy-example";
		commitHash = "ff73c8";
		runJavaLocalExample(path, commitHash);
	}

	/**
	 * Detects at remote git reposotiry.
	 * 
	 * @param
	 * @throws Exception
	 */
	public static void runJavaExample(String cloneUrl, String commitHash) throws Exception {
		String repoName = ExtractPattern.findRepositoryName(cloneUrl);
		File tempFolder = new File("tmp");

		JavaPlugin javaPlugin = new JavaPlugin(tempFolder);
		RefDiff refDiffJava = new RefDiff(javaPlugin);

		File repo = refDiffJava.cloneGitRepository(new File(tempFolder, repoName), cloneUrl);

		printRefactorings(String.format("Refactorings found in %s of %s", repoName, commitHash),
				refDiffJava.computeDiffForCommit(repo, commitHash));
	}

	/**
	 * Detects at local git repository.
	 * 
	 * @param the complete path for the local git project folder
	 * @param the sha1 commit
	 */
	public static void runJavaLocalExample(String folder, String commitHash) {
		File repoFolder = new File(folder + "/.git");
		JavaPlugin javaPlugin = new JavaPlugin(repoFolder);
		RefDiff refDiffJava = new RefDiff(javaPlugin);

		CstDiff diff = refDiffJava.computeDiffForCommit(repoFolder, commitHash);

		printRefactorings(String.format("Refactorings found in %s %s", folder, commitHash), diff);
	}

	private static void printRefactorings(String headLine, CstDiff diff) {
		System.out.println(headLine);
		
		for (Relationship rel : diff.getRefactoringRelationships()) {
			System.out.println(rel.getStandardDescription());
		}
	}

}
