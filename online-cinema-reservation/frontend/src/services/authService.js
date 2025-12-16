import axios from "axios";

const API_BASE_URL = "/api";

const authApi = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export const authService = {
  login: async (usernameOrEmail, password) => {
    try {
      const response = await authApi.post("/auth/login", {
        usernameOrEmail,
        password,
      });
      const { token, username, email, roles } = response.data;
      
      // Stocker le token dans localStorage
      localStorage.setItem("token", token);
      localStorage.setItem("username", username);
      localStorage.setItem("email", email);
      localStorage.setItem("roles", JSON.stringify(roles));
      
      return response.data;
    } catch (error) {
      console.error("Error logging in:", error);
      throw error;
    }
  },

  register: async (username, email, password) => {
    try {
      const response = await authApi.post("/auth/register", {
        username,
        email,
        password,
      });
      const { token, username: user, email: userEmail, roles } = response.data;
      
      // Stocker le token dans localStorage
      localStorage.setItem("token", token);
      localStorage.setItem("username", user);
      localStorage.setItem("email", userEmail);
      localStorage.setItem("roles", JSON.stringify(roles));
      
      return response.data;
    } catch (error) {
      console.error("Error registering:", error);
      throw error;
    }
  },

  logout: () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("email");
    localStorage.removeItem("roles");
  },

  getToken: () => {
    return localStorage.getItem("token");
  },

  isAuthenticated: () => {
    return !!localStorage.getItem("token");
  },

  getUsername: () => {
    return localStorage.getItem("username");
  },

  getEmail: () => {
    return localStorage.getItem("email");
  },
};

export default authService;
