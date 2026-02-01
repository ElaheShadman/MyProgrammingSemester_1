# Write a function vorkommen(n) that takes a string and returns a dictionary. The dictionary should store each letter in the string as a key and the number of its occurrences within the string as the value. All letters should be interpreted as lowercase.
def vorkommen(n):
    n = n.lower()
    d = {}
    for c in n:
        if c in d:
            d[c] += 1
        else:
            d[c] = 1
    return d
