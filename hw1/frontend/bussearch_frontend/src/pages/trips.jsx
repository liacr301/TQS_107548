import {useState, React} from "react";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

function Trips() {
    const [value, onChange] = useState(new Date());

    return (
        <div class="web_page">
            <div className="navbar bg-green-100">
                <a className="btn btn-ghost text-xl">Bus Search</a>
            </div>

            <div className="flex m-6">
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
                    <div className="flex flex-col m-8" id="fromDate">
                        <Calendar onChange={onChange} value={value}/>
                        <p className='text-center'>
                            <span className='bold'>Selected Date:</span>{' '}
                            {value.toDateString()}
                        </p>
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
                    <div className="flex flex-col m-8" id="fromDate">
                        <Calendar onChange={onChange} value={value}/>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default Trips