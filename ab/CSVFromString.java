package com.yavinci.companies.airbnb;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class CSVFromString {
	// 12:11
	// 例子
	// aa, bb, "aa","aa,aa,bb,cc", "aa"aa""
	// 输出
	// aa|bb|aa|aa,bb,cc|aa”aa”

	public static String stringToCSV(String str) {
		if (str == null || str.length() == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		int i = 0, j = 0, quotes = 0;

		while (j < str.length()) {
			char c = str.charAt(j);
			if (c == '"')
				quotes++;
			if (c == ',' && quotes % 2 == 0) {
				sb.append(trimQuotes(str, i, j));
				sb.append("|");
				i = j + 1;
				quotes = 0;
			}
			j++;
		}
		sb.append(trimQuotes(str, i, j));
		return sb.toString();
	}

	private static String trimQuotes(String str, int i, int j) {
		String sub = str.substring(i, j).trim();
		if (sub.charAt(0) == '\"') {
			sub = sub.substring(1, sub.length() - 1);
		}
		sub.replace("\"\"", "\"");
		return sub;
	}

	public static void main(String[] args) {
		String input = "aa, bb, \"aa\",\"aa,bb\", \"aa\"\"aa\"\"\"";
		String res = stringToCSV(input);
		System.out.println(res);
	}
}
