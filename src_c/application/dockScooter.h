#ifndef DOCK_SCOOTER_H
#define DOCK_SCOOTER_H

#include "readLockFile.h"
#include "../domain/dock.h"

#define LOCK_FILE_HEADER "SLOT_ID;SCOOTER_ID;COURIER_ID;CAPACITY;CHARGE;CURRENT"
#define LINE_REGEX "^[0-9]+;[0-9]+;[0-9]+;[0-9]+;[0-9]+;[0-9]+$"
#define LINE_NO_CHARGE_REGEX "^[0-9]+;[0-9]+;[0-9]+$"
#define LINE_MAX_SIZE 100

#define ERROR_MSG_1 "Error: Unknown Lock file header"
#define ERROR_MSG_2 "Error: Unknown Lock file line format"
#define ERROR_MSG_3 "A Scooter was parked incorrectly"

/**
 * Global variable to serve as a flag to indicate whether or not the scooter is to be left to charge.
 * if set to 1 - charge scooter
 * if set to 0 - don't charge scooter
 */ 
int startCharging;

dock* dockScooter(char *filePath, char *fileFlagPath);


#endif