import React from 'react';
import {useNavigate} from "react-router-dom";

const ButtonGroup = () => {
    const navigate = useNavigate();

    return (
        <div>
            <button onClick={() => navigate('/add-book')}>Hinzufügen</button>
            <button onClick={() => navigate('Suche ein bestimmtes Buch')}>Suchen</button>
            <button onClick={() => alert('Lösche das Buch')}>Entfernen</button>
            <button onClick={() => alert('Verleih das Buch')}>Verleihen</button>
        </div>
    );
};

export default ButtonGroup;
