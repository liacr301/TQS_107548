import React from "react";
import { useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "./components/navbar.jsx";
import HomePage from "./pages/homepage.jsx";
import Trips from "./pages/trips.jsx";
import UserDetails from "./pages/user_details.jsx";
import './App.css'

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
              <HomePage />
          }
        />
        <Route
          path="/trips"
          element={
              <Trips />
          }
        />
        <Route
          path="/user_details"
          element={
              <UserDetails />
          }
        />  
      </Routes>
    </BrowserRouter>
  );
}

export default App
