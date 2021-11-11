#include <string.h>
#include "../application/dockScooter.h"
#include "../application/dockScooterController.h"
#include "../application/readLockFile.h"
#include "dockSimulator.h"

void dockingSimulatorWorking(void) {

    printMsg("DOCK SIMULATOR is now running...");

    while(1){

        char path[LOCK_FILE_MAX_PATH_SIZE];

        /* 1. Read files from folder
        ------------------------------------------------------------------------- */
        struct dirent *de;  // Pointer for directory entry 
  
        // opendir() returns a pointer of DIR type.
        DIR *dr = opendir("../files_lock"); 
    
        if (dr == NULL)  // opendir returns NULL if couldn't open directory 
            printf("Error: Could not open current directory" );

        while ((de = readdir(dr)) != NULL){
            
            /* 1.1. Check whether the file name matches expected pattern (looks for flag)
            ------------------------------------------------------------------------- */
            if (match(de->d_name, FILENAME_PATTERN_FLAG) == 1){

                printf("** Process parking ** FILE: %s\n", de->d_name);

                /* 1.1.1. Build .flag full path name */
                char pathFLAG[LOCK_FILE_MAX_PATH_SIZE];
                snprintf(pathFLAG, LOCK_FILE_MAX_PATH_SIZE, "%s/%s", FILES_LOCK_DIR_PATH, de->d_name);
                //printf(".flag path: %s\n", pathFLAG);

                /* 1.1.2. Build .data full path name */
                char src[LOCK_FILE_MAX_PATH_SIZE];
                int last_idx = strlen(de->d_name)-strlen(".flag");
                strncpy(src, de->d_name, last_idx);
                src[last_idx] = '\0';
                snprintf(path, LOCK_FILE_MAX_PATH_SIZE, "%s/%s", FILES_LOCK_DIR_PATH, src);
                
                /* 1.1.3. Check if the lock.[datetime].data file exists */
                if( access( path, F_OK ) != 0 ) {
                    printf("Error: Missing file %s\n", src);
                    continue;
                }
                /* 1.1.4. Call controller on this file */
               dockScooterController(path, pathFLAG);
            }
        }
        closedir(dr);
        sleep(SECONDS_BETWEEN_READS);
        printf("waiting...\n\n");
    }
}
