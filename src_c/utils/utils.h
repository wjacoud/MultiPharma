#ifndef UTILS_H
#define UTILS_H

  #include <regex.h>
  #include "../main/header.h"

  /**
   * Glocal variable array pointer with the elements of a single line.
  */
  int tmp_array[NR_COLUMNS];
  /**
   * Compare two Strings.
   * 
   * Returns 1 if they are equal, returns 0 otherwise.
   */
  int compare_string(char *first, char *second);

  /**
   * Converts a CSV line to an array on integers.
   * 
   * Returns the array, if successful, returns NULL otherwise.
   */
  int* lineToArrayOfInts(char *line);
  /**
  * Match string against the extended regular expression in
  * pattern, treating errors as no match.
  *
  * Return 1 for match, 0 for no match.
  */
  int match(char *string, const char *pattern);

  void printMsg(char *msg);

#endif