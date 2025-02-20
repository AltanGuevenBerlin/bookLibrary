import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";

const AddBook = () => {
    const [title, setTitle] = useState('');
    const [genre, setGenre] = useState('');
    const [author, setAuthor] = useState('');
    const [publicationYear, setPublicationYear] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();

        const newBook = { title, genre, author, publicationYear: Number(publicationYear) };

        try {
            await axios.post('http://localhost:8080/api/book/add', newBook);
            alert('Buch erfolgreich hinzugefügt!');
            navigate('/');
        } catch (error) {
            console.error('Fehler:', error);
            alert('Fehler beim Speichern des Buches');
        }
    };

    return (
        <div>
            <h2>Buch hinzufügen</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Titel:
                    <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} required/>
                </label>
                <br/>
                <label>
                    Autor:
                    <input type="text" value={author} onChange={(e) => setAuthor(e.target.value)} required/>
                </label>
                <br/>
                <label>
                    Genre:
                    <input type="text" value={genre} onChange={(e) => setGenre(e.target.value)} required/>
                </label>
                <br/>
                <label>
                    Erscheinungsjahr:
                    <input type="number" value={publicationYear} onChange={(e) => setPublicationYear(e.target.value)}
                           required/>
                </label>
                <br/>
                <button type="submit">Buch speichern</button>
                <button type="button" onClick={() => navigate('/')}>Abbrechen</button>
            </form>
        </div>
    );
};

export default AddBook;
