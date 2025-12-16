import axios from "axios";

const API_BASE_URL = "/api";

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// Intercepteur pour ajouter le token JWT à chaque requête
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Intercepteur pour gérer les erreurs d'authentification
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Token invalide ou expiré
      localStorage.removeItem("token");
      localStorage.removeItem("username");
      localStorage.removeItem("email");
      localStorage.removeItem("roles");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

// Film Service
const extractIdFromHref = (href) => {
  if (!href) return undefined;
  const m = String(href).match(/\/films\/(\d+)(?:\?.*)?$/);
  return m ? Number(m[1]) : undefined;
};

export const filmService = {
  getAllFilms: async () => {
    try {
      const response = await api.get("/films");

      let films = [];
      if (response.data?._embedded?.films) films = response.data._embedded.films;
      else if (Array.isArray(response.data)) films = response.data;

      return films.map((f) => {
        const id =
            f?.id ??
            f?.filmId ??
            extractIdFromHref(f?._links?.self?.href);

        return { ...f, id };
      });
    } catch (error) {
      console.error("Error fetching films:", error);
      throw error;
    }
  },

  getFilmById: async (id) => {
    try {
      const response = await api.get(`/films/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching film ${id}:`, error);
      throw error;
    }
  },
};

// Reservation Service
export const reservationService = {
  createReservation: async (reservation) => {
    const response = await api.post("/reservations", reservation);
    return response.data;
  },

  getAllReservations: async () => {
    const response = await api.get("/reservations");
    return response.data;
  },

  getReservationsByFilmId: async (filmId) => {
    const response = await api.get(`/reservations/film/${filmId}`);
    return response.data;
  },
};

// Payment Service
export const paymentService = {
  processPayment: async (paymentRequest) => {
    const response = await api.post("/payments", paymentRequest);
    return response.data;
  },
};

export default api;