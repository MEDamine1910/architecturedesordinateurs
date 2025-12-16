import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { reservationService } from '../services/api';
import authService from '../services/authService';

const ReservationForm = ({ film, onSuccess, onCancel }) => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    clientName: '',
    seatsReserved: 1,
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Pré-remplir le nom avec le username si l'utilisateur est connecté
    if (authService.isAuthenticated()) {
      const username = authService.getUsername();
      setFormData(prev => ({ ...prev, clientName: username }));
    }
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setLoading(true);

    try {
      const reservation = {
        filmId: film.id,
        clientName: formData.clientName.trim(),
        seatsReserved: parseInt(formData.seatsReserved),
      };

      const createdReservation = await reservationService.createReservation(reservation);
      
      // Rediriger vers la page de paiement
      navigate('/payment', { 
        state: { 
          reservation: createdReservation,
          film: film 
        } 
      });
    } catch (err) {
      setError(
        err.response?.data?.message || 
        'Erreur lors de la création de la réservation'
      );
    } finally {
      setLoading(false);
    }
  };

  const maxSeats = Math.min(film.placesDisponibles, 10);

  return (
    <div className="border-2 border-primary-200 rounded-lg p-6 bg-primary-50">
      <h3 className="text-2xl font-bold mb-4 text-gray-800">Formulaire de Réservation</h3>
      
      {error && (
        <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
          {error}
        </div>
      )}

      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block text-gray-700 font-semibold mb-2">
            Nom complet *
          </label>
          <input
            type="text"
            required
            value={formData.clientName}
            onChange={(e) => setFormData({ ...formData, clientName: e.target.value })}
            className="input-field"
            placeholder="Entrez votre nom"
          />
        </div>

        <div>
          <label className="block text-gray-700 font-semibold mb-2">
            Nombre de places *
          </label>
          <div className="flex items-center space-x-4">
            <input
              type="number"
              min="1"
              max={maxSeats}
              required
              value={formData.seatsReserved}
              onChange={(e) => {
                const value = parseInt(e.target.value) || 1;
                setFormData({ ...formData, seatsReserved: Math.min(Math.max(1, value), maxSeats) });
              }}
              className="input-field w-32"
            />
            <span className="text-gray-600">
              (Maximum: {maxSeats} places disponibles)
            </span>
          </div>
        </div>

        <div className="bg-blue-50 border border-blue-200 rounded-lg p-4">
          <p className="text-sm text-blue-800">
            <strong>Film:</strong> {film.titre}<br />
            <strong>Places à réserver:</strong> {formData.seatsReserved}
          </p>
        </div>

        <div className="flex space-x-4">
          <button
            type="submit"
            disabled={loading}
            className="btn-primary flex-1 disabled:opacity-50 disabled:cursor-not-allowed"
          >
            {loading ? 'Traitement...' : 'Continuer vers le paiement'}
          </button>
          <button
            type="button"
            onClick={onCancel}
            className="btn-secondary"
            disabled={loading}
          >
            Annuler
          </button>
        </div>
      </form>
    </div>
  );
};

export default ReservationForm;

