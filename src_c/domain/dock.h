#ifndef DOCK_H
#define DOCK_H

#define IDX_SLOT_ID 0
#define IDX_SCOOTER_ID 1
#define IDX_COURIER_ID 2
#define IDX_CAPACITY 3
#define IDX_CHARGE 4
#define IDX_CURRENT 5
#define IDX_ESTIMATE 6
#define IDX_TIMESTAMP 7

typedef struct {
	unsigned int slot_id;
	unsigned int scooter_id;
	unsigned int courier_id;
	unsigned int max_capacity;
	unsigned int charge_percentage;
	unsigned int current;
    float estimate;
	char *timestamp;
} dock;

#define SIZE_STRUCT_DOCK sizeof(dock)

dock* newDock(unsigned int slot_id, unsigned int scooter_id, unsigned int courier_id, unsigned int max_capacity, unsigned int charge_percentage, unsigned int current, char* timestamp);
dock* newDockWithNoCharge(unsigned int slot_id, unsigned int scooter_id, unsigned int courier_id, char* timestamp);

#endif