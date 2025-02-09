import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const SearchBook = () => {
    const [title, setTitle] = useState(""); // nur nach Titeln suchen.
    const [results, setResults] = useState<{ id: string; title: string; author: string }[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleSearch = async (event: React.FormEvent) => {
        event.preventDefault();
        setLoading(true);
        setError("");

        try {
            const response = await fetch(`http://localhost:8080/api/books/search?title=${title}`);
            if (!response.ok) {
                throw new Error("Fehler beim Abrufen der Bücher");
            }
            const data = await response.json();
            setResults(data);
        } catch (err) {
            setError("Fehler beim Abrufen der Bücher.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <h2>Buch nach Titel suchen</h2>
            <form onSubmit={handleSearch}>
                <input
                    type="text"
                    placeholder="Buchtitel eingeben"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                />
                <button type="submit">Suchen</button>
                <button type="button" onClick={() => navigate("/")}>Zurück</button>
            </form>

            {loading && <p>Lade Bücher...</p>}
            {error && <p style={{ color: "red" }}>{error}</p>}

            {results.length > 0 ? (
                <ul>
                    {results.map((book) => (
                        <li key={book.id}>
                            <strong>{book.title}</strong> von {book.author}
                        </li>
                    ))}
                </ul>
            ) : (
                !loading && <p>Keine Bücher gefunden.</p>
            )}
        </div>
    );
};

export default SearchBook;