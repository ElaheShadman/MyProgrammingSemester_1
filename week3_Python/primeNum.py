# Write a function is_prim(n) that returns True if n is a prime number; otherwise, return False.
def is_prim(n):
    if n < 2:           # 0 and 1 are not prime
        return False
    for i in range(2, int(n**0.5) + 1):  # check divisors up to sqrt(n)
        if n % i == 0:  # if n is divisible by i
            return False
    return True          # no divisors found → n is prime

# Step 0: Pick a number

Let’s check n = 29 (a prime number).

Step 1: Check if n < 2
if n < 2:
    return False


n = 29 → not less than 2 → continue

Step 2: Calculate loop range
for i in range(2, int(n**0.5) + 1):


n**0.5 = √29 ≈ 5.385

int(√29) + 1 = 5 + 1 = 6

So the loop will check i = 2, 3, 4, 5

Step 3: Loop iteration table
i	n % i	n divisible by i?	Action
2	29 % 2 = 1	No	Continue to next i
3	29 % 3 = 2	No	Continue to next i
4	29 % 4 = 1	No	Continue to next i
5	29 % 5 = 4	No	Continue to next i

None of these divide 29 evenly → loop finishes
