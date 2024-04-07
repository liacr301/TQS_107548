import React from "react";
import { useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "./components/navbar.jsx";
import HomePage from "./pages/homepage.jsx";
import Trips from "./pages/trips.jsx";
import SearchTrips from "./pages/search_trips.jsx";
import Reservations from "./pages/reservations.jsx";
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
          path="/search_trips"
          element={
              <SearchTrips />
          }
        />
        <Route
          path="/reservations"
          element={
              <Reservations />
          }
        />
        <Route
          path="/user_details/:tripId"
          element={
              <UserDetails />
          }
        />  
      </Routes>
    </BrowserRouter>
  );
}

export default App
