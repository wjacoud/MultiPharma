#include "dock.h"
#include "../main/header.h"

dock* newDock(unsigned int slot_id, unsigned int scooter_id, unsigned int courier_id, unsigned int max_capacity, unsigned int charge_percentage, unsigned int current, char* timestamp){
    
    dock* d = (dock *)malloc(SIZE_STRUCT_DOCK);
    int str_size = strlen(timestamp) + 1;
    d->timestamp = (char *)malloc(str_size);

    d->slot_id = slot_id;
    d->scooter_id = scooter_id;
    d->courier_id = courier_id;
    d->max_capacity = max_capacity;
    d->charge_percentage = charge_percentage;
    d->current = current;
    strncpy(d->timestamp, timestamp, str_size);
    d->estimate = 0.0;

    return d;
}

dock* newDockWithNoCharge(unsigned int slot_id, unsigned int scooter_id, unsigned int courier_id, char* timestamp) {
    
    return newDock(
        slot_id,
        scooter_id,
        courier_id,
        0,
        0,  // charge_percentage
        0,  // current
        timestamp
    );
}
