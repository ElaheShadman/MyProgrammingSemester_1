# Write a function quersumme(n) that calculates the cross sum of a natural number.
def quersumme(n):
    total = 0
    while n > 0:
        total += n % 10
        n = n // 10
    return total

# Step by step:

n	  n % 10	total
4352    2	      2
435	    5	      7
43	    3	     10
4	    4	     14
0	    -	     14

Returns: 14
