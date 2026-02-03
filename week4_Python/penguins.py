import pandas as pd
df = pd.read_csv('penguins.csv')
print(df.head())

print("Shape:", df.shape)          # number of rows & columns
print("\nInfo:")
print(df.info())                   # data types + missing values
print("\nMissing values:")
print(df.isnull().sum())           # count missing values
print("\nSummary statistics:")
print(df.describe())               # numeric summary


# Drop rows missing important numeric values
df_clean = df.dropna(subset=["bill_length_mm", "flipper_length_mm"])

# Fill missing sex values
df_clean["sex"] = df_clean["sex"].fillna("Unknown")

# Check again
print(df_clean.isnull().sum())