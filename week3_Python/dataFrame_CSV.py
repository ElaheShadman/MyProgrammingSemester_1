# Exercise 2

import pandas as pd

path = "week3_Python\myData.csv"
df = pd.read_csv(path)
df.head()

northern_cities = df[df["latitude"] > 0]

average_latitude = northern_cities["latitude"].mean()

print(f"Average Latitude of Northern Cities: {average_latitude:.2f}")
