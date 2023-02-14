import { BrowserRouter, Route, Routes } from "react-router-dom";
import BookForm from "./components/BookForm";
import ShowBooks from "./components/ShowBooks";
import "./App.css";

function App() {
  return (
    <div className="app">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<ShowBooks />}></Route>
          <Route path="/add-book" element={<BookForm />}></Route>
          <Route path="/update-book" element={<BookForm />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
