# Write a function quersumme(n) that calculates the cross sum of a natural number.
def quersumme(n):
    total = 0
    while n > 0:
        total += n % 10
        n = n // 10
    return total
