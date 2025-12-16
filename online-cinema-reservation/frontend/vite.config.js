import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/api/films": {
        target: "http://localhost:8888",
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/api\/films/, "/FILM-SERVICE/films"),
      },
      "/api/reservations": {
        target: "http://localhost:8888",
        changeOrigin: true,
        secure: false,
        // Remplace RESERVATION-SERVICE par le nom réel exposé sur 8888
        rewrite: (path) =>
          path.replace(
            /^\/api\/reservations/,
            "/RESERVATION-SERVICE/reservations"
          ),
      },
      "/api/payments": {
        target: "http://localhost:8888",
        changeOrigin: true,
        secure: false,
        // Remplace PAYMENT-SERVICE par le nom réel exposé sur 8888
        rewrite: (path) =>
          path.replace(/^\/api\/payments/, "/PAYMENT-SERVICE/payments"),
      },
      "/api/auth": {
        target: "http://localhost:8888",
        changeOrigin: true,
        secure: false,
        rewrite: (path) =>
          path.replace(/^\/api\/auth/, "/AUTH-SERVICE/auth"),
      },
    },
  },
});