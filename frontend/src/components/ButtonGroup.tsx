import React from 'react';
import {useNavigate} from "react-router-dom";

const ButtonGroup = () => {
    const navigate = useNavigate();

    return (
        <div>
            <button onClick={() => navigate('/add-book')}>Hinzufügen</button>
            <button onClick={() => navigate('/search-book')}>Suchen</button>
            <button onClick={() => navigate('/delete-book')}>Entfernen</button>
            <button onClick={() => alert('Verleih das Buch')}>Verleihen</button>
        </div>
    );
};

export default ButtonGroup;
