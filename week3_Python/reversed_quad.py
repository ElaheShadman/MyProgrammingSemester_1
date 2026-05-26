# Write a function reversed_quad that takes a list of numbers and returns them squared in reverse order.
def reversed_quad(lst):
    return [x * x for x in lst[::-1]]
