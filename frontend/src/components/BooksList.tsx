import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

function BooksList() {

    const [book, setBook] = useState([])
    const navigator = useNavigate();

    function addNewBook() {
        navigator(("/add-book"))
    }
}