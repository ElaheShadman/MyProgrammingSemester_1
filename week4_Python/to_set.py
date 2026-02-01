# Write a function to_set(n) that takes a list n , removes all elements from n that occur more than once, and then returns n . The order of the list should remain unchanged.For example, if the list [a, b, c, a, b] is given, the function should return the list [a, b, c] .
def to_set(n):
    r = []
    for i in n:
        if i not in r:
            r.append(i)
    return r
