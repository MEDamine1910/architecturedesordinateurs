import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { filmService } from '../services/api';
import authService from '../services/authService';
import Loading from '../components/Loading';
import Error from '../components/Error';
import ReservationForm from '../components/ReservationForm';

const FilmDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [film, setFilm] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [showReservationForm, setShowReservationForm] = useState(false);

  useEffect(() => {
    loadFilm();
  }, [id]);

  const loadFilm = async () => {
    try {
      setLoading(true);
      setError(null);
      const data = await filmService.getFilmById(id);
      setFilm(data);
    } catch (err) {
      setError(err.response?.data?.message || 'Erreur lors du chargement du film');
    } finally {
      setLoading(false);
    }
  };

  const formatDuration = (minutes) => {
    const hours = Math.floor(minutes / 60);
    const mins = minutes % 60;
    return hours > 0 ? `${hours}h${mins}min` : `${mins}min`;
  };

  if (loading) return <Loading />;
  if (error) return <Error message={error} onRetry={loadFilm} />;
  if (!film) return null;

  const availabilityPercentage = (film.placesDisponibles / film.placesTotales) * 100;

  return (
    <div>
      <button
        onClick={() => navigate('/')}
        className="mb-6 text-primary-600 hover:text-primary-700 font-medium flex items-center"
      >
        ← Retour à la liste
      </button>

      <div className="card overflow-hidden">
        <div className="bg-gradient-to-r from-primary-600 to-primary-800 p-8 text-white">
          <h1 className="text-4xl font-bold mb-4">{film.titre}</h1>
          <div className="flex items-center space-x-6 text-lg">
            <span className="flex items-center">
              ⏱️ {formatDuration(film.dureeMinutes)}
            </span>
            <span className="flex items-center">
              🎫 {film.placesDisponibles} places disponibles
            </span>
          </div>
        </div>

        <div className="p-8">
          <div className="mb-6">
            <h3 className="text-xl font-semibold mb-4 text-gray-800">Disponibilité</h3>
            <div className="flex items-center justify-between mb-2">
              <span className="text-gray-600">
                Places disponibles: <strong className="text-primary-600">{film.placesDisponibles}</strong>
              </span>
              <span className="text-gray-600">
                Places totales: <strong>{film.placesTotales}</strong>
              </span>
            </div>
            <div className="w-full bg-gray-200 rounded-full h-4">
              <div
                className={`h-4 rounded-full transition-all ${
                  availabilityPercentage > 50
                    ? 'bg-green-500'
                    : availabilityPercentage > 20
                    ? 'bg-yellow-500'
                    : 'bg-red-500'
                }`}
                style={{ width: `${availabilityPercentage}%` }}
              ></div>
            </div>
            <p className="text-sm text-gray-500 mt-2">
              {availabilityPercentage > 50
                ? '✅ Nombreuses places disponibles'
                : availabilityPercentage > 20
                ? '⚠️ Places limitées'
                : '🔴 Plus que quelques places'}
            </p>
          </div>

          {film.placesDisponibles > 0 ? (
            <div>
              {!authService.isAuthenticated() ? (
                <div className="bg-yellow-50 border border-yellow-200 rounded-lg p-4 text-center">
                  <p className="text-yellow-800 mb-4">Vous devez être connecté pour réserver des places</p>
                  <button
                    onClick={() => navigate('/login')}
                    className="btn-primary"
                  >
                    Se connecter
                  </button>
                </div>
              ) : !showReservationForm ? (
                <button
                  onClick={() => setShowReservationForm(true)}
                  className="btn-primary w-full text-lg py-3"
                >
                  Réserver des places
                </button>
              ) : (
                <ReservationForm
                  film={film}
                  onSuccess={() => {
                    setShowReservationForm(false);
                    loadFilm();
                  }}
                  onCancel={() => setShowReservationForm(false)}
                />
              )}
            </div>
          ) : (
            <div className="bg-red-50 border border-red-200 rounded-lg p-4 text-center">
              <p className="text-red-700 font-semibold">Complet - Plus de places disponibles</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default FilmDetail;

