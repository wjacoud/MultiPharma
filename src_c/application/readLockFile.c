#include "readLockFile.h"

/**
 * Opens file and returns 1 if successful, saving the file pointer in the global variable fptr.
 * Otherwise returns 0.
 */ 
FILE* openFile(char* filePath) {
    FILE *fp;
    if ((fp = fopen(filePath, "r")) == NULL) {
        perror("Error: Problem opening file!\n");
    }
    return fp;
}

/**
 * Reads a line from a lock.[datetime].data, and returns pointer to chars with the contents read, if successful.
 * Otherwise returns NULL.
 */
char* readLine(FILE *fp){
    
    char *buffer;
    size_t bufsize = 50;
    size_t n_characters;

    buffer = (char *)malloc(bufsize * sizeof(char));
    if( buffer == NULL){
        perror("Error: Unable to allocate buffer");
        return NULL;
    }
    
    n_characters = getline(&buffer, &bufsize, fp);

    // Processes line to take the '\n' character
    char* processedLine = malloc(n_characters);
    int i;
    for (i = 0; i < n_characters-1; i++) {  // Need to adjust n_characters: why does the getLine finction adds two more characters to the end of the line?
        if (*(buffer + i) != '\n') {
            *(processedLine + i) = *(buffer + i);
            if(i == n_characters -1){       // the same here: Need to adjust n_character
                *(processedLine + n_characters) = '\0';
            }
        }else{
            *(processedLine + i) = '\0';
            break;
        }
    }
    return processedLine;
}

/**
 * Checks if the file has ended. Returns non-zero value if has ended; 0 if not.
 */ 
int isEndOfFile(FILE *fp) {
    return feof(fp);
}

/**
 * Closes file.
 */
void closeFile(FILE *fp) {
    fclose(fp);
}

/**
 * Deletes file from directory.
 * 
 * Returns 1 if deleted successfuly, returns 0 otherwise.
 */ 
int removeFile(char* file) {
    if (remove(file) != 0) {
        printf("Error: unable to delete file\n");
        return 0;
    }
    return 1;
}

char* getTimestampFromFilename(int s_idx, char *filename){
    char *timestamp = (char *)malloc(MAX_SIZE_FILE_NAME);
    int k=0;
    while(*(filename + s_idx) != '.'){
        //lock_timestamp[k] = *(filename + s_idx);
        *(timestamp + k) = *(filename + s_idx);
        k++;
        s_idx++;
    }
    *(timestamp + k) = '\0';
    return timestamp;
}
