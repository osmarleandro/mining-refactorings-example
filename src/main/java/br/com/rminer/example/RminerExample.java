package br.com.rminer.example;

import java.util.List;

import org.eclipse.jgit.lib.Repository;
import org.refactoringminer.api.GitHistoryRefactoringMiner;
import org.refactoringminer.api.GitService;
import org.refactoringminer.api.Refactoring;
import org.refactoringminer.api.RefactoringHandler;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;

/**
 * Running examples from RefactoringMiner.
 * 
 * @author Osmar Leandro <http://github.com/osmarleandro>
 * @see {@link https://github.com/tsantalis/RefactoringMiner}
 */
public class RminerExample {
	public static void main(String[] args) throws Exception {

		GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

		Repository repo = clone("https://github.com/danilofes/refactoring-toy-example.git");

		detectAll(miner, repo);

		detectBetweenCommits(miner, repo);

		detectBetweenTags(miner, repo);

		detectAtCommit(miner, repo);
	}

	private static Repository clone(String gitUrl) throws Exception {
		GitService gitService = new GitServiceImpl();
		return gitService.cloneIfNotExists("tmp/refactoring-toy-example", gitUrl);
	}

	private static void detectAtCommit(GitHistoryRefactoringMiner miner, Repository repo) {
		miner.detectAtCommit(repo, "05c1e773878bbacae64112f70964f4f2f7944398", new RefactoringHandler() {
			@Override
			public void handle(String commitId, List<Refactoring> refactorings) {
				System.out.println("Refactorings at " + commitId);
				for (Refactoring ref : refactorings) {
					System.out.println(ref.toString());
				}
			}
		});
	}

	private static void detectBetweenTags(GitHistoryRefactoringMiner miner, Repository repo) throws Exception {
		// start tag: 1.0
		// end tag: 1.1
		miner.detectBetweenTags(repo, "1.0", "1.1", new RefactoringHandler() {
			@Override
			public void handle(String commitId, List<Refactoring> refactorings) {
				System.out.println("Refactorings at " + commitId);
				for (Refactoring ref : refactorings) {
					System.out.println(ref.toString());
				}
			}
		});
	}

	private static void detectBetweenCommits(GitHistoryRefactoringMiner miner, Repository repo) throws Exception {
		// start commit: 819b202bfb09d4142dece04d4039f1708735019b
		// end commit: d4bce13a443cf12da40a77c16c1e591f4f985b47
		miner.detectBetweenCommits(repo, "819b202bfb09d4142dece04d4039f1708735019b",
				"d4bce13a443cf12da40a77c16c1e591f4f985b47", new RefactoringHandler() {
					@Override
					public void handle(String commitId, List<Refactoring> refactorings) {
						System.out.println("Refactorings at " + commitId);
						for (Refactoring ref : refactorings) {
							System.out.println(ref.toString());
						}
					}
				});
	}

	private static void detectAll(GitHistoryRefactoringMiner miner, Repository repo) throws Exception {
		miner.detectAll(repo, "master", new RefactoringHandler() {
			@Override
			public void handle(String commitId, List<Refactoring> refactorings) {
				System.out.println("Refactorings at " + commitId);
				for (Refactoring ref : refactorings) {
					System.out.println(ref.toString());
				}
			}
		});
	}
}
