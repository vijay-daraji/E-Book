import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../lib/axios";
import "./ShowBooks.css";

const ShowBooks = () => {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const fetchData = async () => {
    try {
      const { data } = await api.get(`/books`);
      setBooks(data);
      return data;
    } catch (e) {
      console.log("api error", e);
      return null;
    }
  };

  useEffect(() => {
    fetchData();
    setLoading(false);
  }, [loading]);

  const deleteBook = async (bookId) => {
    try {
      await api.delete(`/books/${bookId}`);
      setLoading(true);
    } catch (e) {
      console.log("api error", e);
      return null;
    }
    navigate("/");
  };

  const addBook = () => {
    navigate("/add-book");
  };

  const updateBook = (book) => {
    navigate("/update-book", {
      state: {
        ...book,
      },
    });
  };

  return (
    <div className="showbooks">
      <h1>Book List</h1>
      {books.length !== 0 ? (
        <div className="table">
          <table>
            <thead>
              <tr className="head">
                <td>Category</td>
                <td>Title</td>
                <td>Description</td>
                <td>Auther Name</td>
                <td>Release Date</td>
                <td>Action</td>
              </tr>
            </thead>
            <tbody>
              {books &&
                books?.map((book) => (
                  <tr key={book.id} className="body">
                    <td>{book.categoryName}</td>
                    <td>{book.title}</td>
                    <td>{book.description}</td>
                    <td>{book.autherName}</td>
                    <td>{book.releaseDate}</td>
                    <td>
                      <button onClick={() => updateBook(book)}>Update</button>
                      <button
                        onClick={() => deleteBook(book.id)}
                        className="delete-btn"
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      ) : (
        <div>Book not available</div>
      )}
      <button onClick={() => addBook()} className="add-book">
        Add Book
      </button>
    </div>
  );
};

export default ShowBooks;
