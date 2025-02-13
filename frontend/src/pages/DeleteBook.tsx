import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const DeleteBook = () => {
    const [bookId, setBookId] = useState('');
    const navigate = useNavigate();

    const handleDelete = async (event: React.FormEvent) => {
        event.preventDefault();

        try {
            await axios.delete(`http://localhost:8080/api/book/delete/${bookId}`);
            alert('Buch erfolgreich entfernt!');
            navigate('/');
        } catch (error) {
            console.error('Fehler:', error);
            alert('Fehler beim Entfernen des Buches');
        }
    };

    return (
        <div>
            <h2>Buch entfernen</h2>
            <form onSubmit={handleDelete}>
                <label>
                    Buch ID:
                    <input
                        type="text"
                        value={bookId}
                        onChange={(e) => setBookId(e.target.value)}
                        required
                    />
                </label>
                <br />
                <button type="submit">Buch entfernen</button>
                <button type="button" onClick={() => navigate('/')}>Abbrechen</button>
            </form>
        </div>
    );
};

export default DeleteBook;
