import { useState } from "react";

function SearchTrips() {
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
                        <tr>
                            <td>567</td>
                            <td>Cy Ganderton</td>
                            <td>Quality Control Specialist</td>
                            <td>2017-09-01</td>
                            <td>09:30H</td>
                            <td>1.08€</td>
                            <td><button className="btn btn-primary m-4">Reserve</button></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}
export default SearchTrips