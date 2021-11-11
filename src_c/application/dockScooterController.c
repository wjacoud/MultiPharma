#include "dockScooter.h"
#include "chargeScooter.h"

void dockScooterController(char *filePath, char *fileFlagPath){

    dock *dok = dockScooter(filePath, fileFlagPath);

    if(dok != NULL){
        if (startCharging==1){
            chargeScooter(dok);
        }
    } 
}