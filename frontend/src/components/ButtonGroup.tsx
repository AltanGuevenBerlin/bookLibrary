import React from 'react';
import {useNavigate} from "react-router-dom";

const ButtonGroup = () => {
    const navigate = useNavigate();

    return (
        <div>
            <button onClick={() => navigate('/books')}>📚 Alle Bücher anzeigen</button> {/* NEUER BUTTON */}
            <button onClick={() => navigate('/add-book')}>Hinzufügen</button>
            <button onClick={() => navigate('/search-book')}>Suchen</button>
            <button onClick={() => navigate('/delete-book')}>Entfernen</button>
        </div>
    );
};

export default ButtonGroup;
