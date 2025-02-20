import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

interface Book {
    id: string;
    title: string;
    author: string;
    genre: string;
    publicationYear: number;
}

function BooksList() {
    const [books, setBooks] = useState<Book[]>([]);
    const [sortOrder, setSortOrder] = useState<{ field: keyof Book; order: "asc" | "desc" }>({
        field: "title",
        order: "asc"
    });
    const navigate = useNavigate();

    useEffect(() => {
        axios.get("http://localhost:8080/api/book/getAll")
            .then(response => setBooks(response.data))
            .catch(error => console.error("Fehler beim Laden der Bücher:", error));
    }, []);

    // Sortieren der Bücher
    function sortBooks(field: keyof Book) {
        const newOrder = sortOrder.field === field && sortOrder.order === "asc" ? "desc" : "asc";
        const sortedBooks = [...books].sort((a, b) => {
            if (a[field] < b[field]) return newOrder === "asc" ? -1 : 1;
            if (a[field] > b[field]) return newOrder === "asc" ? 1 : -1;
            return 0;
        });
        setBooks(sortedBooks);
        setSortOrder({ field, order: newOrder });
    }

    function addNewBook() {
        navigate("/add-book");
    }

    return (
        <div>
            <h2>Meine Bücherei</h2>
            <button onClick={addNewBook}>Neues Buch hinzufügen</button>
            <table>
                <thead>
                <tr>
                    <th onClick={() => sortBooks("title")}>Titel {sortOrder.field === "title" ? (sortOrder.order === "asc" ? "↑" : "↓") : ""}</th>
                    <th onClick={() => sortBooks("author")}>Autor {sortOrder.field === "author" ? (sortOrder.order === "asc" ? "↑" : "↓") : ""}</th>
                    <th onClick={() => sortBooks("genre")}>Genre {sortOrder.field === "genre" ? (sortOrder.order === "asc" ? "↑" : "↓") : ""}</th>
                    <th onClick={() => sortBooks("publicationYear")}>Veröffentlichungsjahr {sortOrder.field === "publicationYear" ? (sortOrder.order === "asc" ? "↑" : "↓") : ""}</th>
                    <th onClick={() => sortBooks("id")}>ID {sortOrder.field === "id" ? (sortOrder.order === "asc" ? "↑" : "↓") : ""}</th>
                </tr>
                </thead>
                <tbody>
                {books.map(book => (
                    <tr key={book.id}>
                        <td>{book.title}</td>
                        <td>{book.author}</td>
                        <td>{book.genre}</td>
                        <td>{book.publicationYear}</td>
                        <td>{book.id}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default BooksList;
