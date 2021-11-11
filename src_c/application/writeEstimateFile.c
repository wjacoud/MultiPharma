#include "writeEstimateFile.h"

/**
 * Creates file and returns 1 if successful, saving the file pointer in the global variable fptr.
 * Otherwise returns 0.
 */ 
FILE* createFile(char* filePath) {
    FILE *fp;
    if ((fp = fopen(filePath, "w")) == NULL) {
        printf("Error! Problem creating file\n");
    }
    return fp;
}

/**
 * Writes the content of the global Lock 'instance' to a file
 */ 
void writeEtimateFile(void){

}