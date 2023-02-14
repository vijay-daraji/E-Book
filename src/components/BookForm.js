import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import api from "../lib/axios";
import "./BookForm.css";

const BookForm = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [book, setBook] = useState(
    location?.state?.id ? { ...location?.state } : null
  );
  const [categoryOptions, setCategoryOptions] = useState([]);
  const [option, setOption] = useState(null);
  const [showAlert, setShowAlert] = useState(false);

  const fetchOptionData = async () => {
    try {
      const { data } = await api.get("/categories");
      setCategoryOptions(data);
    } catch (e) {
      console.log(e);
      return null;
    }
  };
  useEffect(() => {
    fetchOptionData();
  }, []);
  const updateBook = async (bookId) => {
    if (!validate()) {
      setShowAlert(true);
      return;
    }
    try {
      const { data } = await api.put(`/books/${bookId}`, book);
      navigate("/");
    } catch (e) {
      console.log(e);
      return null;
    }
  };
  const addBook = async () => {
    if (!validate()) {
      setShowAlert(true);
      return;
    }
    try {
      const { data } = await api.post("/books", book);
      navigate("/");
    } catch (e) {
      console.log(e);
      return null;
    }
  };

  const handleChange = (e) => {
    setOption(e.target.value);
    setBook({ ...book, categoryName: e.target.value });
  };

  const validate = () => {
    if (
      book === null ||
      book?.title?.length === 0 ||
      book?.autherName?.length === 0 ||
      book?.categoryName?.length === 0 ||
      book?.releaseDate?.length === 0
    ) {
      return false;
    } else {
      return true;
    }
  };
  return (
    <div className="bookform">
      {location?.state?.id ? <h2>Update Book</h2> : <h2>Add Book</h2>}
      {showAlert && <h2>Please Enter All Details</h2>}
      <div className="element">
        <label>Title </label>
        <input
          className="input"
          placeholder="Enter Title"
          value={book?.title}
          onChange={(e) => setBook({ ...book, title: e.target.value })}
        />
      </div>
      <div className="element">
        <label>Auther Name </label>
        <input
          className="input"
          placeholder="Enter Auther Name"
          value={book?.autherName}
          onChange={(e) => setBook({ ...book, autherName: e.target.value })}
        />
      </div>
      <div className="element">
        <label>Category </label>
        <select onChange={handleChange} className="input">
          <option>select</option>
          {categoryOptions &&
            categoryOptions.map((category) => (
              <option key={category.id} value={option?.id}>
                {category.categoryName}
              </option>
            ))}
        </select>
      </div>

      <div className="element">
        <label>Description </label>
        <textarea
          className="input"
          placeholder="Enter Description"
          value={book?.description}
          onChange={(e) => setBook({ ...book, description: e.target.value })}
        />
      </div>
      <div className="element">
        <label>Realese Date </label>
        <input
          className="input"
          placeholder="Ex 2023-01-01"
          value={book?.releaseDate}
          onChange={(e) => setBook({ ...book, releaseDate: e.target.value })}
        />
      </div>
      {location?.state?.id ? (
        <button onClick={() => updateBook(location?.state?.id)}>
          Update Book
        </button>
      ) : (
        <button onClick={() => addBook()}>Add Book</button>
      )}
    </div>
  );
};

export default BookForm;
