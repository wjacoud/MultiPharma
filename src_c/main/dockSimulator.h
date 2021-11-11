#ifndef DOCK_SIMULATOR_H
#define DOCK_SIMULATOR_H

#include "header.h"
#include <unistd.h>
#include <dirent.h>

#define FILENAME_PATTERN_FLAG "^lock_20[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}.data.flag$"
#define FILES_LOCK_DIR_PATH "../files_lock"
#define LOCK_FILE_BASE_PATH_NAME "../files_lock/lock_"
#define LOCK_FILE_MAX_PATH_SIZE 100
#define SECONDS_BETWEEN_READS 5

void dockingSimulatorWorking(void);

/**
 * Time (in seconds) to wait in between readings of the directory with the lock.[datetime].data.flag files
 */ 
//unsigned int secondsBetweenReads;

#endif
