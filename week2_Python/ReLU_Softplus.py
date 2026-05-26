import numpy as np
import matplotlib.pyplot as plt

# Define ReLU and Softplus functions
def relu(x):
    return np.maximum(0, x)

def softplus(x):
    return np.log1p(np.exp(x))  # log(1 + exp(x))

# Generate input values
x = np.linspace(-10, 10, 400)

# Compute function values
y_relu = relu(x)
y_softplus = softplus(x)

# Plot both functions
plt.plot(x, y_relu, label='ReLU', color='blue')
plt.plot(x, y_softplus, label='Softplus', color='orange')
plt.title('ReLU vs Softplus')
plt.xlabel('x')
plt.ylabel('f(x)')
plt.legend()
plt.grid(True)
plt.show()
