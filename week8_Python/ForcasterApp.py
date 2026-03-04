import streamlit as st
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression

# 1. Reliable Data Loading
DATA_URL = "50_Startups.csv"

@st.cache_data
def load_data():
    return pd.read_csv(DATA_URL)

df = load_data()
X = df[['R&D Spend', 'Administration', 'Marketing Spend']]
y = df['Profit']
model = LinearRegression().fit(X, y)

# 2. Interactive UI Setup
st.set_page_config(page_title="Startup AI Advisor", layout="wide")
st.title("🚀 Startup Profit Advisor")

# Initialize Chat History (Like in your shared example)
if "messages" not in st.session_state:
    st.session_state.messages = []

# Layout: Left for Tools, Right for Chat
col_tools, col_chat = st.columns([1, 1])

with col_tools:
    st.header("Financial Tools")
    tab1, tab2 = st.tabs(["Budget Sliders", "Correlation Map"])
    
    with tab1:
        rd = st.slider("R&D Spend ($)", 0, 200000, 75000)
        admin = st.slider("Administration ($)", 0, 200000, 110000)
        market = st.slider("Marketing ($)", 0, 500000, 150000)
        prediction = model.predict([[rd, admin, market]])[0]
        st.metric("Predicted Profit", f"${prediction:,.2f}")
        
    with tab2:
        fig, ax = plt.subplots()
        sns.heatmap(df.corr(numeric_only=True), annot=True, cmap='RdYlGn', ax=ax)
        st.pyplot(fig)

with col_chat:
    st.header("Chat with Advisor")
    
    # Display Chat History [cite: 6]
    for message in st.session_state.messages:
        with st.chat_message(message["role"]):
            st.markdown(message["content"])

    # Chat Input [cite: 6]
    if prompt := st.chat_input("Ask me about your budget (e.g., 'What if I spend 100k on R&D?')"):
        st.session_state.messages.append({"role": "user", "content": prompt})
        with st.chat_message("user"):
            st.markdown(prompt)

        # Basic "AI" logic using your Model
        if "r&d" in prompt.lower() or "spend" in prompt.lower():
            response = f"Based on the data, if you maintain that budget, your estimated profit is ${prediction:,.2f}. Remember, R&D has the highest impact!"
        else:
            response = "I'm your financial assistant. Use the sliders to adjust your budget, and I'll update the forecast!"

        st.session_state.messages.append({"role": "assistant", "content": response})
        with st.chat_message("assistant"):
            st.markdown(response)