# Write a function mitternacht(a, b, c) that takes three integers (representing the coefficients of a quadratic formula) and returns the sum of all real solutions, converted to an integer. If there is no solution, return 0. It is given that a != 0 .
def mitternacht(a, b, c):
    d = b*b - 4*a*c
