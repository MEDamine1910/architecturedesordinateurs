import { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { paymentService } from '../services/api';

const Payment = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { reservation, film } = location.state || {};
  
  const [formData, setFormData] = useState({
    cardNumber: '',
    amount: 0,
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    if (!reservation || !film) {
      navigate('/');
      return;
    }
    // Calculer le montant (exemple: 10€ par place)
    setFormData(prev => ({
      ...prev,
      amount: reservation.seatsReserved * 10,
    }));
  }, [reservation, film, navigate]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setLoading(true);

    try {
      const paymentRequest = {
        reservationId: reservation.id,
        amount: formData.amount,
        cardNumber: formData.cardNumber.replace(/\s/g, ''),
      };

      const result = await paymentService.processPayment(paymentRequest);
      
      if (result.success) {
        setSuccess(true);
        setTimeout(() => {
          navigate('/reservations');
        }, 3000);
      } else {
        setError(result.message || 'Le paiement a échoué');
      }
    } catch (err) {
      setError(
        err.response?.data?.message || 
        'Erreur lors du traitement du paiement'
      );
    } finally {
      setLoading(false);
    }
  };

  const formatCardNumber = (value) => {
    const v = value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
    const matches = v.match(/\d{4,16}/g);
    const match = (matches && matches[0]) || '';
    const parts = [];
    for (let i = 0, len = match.length; i < len; i += 4) {
      parts.push(match.substring(i, i + 4));
    }
    if (parts.length) {
      return parts.join(' ');
    } else {
      return v;
    }
  };

  if (!reservation || !film) {
    return null;
  }

  if (success) {
    return (
      <div className="max-w-2xl mx-auto">
        <div className="card p-8 text-center">
          <div className="text-6xl mb-4">✅</div>
          <h2 className="text-3xl font-bold text-green-600 mb-4">Paiement Réussi !</h2>
          <p className="text-gray-600 mb-6">
            Votre réservation a été confirmée. Vous allez être redirigé vers vos réservations...
          </p>
          <div className="bg-green-50 border border-green-200 rounded-lg p-4">
            <p className="text-sm text-green-800">
              <strong>Réservation ID:</strong> {reservation.id}<br />
              <strong>Film:</strong> {film.titre}<br />
              <strong>Places:</strong> {reservation.seatsReserved}
            </p>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="max-w-2xl mx-auto">
      <button
        onClick={() => navigate(-1)}
        className="mb-6 text-primary-600 hover:text-primary-700 font-medium flex items-center"
      >
        ← Retour
      </button>

      <div className="card p-8">
        <h2 className="text-3xl font-bold mb-6 text-gray-800">Paiement</h2>

        <div className="bg-gray-50 rounded-lg p-6 mb-6">
          <h3 className="font-semibold text-lg mb-4">Détails de la réservation</h3>
          <div className="space-y-2 text-gray-700">
            <p><strong>Film:</strong> {film.titre}</p>
            <p><strong>Client:</strong> {reservation.clientName}</p>
            <p><strong>Nombre de places:</strong> {reservation.seatsReserved}</p>
            <p className="text-xl font-bold text-primary-600 pt-2 border-t">
              <strong>Montant total:</strong> {formData.amount.toFixed(2)} €
            </p>
          </div>
        </div>

        {error && (
          <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-6">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block text-gray-700 font-semibold mb-2">
              Numéro de carte *
            </label>
            <input
              type="text"
              required
              value={formData.cardNumber}
              onChange={(e) => {
                const formatted = formatCardNumber(e.target.value);
                setFormData({ ...formData, cardNumber: formatted });
              }}
              className="input-field"
              placeholder="1234 5678 9012 3456"
              maxLength="19"
            />
            <p className="text-xs text-gray-500 mt-1">
              Format: 13-19 chiffres (exemple: 1234567890123456)
            </p>
          </div>

          <div className="bg-yellow-50 border border-yellow-200 rounded-lg p-4">
            <p className="text-sm text-yellow-800">
              ⚠️ <strong>Mode démo:</strong> Ceci est une simulation. Aucun paiement réel ne sera effectué.
            </p>
          </div>

          <div className="flex space-x-4">
            <button
              type="submit"
              disabled={loading}
              className="btn-primary flex-1 disabled:opacity-50 disabled:cursor-not-allowed text-lg py-3"
            >
              {loading ? 'Traitement du paiement...' : `Payer ${formData.amount.toFixed(2)} €`}
            </button>
            <button
              type="button"
              onClick={() => navigate(-1)}
              className="btn-secondary"
              disabled={loading}
            >
              Annuler
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Payment;

