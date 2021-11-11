# A Makefile with variables and suffix rules

# Variables
INCLUDES = header.h dockSimulator.h ../application/dockScooter.h ../application/dockScooterController.h ../application/readLockFile.h ../utils/utils.h ../application/writeEstimateFile.h ../application/chargeScooter.h ../domain/dock.h ../application/estimateTimeToFullCharge.h
SOURCES = mainClass.c dockSimulator.c ../application/dockScooter.c ../application/dockScooterController.c ../application/readLockFile.c ../utils/utils.c ../application/writeEstimateFile.c ../application/chargeScooter.c ../domain/dock.c ../application/estimateTimeToFullCharge.s
OBJFILES = mainClass.o dockSimulator.o dockScooter.o dockScooterController.o readLockFile.o utils.o writeEstimateFile.o chargeScooter.o dock.o estimateTimeToFullCharge.o
OBJFILESS = mainClass.o dockSimulator.o ../application/dockScooter.o ../application/dockScooterController.o ../application/readLockFile.o ../utils/utils.o ../application/writeEstimateFile.o ../application/chargeScooter.o ../domain/dock.o ../application/estimateTimeToFullCharge.o
EXEC = dockSimulatorProg

# Suffix rules
.SUFFIXES: .c .o

# How to build an object .o from a code file .c ; $<--- file name
.c.o:
	gcc -Wall -g -c $<
.s.o:
	gcc -Wall -g -c $<

${EXEC}: ${OBJFILES} ${OBJFILESS}
	gcc -Wall -g -o ${EXEC} ${OBJFILES}
	
${OBJFILES}: ${SOURCES} ${INCLUDES}

run: ${EXEC}
	./${EXEC} 5 120
	
clean:
	rm -f ${OBJFILES} ${EXEC}

# Debug
debug: ${EXEC}
	gdb -tui ${EXEC}

