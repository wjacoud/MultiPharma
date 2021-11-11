#ifndef WRITE_ESTIMATE_FILE_H
#define WRITE_ESTIMATE_FILE_H

#include "readLockFile.h"

#define FILE_ESTIMATE_BASE_PATH_NAME "../files_estimate/estimate_"

/**
 * Creates file and returns 1 if successful, saving the file pointer in the global variable fptr.
 * Otherwise returns 0.
 */ 
FILE* createFile(char* filePath);

/**
 * Writes the content of the global Lock 'instance' to a file
 */ 
void writeEtimateFile(void);

#endif