#ifndef HEADER_H
#define HEADER_H

// Common 'includes' used throughout the application
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../domain/dock.h"

#define NR_COLUMNS 6

/**
 * ID of the scooter whos parking is being simulated
 */ 
unsigned int scooterID;

/**
 * ID of the courier that has parked the scooter
 */ 
unsigned int courierID;

/**
 * ID of the courier that has parked the scooter
 */ 
unsigned int nr_slots;

/**
 * ID of the courier that has parked the scooter
 */ 
unsigned int park_current;

#endif