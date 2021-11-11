#ifndef READ_LOCK_FILE_H
#define READ_LOCK_FILE_H

    #include <regex.h>
    #include "../main/header.h"
    #include "../utils/utils.h"

    #define MAX_SIZE_FILE_NAME 100
    #define MAX_SIZE_TIMESTAMP 30

    /**
     * Timestamp of the lock_[datetime].data file
     */  
    char lock_timestamp[MAX_SIZE_TIMESTAMP];

    /**
     * Opens file and returns 1 if successful, saving the file pointer in the global variable fptr.
     * Otherwise returns 0.
     */ 
    FILE* openFile(char* filePath);

    /**
     * Reads a line from a lock.[datetime].data, and returns pointer to chars with the contents read, if successful.
     * Otherwise returns NULL.
     */
    char* readLine(FILE *fp);

    /**
     * Checks if the file has ended. Returns non-zero value if has ended; 0 if not.
     */ 
    int isEndOfFile(FILE *fp);

    /**
     * Closes file.
     */
    void closeFile(FILE *fp);

    /**
     * Deletes file from directory.
     * 
     * Returns 1 if deleted successfuly, returns 0 otherwise.
     */ 
    int removeFile(char* file);

    char* getTimestampFromFilename(int s_idx, char *filename);

#endif