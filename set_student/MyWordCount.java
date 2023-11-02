package set_student;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MyWordCount {
	// public static final String fileName = "data/hamlet.txt";
	public static final String fileName = "src/data/fit.txt";

	private List<String> words = new ArrayList<>();

	public MyWordCount() {
		try {
			this.words.addAll(Utils.loadWords(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt)
	// In this method, we do not consider the order of tokens.
	public List<WordCount> getWordCounts() {
		List<WordCount> list = new ArrayList<>();
		for (String word : words) {
			WordCount wordCount = new WordCount(word, countWord(word));
			if(!list.contains(wordCount)) list.add(wordCount);
		}
		return list;
	}
	
	public int countWord(String word) {
		int count = 0;
		for (String string : words) {
			if(string.equals(word)) count++;
		}
		return count;
	}

	// Returns the words that their appearance are 1, do not consider duplidated
	// words
	public Set<String> getUniqueWords() {
		Set<String> set = new HashSet<>();
		for (String word : words) {
			if(countWord(word) == 1) set.add(word);
		}
		return set;
	}

	// Returns the words in the text file, duplicated words appear once in the
	// result
	public Set<String> getDistinctWords() {
		Set<String> set = new HashSet<>();
		for (String word : words) {
			set.add(word);
		}
		return set;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according ascending order of tokens
	// Example: An - 3, Bug - 10, ...
	public Set<WordCount> printWordCounts() {
		Set<WordCount> set = new TreeSet<>(new Comparator<WordCount>() {
			@Override
			public int compare(WordCount o1, WordCount o2) {
				if(o1.getCount() == o2.getCount()) return o1.getWord().compareTo(o2.getWord());
				return o1.getCount() - o2.getCount();
			}
		});
		for (String word : words) {
			set.add(new WordCount(word, countWord(word)));
		}
		return set;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according descending order of occurences
	// Example: Bug - 10, An - 3, Nam - 2.
	public Set<WordCount> exportWordCountsByOccurence() {
		Set<WordCount> set = new TreeSet<>(new Comparator<WordCount>() {
			@Override
			public int compare(WordCount o1, WordCount o2) {
				if(o2.getCount() == o1.getCount()) return o2.getWord().compareTo(o1.getWord());
				return o2.getCount() - o1.getCount();
			}
		});
		for (String word : words) {
			set.add(new WordCount(word, countWord(word)));
		}
		return set;
	}

	// delete words begining with the given pattern (i.e., delete words begin with
	// 'A' letter
	public Set<String> filterWords(String pattern) {
		Set<String> set = new HashSet<>();
		for (String word : words) {
			if(word.indexOf(pattern) != 0) set.add(word);
		}
		return set;
	}
	
	public static void main(String[] args) {
		MyWordCount mw = new MyWordCount();
		List<WordCount> arr = mw.getWordCounts();
		System.out.println(arr);
		Set<String> set1 = mw.getUniqueWords();
		System.out.println(set1);
		Set<String> set2 = mw.getDistinctWords();
		System.out.println(set2);
		Set<WordCount> set3 = mw.printWordCounts();
		System.out.println(set3);
		Set<WordCount> set4 = mw.exportWordCountsByOccurence();
		System.out.println(set4);
		Set<String> set5 = mw.filterWords("D");
		System.out.println(set5);
		
	}
	
}
