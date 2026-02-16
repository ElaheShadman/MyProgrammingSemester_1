import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np
from traitlets import This

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



#Hypothesis 1: The "Swimming Efficiency" Correlation
#Question: Does a larger body mass always require significantly longer flippers across all species, or do some species have a more "efficient" ratio?

#Hypothesis: There is a strong positive linear correlation between body_mass_g and flipper_length_mm, but the slope of this relationship differs by species.

#How to Prove/Disprove: * Use sns.lmplot(data=df, x='body_mass_g', y='flipper_length_mm', hue='species').

#Insight: If the lines are parallel, the relationship is universal. If one species (like Gentoos) has a steeper line, they gain more flipper length per gram of mass than others.

#Hypothesis 2: Bill Shape Specialization (Niche Partitioning)
#Question: Do species living on the same island evolve different bill shapes to avoid competing for the same food?

#Hypothesis: On islands where multiple species coexist (e.g., Dream or Biscoe), the distribution of bill_length_mm vs. bill_depth_mm will show zero overlap, indicating specialized feeding habits.

#How to Prove/Disprove: * Filter the dataframe for a specific island: dream_island = df[df['island'] == 'Dream'].

#Create a scatter plot: sns.scatterplot(data=dream_island, x='bill_length_mm', y='bill_depth_mm', hue='species').

#Insight: Overlap suggests competition; distinct clusters suggest "niche partitioning" (eating different sizes of krill/fish).

#Hypothesis 3: The "Gentoo Gigantism" Theory
#Question: Are Gentoo penguins significantly larger than all other species regardless of their sex?

#Hypothesis: The smallest male Gentoo penguin is still larger in body_mass_g than the largest individuals of the Adelie and Chinstrap species.

#How to Prove/Disprove: * Use a violin plot or a boxplot with a swarm overlay: sns.boxplot(data=df, x='species', y='body_mass_g').

#Check the "max" of Adelie/Chinstrap vs. the "min" of Gentoo.

#Insight: This helps stakeholders understand if "Species" is a stronger predictor of weight than "Sex."


sns.lmplot(
    data=df,
    x='body_mass_g',
    y='flipper_length_mm',
    hue='species',
    height=6,
    aspect=1.2,
    markers=["o", "s", "D"]
)

plt.title("Body Mass vs Flipper Length by Species")
plt.show()




# Filter for a specific island (Dream, Biscoe, or Torgersen)
dream_island = df[df['island'] == 'Dream']

sns.scatterplot(
    data=dream_island,
    x='bill_length_mm',
    y='bill_depth_mm',
    hue='species',
    s=80
)

plt.title("Bill Length vs Bill Depth on Dream Island")
plt.show()





#if you want to tes each iland you can loop:



for island in df['island'].unique():
    island_data = df[df['island'] == island]
    sns.scatterplot(
        data=island_data,
        x='bill_length_mm',
        y='bill_depth_mm',
        hue='species',
        s=80
    )
    plt.title(f"Bill Length vs Bill Depth on {island} Island")
    plt.show()





    sns.boxplot(
    data=df,
    x='species',
    y='body_mass_g'
)

sns.swarmplot(
    data=df,
    x='species',
    y='body_mass_g',
    color='black',
    size=3
)

plt.title("Body Mass Distribution by Species")
plt.show()


df.groupby('species')['body_mass_g'].agg(['min', 'max'])




