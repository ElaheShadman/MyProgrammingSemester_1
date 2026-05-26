def vector_maximum(vector):
  # Write your function here
  max_in_vector = vector[0]
  for element in vector:
    if element > max_in_vector:
       max_in_vector = element
  return max_in_vector
