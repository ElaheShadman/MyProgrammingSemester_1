import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

# ------------------------------------------------------------------------------
# LOAD DATA
# ------------------------------------------------------------------------------
path = "https://raw.githubusercontent.com/AyushDiyundi/AI-Scientific-Programming/main/KC_housing_data.csv"
df = pd.read_csv(path)

print("Current Columns in df:", df.columns.tolist())

# Check the date range
print(f"Data starts on: {df['date'].min()}")
print(f"Data ends on: {df['date'].max()}")

# Convert date column
df['date'] = pd.to_datetime(df['date'])
df['month'] = df['date'].dt.month

# Check monthly distribution
print(df['month'].value_counts().sort_index())

# Check for missing dates
print(f"Missing dates: {df['date'].isnull().sum()}")

# ------------------------------------------------------------------------------
# ROLE A: THE DATA WRANGLER
# ------------------------------------------------------------------------------
print("\n--- [Role A] Data Preparation ---")

# Ensure date formatting (already done above)
df['date'] = pd.to_datetime(df['date'])
df['month'] = df['date'].dt.month

# Filter: Basements Only
df_underground = df[df['sqft_basement'] > 0].copy()

# Filter: Remove extreme outliers
df_clean = df_underground[
    (df_underground['price'] < 2000000) &
    (df_underground['bedrooms'] < 10)
].copy()

print(f"Data Ready. Properties with basements: {len(df_clean)}")

# ------------------------------------------------------------------------------
# ROLE B: THE SCOUT
# ------------------------------------------------------------------------------
print("\n--- [Role B] Location Scouting ---")

# Filter for "Low Profile" homes
potential_buys = df_clean[
    (df_clean['condition'] <= 3) &      # average condition
    (df_clean['sqft_lot'] > 8000) &     # privacy
    (df_clean['waterfront'] == 0) &     # no waterfront
    (df_clean['view'] == 0)             # no special view
].copy()

# Find top zip codes
top_zips = potential_buys['statezip'].value_counts().head(5)
target_zip = top_zips.idxmax()

# Homes in the best zip code
target_homes = potential_buys[potential_buys['statezip'] == target_zip].copy()

print(f"Target Cluster Identified: {target_zip}")

# Visual: Bar chart of top zip codes
plt.figure(figsize=(10, 6))
top_zips.plot(kind='bar', color='skyblue')
plt.title(f'Top Areas for "Low Profile" Strategy (Winner: {target_zip})')
plt.xlabel('State Zip Code')
plt.ylabel('Number of Properties')
plt.xticks(rotation=45)
plt.show()

# ------------------------------------------------------------------------------
# ROLE C: THE STRATEGIST
# ------------------------------------------------------------------------------
print("\n--- [Role C] Timing Analysis ---")

# Monthly trends in the target area
monthly_stats = target_homes.groupby('month')['price'].agg(['count', 'mean'])

# Visual: Buying window
plt.figure(figsize=(10, 5))
sns.lineplot(
    data=monthly_stats,
    x=monthly_stats.index,
    y='count',
    marker='o',
    color='green',
    linewidth=2.5
)
plt.title(f'Inventory Volume by Month (Area: {target_zip})')
plt.xlabel('Month (1=Jan, 12=Dec)')
plt.ylabel('Available Homes')
plt.axvspan(1, 2, color='yellow', alpha=0.3, label='Recommended Buy Window')
plt.grid(True, alpha=0.3)
plt.legend()
plt.show()

# ------------------------------------------------------------------------------
# ROLE D: THE CLOSER
# ------------------------------------------------------------------------------
print("\n--- [Role D] Final Recommendation ---")

# Sort by basement size (utility)
final_picks = target_homes.sort_values(by='sqft_basement', ascending=False).head(5)

# Columns to display
cols_to_show = ['street', 'city', 'statezip', 'price', 'sqft_basement', 'condition']

print("RECOMMENDED ACQUISITION LIST:")
if not final_picks.empty:
    print(final_picks[cols_to_show])
else:
    print("No properties found matching all criteria.")
