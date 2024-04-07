import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import React from 'react';
import { useNavigate } from 'react-router-dom';

function SearchTrips() {

    const { state } = useLocation();
    const [trips, setTrips] = useState([]); 
    const navigate = useNavigate();

    const handleClick = (tripId) => {
        navigate('/user-details', { state: { tripId } });
    };

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(
                `http://localhost:8001/api/trips/search_for_all?fromCity=${state.fromCity}&toCity=${state.toCity}&dateTrip=${state.dateTrip}`,
                { method: "GET" }
            );
            const data = await response.json();
            setTrips(data);
        };

        if (state) {
            fetchData().catch(console.error);
        }
    }, [state]);

    return (
        <div className="web_page">
            <div className="navbar bg-green-100 flex justify-between">
                <a className="btn btn-ghost text-xl">Bus Search</a>
                <a className="btn btn-ghost text-xl" href='./reservations'>See your reservations</a>
            </div>
            <div className="m-8">
                <div className="w-full mb-4">
                <select className="select w-1/2 max-w-xs bg-green-100">
                        <option>EUR</option>
                        <option>USD</option>
                        <option>GBP</option>
                        <option>AUD</option>
                        <option>CAD</option>
                    </select>
                </div>
                <div className="overflow-x-auto">
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Bus</th>
                            <th>From City</th>
                            <th>To City</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        {trips.map((trip) => (
                            <tr key={trip.id}>
                                <td>{trip.bus}</td>
                                <td>{trip.fromCity}</td>
                                <td>{trip.toCity}</td>
                                <td>{trip.date}</td>
                                <td>{trip.time}</td>
                                <td>{trip.price}</td>
                                <td><button onClick={() => handleClick(trip.id)} className="btn btn-primary m-4">Reserve</button></td>
                            </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}
export default SearchTrips