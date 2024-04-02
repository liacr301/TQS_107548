import React from 'react';

function Reservations() {
    return (
        <div className='web_page'>
            <div className="navbar bg-green-100 flex justify-between">
                <a className="btn btn-ghost text-xl">Bus Search</a>
                <a className="btn btn-ghost text-xl" href='./reservations'>See your reservations</a>
            </div>
            <div className='m-8 flex justify-between flex-wrap'>
                <div className="card w-96 bg-base-100 shadow-xl">
                    <div className="card-body">
                        <h2 className="card-title">TRIP</h2>
                        <p>BUS: 567</p>
                        <p>FROM: AVEIRO</p>
                        <p>TO: LISBON</p>
                        <p>DATE: 2022-12-12</p>
                        <p>TIME: 12:00H</p>
                        <p>PRICE: 10€</p>
                        <div className="card-actions justify-end">
                        </div>
                    </div>
                </div>
                <div className="card w-96 bg-base-100 shadow-xl">
                    <div className="card-body">
                        <h2 className="card-title">TRIP</h2>
                        <p>BUS: 567</p>
                        <p>FROM: AVEIRO</p>
                        <p>TO: LISBON</p>
                        <p>DATE: 2022-12-12</p>
                        <p>TIME: 12:00H</p>
                        <p>PRICE: 10€</p>
                        <div className="card-actions justify-end">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default Reservations