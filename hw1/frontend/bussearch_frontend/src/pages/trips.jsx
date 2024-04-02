import { useState } from "react";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

function Trips() {
    const [value, onChange] = useState(new Date());

    return (
        <div className="web_page">
            <div className="navbar bg-green-100 flex justify-between">
                <a className="btn btn-ghost text-xl">Bus Search</a>
                <a className="btn btn-ghost text-xl" href='./reservations'>See your reservations</a>
            </div>

            {/* Grouping city selection sections */}
            <div className="flex flex-col m-6">
                <div className="flex">
                    <div className="grid flex-col flex-grow card bg-base-300 rounded-box place-items-start m">
                        <div className="flex flex-col m-8" id="fromCity">
                            <select className="select w-full max-w-xs">
                                <option disabled selected>Choose a City</option>
                                <option>Aveiro</option>
                                <option>Lisboa</option>
                                <option>Porto</option>
                                <option>Viseu</option>
                                <option>Guarda</option>
                            </select>
                        </div>
                    </div>
                    <div className="divider divider-horizontal">TO</div>
                    <div className="grid flex-col flex-grow card bg-base-300 rounded-box place-items-start">
                        <div className="flex flex-col m-8" id="toCity">
                            <select className="select w-full max-w-xs">
                                <option disabled selected>Choose a City</option>
                                <option>Aveiro</option>
                                <option>Lisboa</option>
                                <option>Porto</option>
                                <option>Viseu</option>
                                <option>Guarda</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div className="flex flex-col m-8 items-center">
                    <divider className="divider divider-vertical m-4">WHEN</divider>
                    <Calendar
                        onChange={onChange}
                        value={value}
                    />
                    <button className="btn btn-primary m-4">Search</button>
                </div>
            </div>
        </div>
    );
}

export default Trips;
