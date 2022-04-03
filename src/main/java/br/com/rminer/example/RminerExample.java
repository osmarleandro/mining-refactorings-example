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
 * @author Osmar Leandro <https://github.com/osmarleandro>
 * @see {@link https://github.com/tsantalis/RefactoringMiner}
 */
public class RminerExample {
	public static void main(String[] args) throws Exception {

		Repository repo = clone("https://github.com/osmarleandro/refactoring-toy-example.git");

		String commitId = "05c1e773878bbacae64112f70964f4f2f7944398";
		detectAtCommit(repo, commitId);

		String startTag = "1.0";
		String endTag = "1.1";
		detectBetweenTags(repo, startTag, endTag);

		String startCommitId = "819b202bfb09d4142dece04d4039f1708735019b";
		String endCommitId = "d4bce13a443cf12da40a77c16c1e591f4f985b47";
		detectBetweenCommits(repo, startCommitId, endCommitId);

		String branch = "master";
		detectAll(repo, branch);
	}

	public static Repository clone(String gitUrl) throws Exception {
		GitService gitService = new GitServiceImpl();
		return gitService.cloneIfNotExists("tmp/refactoring-toy-example", gitUrl);
	}

	public static void detectAtCommit(Repository repo, String commitId) {
		GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

		miner.detectAtCommit(repo, commitId, new RefactoringHandler() {
			@Override
			public void handle(String commitId, List<Refactoring> refactorings) {
				System.out.println("Refactorings at " + commitId);
				for (Refactoring ref : refactorings) {
					System.out.println(ref.toString());
				}
			}
		});
	}

	public static void detectBetweenTags(Repository repo, String startTag, String endTag) throws Exception {
		GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

		miner.detectBetweenTags(repo, startTag, endTag, new RefactoringHandler() {
			@Override
			public void handle(String commitId, List<Refactoring> refactorings) {
				System.out.println("Refactorings at " + commitId);
				for (Refactoring ref : refactorings) {
					System.out.println(ref.toString());
				}
			}
		});
	}

	public static void detectBetweenCommits(Repository repo, String startCommitId, String endCommitId)
			throws Exception {
		GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

		miner.detectBetweenCommits(repo, startCommitId, endCommitId, new RefactoringHandler() {
			@Override
			public void handle(String commitId, List<Refactoring> refactorings) {
				System.out.println("Refactorings at " + commitId);
				for (Refactoring ref : refactorings) {
					System.out.println(ref.toString());
				}
			}
		});
	}

	public static void detectAll(Repository repo, String branch) throws Exception {
		GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

		miner.detectAll(repo, branch, new RefactoringHandler() {
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
