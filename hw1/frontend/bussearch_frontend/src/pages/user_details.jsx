import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

function UserDetails() {
    const { state } = useLocation();
    const tripId = state.tripId;
    console.log(tripId);
    const navigate = useNavigate();

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');

    const handlePurchase = async () => {
        const params = new URLSearchParams();
        params.append('tripId', tripId);
        params.append('firstName', firstName);
        params.append('lastName', lastName);
        params.append('email', email);

        const response = await fetch('http://localhost:8002/api/reservations/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params
        });
        console.log(tripId)
        if (response.ok) {
            const data = await response.json();
            const token = data.token;
            alert('Reservation successful! Here\'s your token: ' + token + '. Please keep it safe.');
            navigate('/trips');
        } else {
            alert('There was a problem with your reservation. Please try again.');
        }
    };

    return (
        <div className='web_page'>
            <div className="navbar bg-green-100 flex justify-between">
                <a className="btn btn-ghost text-xl" href="/trips">Bus Search</a>
                <a className="btn btn-ghost text-xl" href='./reservations'>See your reservations</a>
            </div>
            <div className='m-4 w-1/2 flex flex-col space-y-4'>
                <input type="text" placeholder="First Name" className="input input-bordered w-full" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                <input type="text" placeholder="Last Name" className="input input-bordered w-full" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                <input type="email" placeholder="Email" className="input input-bordered w-full" value={email} onChange={(e) => setEmail(e.target.value)} />
                <button className="btn btn-primary" onClick={handlePurchase}>Purchase</button>
            </div>
        </div>
    );
}

export default UserDetails;
