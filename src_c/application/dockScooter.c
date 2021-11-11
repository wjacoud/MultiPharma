#include <stdio.h>
#include "dockScooter.h"

static void printParkingSuccessMsg(unsigned int scooterId);

dock* dockScooter(char *filePath, char *fileFlagPath){

    /* 1. Open file lock */
    FILE* fp = openFile(filePath);

    if (fp == NULL)
        return NULL;

    /* Parking Place allways starts as having no charging capabilities */
    startCharging = 0;
    int *element_vec;
    dock *dok = NULL;
    
    /* 2. Read line from file */
    int line_nr = 1;
    while(!isEndOfFile(fp)){

        if (line_nr > 2)
            break;
        
        char *line = readLine(fp);
        char line_clone[LINE_MAX_SIZE];
        strcpy(line_clone, line);

        /* Read 1st line - header */
        if (line_nr == 1){
            if (compare_string(line, LOCK_FILE_HEADER) == 0){
                printf("Error: Unknown Header! \nExpected: %s\n", LOCK_FILE_HEADER);
                printMsg(ERROR_MSG_3);
                break;
            }
        }

        /* Read 2nd line - content */
        if (line_nr == 2){

            /* Set Timestamp to be used while creating the estimate file */
            char *timestamp = getTimestampFromFilename(strlen("../files_lock/lock_"), filePath);

            /* Process line */
            element_vec = lineToArrayOfInts(line_clone);
            
            /* A. Scooter parked with no charger */
            if (match(line, LINE_NO_CHARGE_REGEX) == 1){

                dok = newDockWithNoCharge(*(element_vec+IDX_SLOT_ID),
                                        *(element_vec+IDX_SCOOTER_ID),
                                        *(element_vec+IDX_COURIER_ID),
                                        timestamp);

                printParkingSuccessMsg(dok->scooter_id);
/*------------- Write to some file the parking success? --------------*/

                break;
            /* B. Scooter parked with charger*/
            }else if(match(line, LINE_REGEX) == 1){
                
                dok = newDock(*(element_vec+IDX_SLOT_ID),
                                *(element_vec+IDX_SCOOTER_ID),
                                *(element_vec+IDX_COURIER_ID),
                                *(element_vec+IDX_CAPACITY),
                                *(element_vec+IDX_CHARGE),
                                *(element_vec+IDX_CURRENT),
                                timestamp);

                printParkingSuccessMsg(dok->scooter_id);
                startCharging = 1;
                break;
            }else{
                /* If format invalid: incorrect parking */
                printf("%s\n", ERROR_MSG_2);
                printMsg(ERROR_MSG_3);
                break;
            }
        }
        line_nr++;
    }
    closeFile(fp);
    removeFile(filePath);
    removeFile(fileFlagPath);
    return dok;
}

static void printParkingSuccessMsg(unsigned int scooterId){
    char tmp[MAX_SIZE_FILE_NAME];
    sprintf(tmp, "Scooter id:%u, was parked successfuly!", scooterId);
    printMsg(tmp);
}