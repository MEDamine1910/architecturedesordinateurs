import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { reservationService, filmService } from '../services/api';
import authService from '../services/authService';
import Loading from '../components/Loading';
import Error from '../components/Error';

const Reservations = () => {
  const [reservations, setReservations] = useState([]);
  const [films, setFilms] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Rediriger vers la page de login si l'utilisateur n'est pas authentifié
    if (!authService.isAuthenticated()) {
      navigate('/login');
      return;
    }
    loadReservations();
  }, [navigate]);

  const loadReservations = async () => {
    try {
      setLoading(true);
      setError(null);
      const reservationsData = await reservationService.getAllReservations();
      setReservations(reservationsData);

      // Charger les détails des films
      const filmIds = [...new Set(reservationsData.map(r => r.filmId))];
      const filmsData = {};
      for (const filmId of filmIds) {
        try {
          const film = await filmService.getFilmById(filmId);
          filmsData[filmId] = film;
        } catch (err) {
          console.error(`Erreur lors du chargement du film ${filmId}:`, err);
        }
      }
      setFilms(filmsData);
    } catch (err) {
      setError(err.response?.data?.message || 'Erreur lors du chargement des réservations');
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <Loading />;
  if (error) return <Error message={error} onRetry={loadReservations} />;

  return (
    <div>
      <div className="mb-8">
        <h2 className="text-4xl font-bold text-gray-800 mb-2">Mes Réservations</h2>
        <p className="text-gray-600">Consultez toutes vos réservations</p>
      </div>

      {reservations.length === 0 ? (
        <div className="card p-12 text-center">
          <div className="text-6xl mb-4">🎫</div>
          <h3 className="text-2xl font-semibold text-gray-800 mb-2">Aucune réservation</h3>
          <p className="text-gray-600 mb-6">Vous n'avez pas encore de réservations</p>
          <button
            onClick={() => navigate('/')}
            className="btn-primary"
          >
            Voir les films disponibles
          </button>
        </div>
      ) : (
        <div className="space-y-4">
          {reservations.map((reservation) => {
            const film = films[reservation.filmId];
            return (
              <div key={reservation.id} className="card p-6">
                <div className="flex items-start justify-between">
                  <div className="flex-1">
                    <div className="flex items-center space-x-4 mb-4">
                      <div className="bg-primary-100 text-primary-700 rounded-lg p-3">
                        <span className="text-2xl">🎬</span>
                      </div>
                      <div>
                        <h3 className="text-2xl font-bold text-gray-800">
                          {film ? film.titre : `Film ID: ${reservation.filmId}`}
                        </h3>
                        <p className="text-gray-600">Réservation #{reservation.id}</p>
                      </div>
                    </div>
                    
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
                      <div className="bg-gray-50 rounded-lg p-4">
                        <p className="text-sm text-gray-600 mb-1">Client</p>
                        <p className="font-semibold text-gray-800">{reservation.clientName}</p>
                      </div>
                      <div className="bg-gray-50 rounded-lg p-4">
                        <p className="text-sm text-gray-600 mb-1">Places réservées</p>
                        <p className="font-semibold text-gray-800">{reservation.seatsReserved}</p>
                      </div>
                      {film && (
                        <div className="bg-gray-50 rounded-lg p-4">
                          <p className="text-sm text-gray-600 mb-1">Durée</p>
                          <p className="font-semibold text-gray-800">
                            {Math.floor(film.dureeMinutes / 60)}h{film.dureeMinutes % 60}min
                          </p>
                        </div>
                      )}
                    </div>
                  </div>
                  
                  {film && (
                    <button
                      onClick={() => navigate(`/film/${film.id}`)}
                      className="btn-secondary ml-4"
                    >
                      Voir le film
                    </button>
                  )}
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};

export default Reservations;

