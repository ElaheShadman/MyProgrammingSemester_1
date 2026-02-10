import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

# 1. LOAD DATA + BASIC EDA

df = pd.read_csv("penguins.csv")

# Shape
print("Shape of dataset:")
print(df.shape)

# Data types + missing values
print("\nInfo:")
print(df.info())

# Summary statistics
print("\nSummary statistics:")
print(df.describe())

# Missing values
print("\nMissing values:")
print(df.isnull().sum())


# 2. DATA CLEANING

# Drop rows missing key measurements
df_clean = df.dropna(subset=[
    'bill_length_mm',
    'flipper_length_mm',
    'body_mass_g'
])

# Fill missing categorical values
df_clean['sex'] = df_clean['sex'].fillna('Unknown')

# Check cleaning
print("\nMissing values after cleaning:")
print(df_clean.isnull().sum())

print("\nSpecies count:")
print(df_clean['species'].value_counts())


# 3. VISUALIZATIONS (2x2 GRID)

fig, ax = plt.subplots(2, 2, figsize=(12, 10))

# A) Histogram
sns.histplot(
    data=df_clean,
    x='bill_length_mm',
    hue='species',
    kde=True,
    ax=ax[0, 0]
)
ax[0, 0].set_title("Bill Length by Species")

# B) Boxplot
sns.boxplot(
    data=df_clean,
    x='species',
    y='flipper_length_mm',
    ax=ax[0, 1]
)
ax[0, 1].set_title("Flipper Length by Species")

# C) Scatter plot
sns.scatterplot(
    data=df_clean,
    x='bill_length_mm',
    y='body_mass_g',
    hue='species',
    ax=ax[1, 0]
)
ax[1, 0].set_title("Bill Length vs Body Mass")

# D) Countplot
sns.countplot(
    data=df_clean,
    x='island',
    hue='species',
    ax=ax[1, 1]
)
ax[1, 1].set_title("Species by Island")
ax[1, 1].tick_params(axis='x', rotation=45)

plt.tight_layout()
plt.show()


# 4. INSIGHTS TABLE

insights = pd.DataFrame({
    "Observation": [
        "Gentoo penguins are the heaviest and have the longest bills",
        "Chinstrap penguins have medium flipper lengths",
        "Penguin species are linked to specific islands"
    ],
    "Implication": [
        "Gentoo are adapted for stronger swimming",
        "Chinstrap show moderate body size",
        "Geography affects species distribution"
    ]
})

print("\nInsights Table:")
print(insights)


# 5. BONUS: CORRELATION HEATMAP

corr = df_clean[
    ['bill_length_mm', 'bill_depth_mm',
     'flipper_length_mm', 'body_mass_g']
].corr()

plt.figure(figsize=(6, 5))
sns.heatmap(corr, annot=True, cmap='coolwarm')
plt.title("Feature Correlations")
plt.show()

# BONUS: Pairplot
sns.pairplot(
    df_clean,
    hue='species',
    vars=[
        'bill_length_mm',
        'bill_depth_mm',
        'flipper_length_mm',
        'body_mass_g'
    ]
)
plt.show()
