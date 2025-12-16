import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { filmService } from '../services/api';
import Loading from '../components/Loading';
import Error from '../components/Error';

const Home = () => {
  const [films, setFilms] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    loadFilms();
  }, []);

  const loadFilms = async () => {
    try {
      setLoading(true);
      setError(null);
      const data = await filmService.getAllFilms();
      console.log("[Home] films[0] =", data?.[0]);
      setFilms(data);
    } catch (err) {
      setError(err.response?.data?.message || 'Erreur lors du chargement des films');
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
  if (error) return <Error message={error} onRetry={loadFilms} />;

  return (
    <div>
      <div className="mb-8">
        <h2 className="text-4xl font-bold text-gray-800 mb-2">Films Disponibles</h2>
        <p className="text-gray-600">Choisissez votre film et réservez vos places</p>
      </div>

      {films.length === 0 ? (
        <div className="text-center py-12">
          <p className="text-gray-500 text-lg">Aucun film disponible pour le moment</p>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {films.map((film) => (
            <div
              key={film.id}
              className="card cursor-pointer transform hover:scale-105 transition-transform"
              onClick={() => {
                const id = film?.id;
                if (id == null) return;
                navigate(`/film/${id}`);
              }}
            >
              <div className="bg-gradient-to-br from-primary-500 to-primary-700 p-6 text-white">
                <h3 className="text-2xl font-bold mb-2">{film.titre}</h3>
                <div className="flex items-center justify-between text-sm">
                  <span>⏱️ {formatDuration(film.dureeMinutes)}</span>
                  <span className="bg-white/20 px-3 py-1 rounded-full">
                    {film.placesDisponibles} places
                  </span>
                </div>
              </div>
              <div className="p-6">
                <div className="mb-4">
                  <div className="flex justify-between text-sm text-gray-600 mb-2">
                    <span>Places disponibles</span>
                    <span className="font-semibold text-primary-600">
                      {film.placesDisponibles} / {film.placesTotales}
                    </span>
                  </div>
                  <div className="w-full bg-gray-200 rounded-full h-2">
                    <div
                      className="bg-primary-600 h-2 rounded-full transition-all"
                      style={{
                        width: `${(film.placesDisponibles / film.placesTotales) * 100}%`,
                      }}
                    ></div>
                  </div>
                </div>
                <button
                  className="btn-primary w-full"
                  onClick={(e) => {
                    e.stopPropagation();
                    const id = film?.id;
                    if (id == null) return;
                    navigate(`/film/${id}`);
                  }}
                >
                  Réserver maintenant
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Home;