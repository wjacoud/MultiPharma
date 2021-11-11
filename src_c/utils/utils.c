#include "utils.h"
/*
 * Match string against the extended regular expression in
 * pattern, treating errors as no match.
 *
 * Return 1 for match, 0 for no match.
 */

int match(char *string, const char *pattern){
    int    status;
    regex_t    re;
    if (regcomp(&re, pattern, REG_EXTENDED|REG_NOSUB) != 0) {
        return 0;      /* Report error. */
    }
    status = regexec(&re, string, (size_t) 0, NULL, 0);
    regfree(&re);
    
    if (status != 0) {
        return 0;      /* Report error. */
    }
    return 1;
}

/**
 * Compare two Strings.
 * 
 * Returns 1 if they are equal, returns 0 otherwise.
 */
int compare_string(char *first, char *second) {
    int i = 0;
    while (*(first+i) == *(second+i)) {
        if (*(first+i) == '\0' || *(second+i) == '\0')
            break;
        i++;
    }
    if (*(first+i) == '\0' && *(second+i)  == '\0')
        return 1;
    else
        return 0;
}

/**
 * Converts a CSV line to an array on integers.
 * 
 * Returns the array, if successful, returns NULL otherwise.
 */
int* lineToArrayOfInts(char *line) {
    int *tmp = malloc(NR_COLUMNS*sizeof(int));
    char delim[] = ";";
    char *ptr = strtok(line, delim);

    int i=0;
    while(ptr != NULL){
        int tmp_int = atoi(ptr);
        *(tmp+i) = tmp_int;
        i++;
		ptr = strtok(NULL, delim);
	}
    *(tmp + i) = '\0';
    return tmp;
}

void printMsg(char *msg){
    int i;
    for(i=0; i <strlen(msg)+6; i++){
        printf("-");
    }printf("\n");
    printf("-  %s  -\n", msg);
    for(i=0; i <strlen(msg)+6; i++){
        printf("-");
    }printf("\n\n");
}