
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2016, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.text.linguistic.lucene;

/* 
 * Imported from Apache Lucene.
 * 
 * https://svn.apache.org/repos/asf/lucene/dev/trunk
 * svn rev.: 1534186
 */

/**
 * Some commonly-used stemming functions
 * 
 * @lucene.internal
 */
class StemmerUtil {
  /** no instance */
  private StemmerUtil() {}

  /**
   * Returns true if the character array starts with the suffix.
   * 
   * @param s Input Buffer
   * @param len length of input buffer
   * @param prefix Prefix string to test
   * @return true if <code>s</code> starts with <code>prefix</code>
   */
  public static boolean startsWith(char s[], int len, String prefix) {
    final int prefixLen = prefix.length();
    if (prefixLen > len)
      return false;
    for (int i = 0; i < prefixLen; i++)
      if (s[i] != prefix.charAt(i)) 
        return false;
    return true;
  }
  
  /**
   * Returns true if the character array ends with the suffix.
   * 
   * @param s Input Buffer
   * @param len length of input buffer
   * @param suffix Suffix string to test
   * @return true if <code>s</code> ends with <code>suffix</code>
   */
  public static boolean endsWith(char s[], int len, String suffix) {
    final int suffixLen = suffix.length();
    if (suffixLen > len)
      return false;
    for (int i = suffixLen - 1; i >= 0; i--)
      if (s[len -(suffixLen - i)] != suffix.charAt(i))
        return false;
    
    return true;
  }
  
  /**
   * Returns true if the character array ends with the suffix.
   * 
   * @param s Input Buffer
   * @param len length of input buffer
   * @param suffix Suffix string to test
   * @return true if <code>s</code> ends with <code>suffix</code>
   */
  public static boolean endsWith(char s[], int len, char suffix[]) {
    final int suffixLen = suffix.length;
    if (suffixLen > len)
      return false;
    for (int i = suffixLen - 1; i >= 0; i--)
      if (s[len -(suffixLen - i)] != suffix[i])
        return false;
    
    return true;
  }
  
  /**
   * Delete a character in-place
   * 
   * @param s Input Buffer
   * @param pos Position of character to delete
   * @param len length of input buffer
   * @return length of input buffer after deletion
   */
  public static int delete(char s[], int pos, int len) {
    assert pos < len;
    if (pos < len - 1) { // don't arraycopy if asked to delete last character
      System.arraycopy(s, pos + 1, s, pos, len - pos - 1);
    }
    return len - 1;
  }
  
  /**
   * Delete n characters in-place
   * 
   * @param s Input Buffer
   * @param pos Position of character to delete
   * @param len Length of input buffer
   * @param nChars number of characters to delete
   * @return length of input buffer after deletion
   */
  public static int deleteN(char s[], int pos, int len, int nChars) {
    assert pos + nChars <= len;
    if (pos + nChars < len) { // don't arraycopy if asked to delete the last characters
      System.arraycopy(s, pos + nChars, s, pos, len - pos - nChars);
    }
    return len - nChars;
  }
}
