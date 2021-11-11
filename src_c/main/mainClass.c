#include "dockSimulator.h"
#include "../domain/dock.h"

int main(int argc, char *argv[]) {

    /* Build Park */
    /*if(argc == 3){
        nr_slots = (unsigned int) atoi(argv[1]);
        park_current = (unsigned int) atoi(argv[2]);
        
        dock *park[nr_slots];

        //-- Build each of the parking slots
        int i;
        for(i=0; i<nr_slots; i++){
            *(park+i) = newDock(i+1,0,0,0,0,0,"");
        }

        for(i=0; i<nr_slots; i++){
            dock *dok = *(park+i);
            printf("slot id: %u\n", dok->slot_id);
        }
    }*/
    
    // Calls dock simulator algorithm    
    dockingSimulatorWorking();

    return 0;
}