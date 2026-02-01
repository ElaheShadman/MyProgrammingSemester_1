# Write a function mitternacht(a, b, c) that takes three integers (representing the coefficients of a quadratic formula) and returns the sum of all real solutions, converted to an integer. If there is no solution, return 0. It is given that a != 0 .
def mitternacht(a, b, c):
    d = b*b - 4*a*c
    if d < 0:
        return 0
    return int(-b / a)


# The task is about the quadratic equation:

ax² + bx + c = 0

The function mitternacht(a, b, c) gets the three coefficients of this equation.

You must:

Find the real solutions of the quadratic equation.

Add all real solutions together.

Convert the result to an integer.

If there are no real solutions, return 0.

Important math fact

For a quadratic equation:

ax² + bx + c = 0

The solutions are:

x = (-b ± √(b² - 4ac)) / (2a)

The term:

b² - 4ac

is called the discriminant.

If discriminant < 0 → no real solutions

If discriminant ≥ 0 → real solutions exist

Key observation

If solutions exist:

x1 = (-b + √d) / (2a)
x2 = (-b - √d) / (2a)

The sum of the two solutions is always:

x1 + x2 = -b / a

So we do NOT need to calculate square roots at all.

Therefore the logic is:

If b² - 4ac < 0 → return 0

Else → return int(-b / a)
