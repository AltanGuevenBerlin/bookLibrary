import { useState } from "react";

const BookComponent = () => {
    const [id, setId] = useState("");
    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [genre, setGenre] = useState("");
    const [publicationYear, setPublicationYear] = useState<number | string>("");

    // Event-Handler für Formularabsendungen
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        // Hier könntest du eine API-Anfrage oder Logik hinzufügen, um das Buch zu speichern
        console.log({
            id,
            title,
            author,
            genre,
            publicationYear
        });
    };

    return (
        <div>
            <h2>Neues Buch hinzufügen</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>ID:</label>
                    <input
                        type="text"
                        value={id}
                        onChange={(e) => setId(e.target.value)}
                    />
                </div>
                <div>
                    <label>Titel:</label>
                    <input
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                    />
                </div>
                <div>
                    <label>Autor:</label>
                    <input
                        type="text"
                        value={author}
                        onChange={(e) => setAuthor(e.target.value)}
                    />
                </div>
                <div>
                    <label>Genre:</label>
                    <input
                        type="text"
                        value={genre}
                        onChange={(e) => setGenre(e.target.value)}
                    />
                </div>
                <div>
                    <label>Veröffentlichungsjahr:</label>
                    <input
                        type="number"
                        value={publicationYear}
                        onChange={(e) => setPublicationYear(e.target.value)}
                    />
                </div>
                <button type="submit">Buch hinzufügen</button>
            </form>
        </div>
    );
};

export default BookComponent;
