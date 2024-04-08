import React, { useState, useEffect } from 'react';

function Reservations() {
    const [token, setToken] = useState('');
    const [reservation, setReservation] = useState(null);
    const [error, setError] = useState(false);

    useEffect(() => {
        if (token !== '') {
            console.log("token: ", token)
            fetch(`http://localhost:8002/api/reservations/${token}`)
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Reservation not found');
                    }
                })
                .then(data => {
                    setReservation(data);
                    setError(false);
                })
                .catch(() => {
                    setError(true);
                    setReservation(null);
                });
        }
    }, [token]);
    console.log("reservation: ", reservation)
    return (
        <div className='web_page'>
            <div className="navbar bg-green-100 flex justify-between">
                <a className="btn btn-ghost text-xl" href="/trips">Bus Search</a>
                <a className="btn btn-ghost text-xl" href='./reservations'>See your reservations</a>
            </div>
            <div className='m-8'>
                <div className="flex justify-center">
                    <input type="text" placeholder="Insert Reservation Token" className="input input-bordered w-1/3" value={token} onChange={(e) => setToken(e.target.value)} />
                </div>
                <div className="flex justify-center mt-4">
                    {reservation && !error ? (
                        <div className="card w-96 bg-base-100 shadow-xl">
                            <div className="card-body">
                                <h2 className="card-title">TRIP</h2>
                                <p>FROM: {reservation.fromCity}</p>
                                <p>TO: {reservation.toCity}</p>
                                <p>DATE: {reservation.dateTrip}</p>
                                <p>TIME: {reservation.timeTrip}</p>
                                <p>FIRST NAME: {reservation.firstName}</p>
                                <p>LAST NAME: {reservation.lastName}</p>
                                <p>EMAIL: {reservation.email}</p>
                            </div>
                        </div>
                    ) : error ? (
                        <p>Trips with that token don't exist!</p>
                    ) : null}
                </div>
            </div>
        </div>
    );
}

export default Reservations;
