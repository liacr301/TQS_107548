import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import React from 'react';
import { useNavigate } from 'react-router-dom';

function SearchTrips() {
    const { state } = useLocation();
    const [trips, setTrips] = useState([]); 
    const [currency, setCurrency] = useState('EUR');
    const navigate = useNavigate();

    const handleClick = (tripId) => {
        navigate('/user_details', { state: { tripId } });
    };

    useEffect(() => {
        fetchTrips();
    }, [state, currency]);

    const fetchTrips = async () => {
        if (!state) return;
        const response = await fetch(
            `http://localhost:8002/api/trips/all_for_search?fromCity=${state.fromCity}&toCity=${state.toCity}&dateTrip=${state.dateTrip}`,
            { method: "GET" }
        );
        const data = await response.json();
        const tripsWithConvertedPrices = await Promise.all(data.map(async (trip) => {
            const convertedPrice = await fetchConvertedPrice(trip.price);
            return { ...trip, price: convertedPrice };
        }));
        setTrips(tripsWithConvertedPrices);
    };

    const fetchConvertedPrice = async (price) => {
        const response = await fetch(`http://localhost:8002/api/exchange/?amount=${price}&currency=${currency}`);
        if (response.ok) {
            const convertedPrice = await response.json();
            return convertedPrice;
        } else {
            console.error('Falha ao obter o preço convertido');
            return price;
        }
    };

    return (
        <div className="web_page">
            <div className="navbar bg-green-100 flex justify-between">
                <a className="btn btn-ghost text-xl" href="/trips">Bus Search</a>
                <a className="btn btn-ghost text-xl" href='./reservations'>See your reservations</a>
            </div>
            <div className="m-8">
                <div className="w-full mb-4">
                <select className="select w-1/2 max-w-xs bg-green-100" onChange={(e) => setCurrency(e.target.value)} value={currency}>
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
                            <th>Seats Available</th>
                        </tr>
                        </thead>
                        <tbody>
                        {trips.map((trip) => (
                            <tr key={trip.id}>
                                <td>{trip.bus}</td>
                                <td>{trip.fromCity}</td>
                                <td>{trip.toCity}</td>
                                <td>{trip.dateTrip}</td>
                                <td>{trip.timeTrip}</td>
                                <td>{trip.price.toFixed(2)}{currency}</td>
                                <td>{trip.availableSeats}</td>
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