# Write a recursive function fac(n) that returns the factorial of a natural number.
def fac(n):
    if n == 0:
        return 1
    return n * fac(n - 1)
