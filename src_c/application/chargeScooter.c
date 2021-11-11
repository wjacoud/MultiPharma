#include "chargeScooter.h"
#include "writeEstimateFile.h"
#include "estimateTimeToFullCharge.h"

static float estimateTime(int capacity, int current);

void chargeScooter(dock *dok){

    /* 1. Build filename */
    char filename[MAX_SIZE_FILE_NAME];
    snprintf(filename, MAX_SIZE_FILE_NAME, "%s%s.data", FILE_ESTIMATE_BASE_PATH_NAME, dok->timestamp);
    /* 2. Create file .data */
    FILE *fp;
    if((fp = createFile(filename)) == NULL)
        return;

    /* 3. Estimate time to full charge */
    float estimate = estimateTime(dok->max_capacity, dok->current);

    /* 3.2. Adjust to current percentafe of charge */
    float per =  (float) ((100- dok->charge_percentage)/100);
    per = (float) (100 - dok->charge_percentage);
    per = per/100;
    estimate = (float) estimate*per;
    dok->estimate = estimate;

    /* 4. Write file content */
    fprintf(fp, "%s\n%d;%d;%d;%f\n", ESTIMATE_FILE_HEADER, 
                                    dok->slot_id, 
                                    dok->scooter_id, 
                                    dok->courier_id,
                                    dok->estimate);

    /* 5. Close file .data */
    closeFile(fp);

    /* 6. Build filename */
    snprintf(filename, MAX_SIZE_FILE_NAME, "%s%s.data.flag", FILE_ESTIMATE_BASE_PATH_NAME, dok->timestamp);

    /* 7. Create file .data.flag */
    if((fp = createFile(filename)) == NULL)
        return;

    /* 8. Close file .data.flag */
    closeFile(fp);

    /* 9. Free struct */
    free(dok->timestamp);
    free(dok);
}

static float estimateTime(int capacity, int current){
    long long int estimate = estimateTimeToFullCharge(capacity, current);
    
    /* 3.1.  Get integer part and decimal part of estimate*/
    long int *temp = (long int*) &estimate;
	int real = *(temp + 0);
	int decimal = *(temp + 1);
    return (float) real + (float) decimal/current;
}
