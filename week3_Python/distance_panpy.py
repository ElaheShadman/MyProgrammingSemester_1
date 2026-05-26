import pandas as pd
import numpy as np

# Step 1: Create a small dataset
cities = pd.DataFrame({
    "City": ["A", "B", "C"],
    "Latitude": [10, 20, 30],
    "Longitude": [100, 110, 120]
})

# Step 2: Define a simple distance function (Haversine)
def haversine(lat1, lon1, lat2, lon2):
    R = 6371  # Earth radius in km
    dlat = np.radians(lat2 - lat1)
    dlon = np.radians(lon2 - lon1)
    a = np.sin(dlat/2)**2 + np.cos(np.radians(lat1)) * np.cos(np.radians(lat2)) * np.sin(dlon/2)**2
    return R * 2 * np.arctan2(np.sqrt(a), np.sqrt(1 - a))

# Step 3: Calculate pairwise distances
n = len(cities)
dist = np.zeros((n, n))

for i in range(n):
    for j in range(n):
        dist[i, j] = haversine(cities.loc[i, "Latitude"], cities.loc[i, "Longitude"],
                               cities.loc[j, "Latitude"], cities.loc[j, "Longitude"])

# Step 4: Store in a new DataFrame
distance_df = pd.DataFrame(dist, index=cities["City"], columns=cities["City"])
print(distance_df)
