# Write a function ggT(a, b) that takes two natural numbers and returns their greatest common divisor (GCD).
def ggT(a, b):
  while b != 0:
        a, b = b, a % b
    return a


# it is a recursive implementation of the Euclidean algorithm for finding the greatest common divisor (GCD).

Step 1: Understand recursion

ggT calls itself until a condition is met.
Here, the condition is b == 0.

Step 2: Logic

if b == 0:

That means the second number is 0.

In this case, the GCD is a.

else:

If b is not 0, we call ggT again with the numbers (b, a % b)

% is the modulus operator, it gives the remainder of a divided by b.

Step 3: How it works (example)
ggT(48, 18)


Step 1: b != 0 → call ggT(18, 48 % 18) → ggT(18, 12)

Step 2: b != 0 → call ggT(12, 18 % 12) → ggT(12, 6)

Step 3: b != 0 → call ggT(6, 12 % 6) → ggT(6, 0)

Step 4: b == 0 → return 6 ✅

GCD of 48 and 18 is 6.
