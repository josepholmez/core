package com.olmez.core.utility;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtility {

	/**
	 * It says it is null or no its length
	 * 
	 * @param str
	 * @return {@code true} if its length is greater than 0
	 */
	public static boolean isEmpty(String str) {
		return (str == null) || str.isEmpty();
	}

	/**
	 * Makes a list of sentences in a given plain text.
	 * 
	 * @param text consists of sentences.
	 * @return list of sentences
	 */
	public static List<String> splitBySentence(String text) {
		List<String> list = new ArrayList<>();
		if (!isEmpty(text)) {
			BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
			iterator.setText(text);
			int start = iterator.first();
			for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
				list.add(text.substring(start, end));
			}
		}
		return list;
	}

	public static String extractBetween(String line, String beforePattern, String afterPattern) {
		int index = line.indexOf(beforePattern);
		if (index < 0) {
			return null;
		}

		int endIndex = line.indexOf(afterPattern, index + beforePattern.length());
		if (endIndex < 0) {
			return null;
		}
		return line.substring(index + beforePattern.length(), endIndex);
	}

	public static String camelCaseToLabel(String label) {
		if (isEmpty(label)) {
			return label;
		}
		String retVal = label.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
		return retVal.substring(0, 1).toUpperCase() + retVal.substring(1);
	}
}
