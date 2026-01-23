def euclidean_norm(vector):
  # Write your function here
  sum_squares = 0
  for item in vector:
    sum_squares += item ** 2
  return sum_squares ** 0.5
