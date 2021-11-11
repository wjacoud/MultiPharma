#ifndef CHARGE_SCOOTER_H
#define CHARGE_SCOOTER_H

#include "readLockFile.h"

#define ESTIMATE_FILE_HEADER "SLOT_ID;SCOOTER_ID;COURIER_ID;ESTIMATE"

void chargeScooter(dock * dok);

#endif