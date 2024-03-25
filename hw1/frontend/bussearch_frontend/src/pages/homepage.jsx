import React from 'react';
import { useNavigate } from "react-router-dom";
import backgroundImage_1 from '/src/assets/pexels-preston-zeller-15670.jpg';

function HomePage() {
    let navigate = useNavigate(); 
        const routeChange = () =>{ 
            let path = `/trips`; 
            navigate(path);
    }

    return (
        <div className="hero min-h-screen" style={{backgroundImage: `url(${backgroundImage_1})`}}>
            <div className="hero-overlay bg-opacity-60"></div>
                <div className="hero-content text-center text-neutral-content">
                    <div className="max-w-md">
                        <h1 className="mb-5 text-5xl font-bold">Hello there</h1>
                        <p className="mb-5">Embark on your next adventure effortlessly with our intuitive bus trip search engine, connecting you to countless destinations with just a few clicks.</p>
                        <button className="btn btn-primary" onClick={routeChange}>Find a Bus Trip!</button>
                    </div>
                </div>
        </div>
        
    )
}
export default HomePage