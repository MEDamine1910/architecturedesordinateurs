import { Link, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import authService from '../services/authService';

const Header = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const checkAuth = () => {
      const authenticated = authService.isAuthenticated();
      setIsAuthenticated(authenticated);
      if (authenticated) {
        setUsername(authService.getUsername());
      }
    };

    checkAuth();
    // Vérifier l'authentification toutes les secondes (pour détecter les changements)
    const interval = setInterval(checkAuth, 1000);
    return () => clearInterval(interval);
  }, []);

  const handleLogout = () => {
    authService.logout();
    setIsAuthenticated(false);
    setUsername('');
    navigate('/');
  };

  return (
    <header className="bg-gradient-to-r from-primary-700 to-primary-900 text-white shadow-lg">
      <div className="container mx-auto px-4 py-4">
        <div className="flex items-center justify-between">
          <Link to="/" className="flex items-center space-x-3">
            <div className="w-10 h-10 bg-white rounded-full flex items-center justify-center">
              <span className="text-primary-700 font-bold text-xl">🎬</span>
            </div>
            <h1 className="text-2xl font-bold">Cinema Online</h1>
          </Link>
          <nav className="flex items-center space-x-6">
            {isAuthenticated ? (
              <>
                <span className="text-primary-200">Bonjour, {username}</span>
                <Link 
                  to="/reservations" 
                  className="hover:text-primary-200 transition-colors font-medium"
                >
                  Mes Réservations
                </Link>
                <button
                  onClick={handleLogout}
                  className="hover:text-primary-200 transition-colors font-medium"
                >
                  Déconnexion
                </button>
              </>
            ) : (
              <>
                <Link 
                  to="/login" 
                  className="hover:text-primary-200 transition-colors font-medium"
                >
                  Connexion
                </Link>
                <Link 
                  to="/register" 
                  className="bg-primary-600 hover:bg-primary-500 px-4 py-2 rounded-md transition-colors font-medium"
                >
                  Inscription
                </Link>
              </>
            )}
          </nav>
        </div>
      </div>
    </header>
  );
};

export default Header;

