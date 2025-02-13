import React, { useState } from 'react';
import axios from 'axios';

const SearchBook = () => {
    const [query, setQuery] = useState('');
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(false);

    const handleSearch = async (event: React.FormEvent) => {
        event.preventDefault();
        setLoading(true);
        try {
            const response = await axios.get(`http://localhost:8080/api/book/getAll`);
            // Filtere die Ergebnisse basierend auf der Suchanfrage (hier nach Titel)
            const filteredBooks = response.data.filter((book: any) =>
                book.title.toLowerCase().includes(query.toLowerCase())
            );
            setBooks(filteredBooks);
        } catch (error) {
            console.error('Fehler bei der Suche:', error);
            alert('Fehler beim Laden der Bücher');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <h2>Suche nach einem Buch</h2>
            <form onSubmit={handleSearch}>
                <label>
                    Titel:
                    <input
                        type="text"
                        value={query}
                        onChange={(e) => setQuery(e.target.value)}
                        placeholder="Buch Titel"
                        required
                    />
                </label>
                <button type="submit">Suchen</button>
            </form>

            {loading && <p>Lädt...</p>}

            {books.length > 0 ? (
                <div>
                    <h3>Suchergebnisse:</h3>
                    <ul>
                        {books.map((book: any) => (
                            <li key={book.id}>
                                <strong>{book.title}</strong> by {book.author} - {book.genre} ({book.publicationYear})
                            </li>
                        ))}
                    </ul>
                </div>
            ) : (
                <p>Keine Bücher gefunden.</p>
            )}
        </div>
    );
};

export default SearchBook;
