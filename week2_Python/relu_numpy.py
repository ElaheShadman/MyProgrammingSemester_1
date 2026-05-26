import numpy as np
def relu_numpy(x): 
  return np.maximum(0, x)

relu_numpy(np.array([1, -3, 2.5]))
