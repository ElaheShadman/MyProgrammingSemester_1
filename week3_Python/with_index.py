# Write a function with_index(l) that takes a list and returns a dictionary where the keys are the indices of the elements in the list, and the values are the corresponding list elements.

def with_index(l):
  d = {}
  for i in range (len(l)):
    d[i] = l[i]
  return d
