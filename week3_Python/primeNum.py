# Write a function is_prim(n) that returns True if n is a prime number; otherwise, return False.
def is_prim(n):
    if n < 2:           # 0 and 1 are not prime
        return False
    for i in range(2, int(n**0.5) + 1):  # check divisors up to sqrt(n)
        if n % i == 0:  # if n is divisible by i
            return False
    return True          # no divisors found → n is prime
