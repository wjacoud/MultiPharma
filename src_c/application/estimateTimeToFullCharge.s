# .section .bss
#	.comm	ptrvec, 4	

.section .data

.section .text
	.global estimateTimeToFullCharge	 	# long long int estimateTimeToFullCharge(int capacity, int current);

# -----------#
# FUNCTION 1
# ---------- #
estimateTimeToFullCharge:
# prologue
	pushl %ebp 		# save previous stack frame pointer
	movl %esp, %ebp # the stack frame pointer for sum function
	
# ------------------- CALLEE-SAVE ------------------- #
#	pushl %ebx
#	pushl %esi
#	pushl %edi
# --------------------------------------------------- #

# body of the function
	movl $0, %edx	    # clear edx

	movl 8(%ebp), %eax		# access 1st parameter	-> eax = capacity
    cdq
 	movl 12(%ebp), %ecx	    # access 2nd parameter  -> ecx = current
    idivl %ecx              # return eax:edx

end:
# ------------------ CALLEE-RESTORE ----------------- #
#	popl %edi
#	popl %esi
#	popl %ebx
# --------------------------------------------------- #

# epilogue
	movl %ebp, %esp # restore the previous stack pointer ("clear" the stack)
	popl %ebp 		# restore the previous stack frame pointer

# return from the funciton	
	ret
