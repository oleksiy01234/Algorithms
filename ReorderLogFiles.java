import java.util.Arrays;

/**
 * 937. Reorder Data in Log Files
 * https://leetcode.com/problems/reorder-data-in-log-files/
 * 
 * You have an array of logs.  Each log is a space delimited string of words.
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  
 * It is guaranteed that each log has at least one word after its identifier.
 * 
 * Reorder the logs so that 
 * 1. all of the letter-logs come before any digit-log. 
 * 2. The letter-logs are ordered lexicographically ignoring identifier, 
 * 3. with the identifier used in case of ties.  
 * 4. The digit-logs should be put in their original order.
 * 
 * Return the final order of the logs.
 * 
 * E.g In:  ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *  
 * Constraints: 
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
class ReorderLogFiles {
  public String[] reorderLogFiles(String[] logs) {
    Arrays.sort(logs, (log1, log2) -> {
      int spaceIndex1 = log1.indexOf(" ");
      int spaceIndex2 = log2.indexOf(" ");

      String id1 = log1.substring(0, spaceIndex1);
      String id2 = log2.substring(0, spaceIndex2);

      String content1 = log1.substring(spaceIndex1 + 1);
      String content2 = log2.substring(spaceIndex2 + 1);

      boolean isLetter1 = Character.isLetter(content1.charAt(0));
      boolean isLetter2 = Character.isLetter(content2.charAt(0));

      if (isLetter1 && isLetter2) { // both are letter-logs
        if (content1.equals(content2)) { // if contents are the same, compare IDs (rule 3)
          return id1.compareTo(id2);
        } else {
          return content1.compareTo(content2); // compare contents (rule 2)
        }
      } else if (isLetter1) { // only log1 is letter. log1 is less than/before log2 (rule 1)
        return -1;
      } else if (isLetter2) { // only log2 is letter. log1 is more than/after log2 (rule 1)
        return 1;
      } else { // both are digit-logs. So keep them in original order (rule 4).
        return 0;
      }
    });

    return logs;
  }
}