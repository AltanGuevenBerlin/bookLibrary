import './App.css';
import ButtonGroup from './components/ButtonGroup.tsx';
import Footer from './components/Footer.tsx';
import HeaderComponent from './components/Header.tsx';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddBook from './pages/AddBook.tsx';
import SearchBook from './pages/SearchBook.tsx';
import DeleteBook from './pages/DeleteBook.tsx';
import BooksList from "./components/BooksList.tsx"; // Importiere die DeleteBook-Seite

function App() {
    return (
        <Router>
            <div className="App">
                <HeaderComponent />
                <h1>Unser Bücherregal</h1>
                <Routes>
                    <Route path="/" element={<ButtonGroup />} />
                    <Route path="/add-book" element={<AddBook />} />
                    <Route path="/books" element={<BooksList />} /> {/* 📌 Neue Route für die Buchliste */}
                    <Route path="/search-book" element={<SearchBook />} />
                    <Route path="/delete-book" element={<DeleteBook />} /> {/* Neue Route für DeleteBook */}
                </Routes>
                <Footer />
            </div>
        </Router>
    );
}

export default App;
