const Error = ({ message, onRetry }) => {
  return (
    <div className="flex flex-col items-center justify-center min-h-[400px] p-8">
      <div className="text-red-500 text-6xl mb-4">⚠️</div>
      <h2 className="text-2xl font-bold text-gray-800 mb-2">Une erreur est survenue</h2>
      <p className="text-gray-600 mb-6">{message || "Une erreur inattendue s'est produite"}</p>
      {onRetry && (
        <button onClick={onRetry} className="btn-primary">
          Réessayer
        </button>
      )}
    </div>
  );
};

export default Error;

