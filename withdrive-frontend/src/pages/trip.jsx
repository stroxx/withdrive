import React, { useState,useEffect } from "react";
import TripService from '../services/TripService';
import Map from '../components/map.component'
import moment from 'moment';
import { Button } from "react-bootstrap";
import NotFound from './notfound'
import Rating from '@mui/material/Rating';
import BackButton from '../components/backButton.component'


import form from '../modules/innerPage.module.css'
import text from '../modules/text.module.css'

let id = window.location.pathname.split('/').pop();

const Trip = () => {
    const [trip,setTrip] = useState(null);

    useEffect(() => {
        TripService.getTrip(id).then((response) => {
            setTrip(response.data);
        });
    }, [id])

    if(!trip) return <NotFound/>;

return (
<>
    <br></br>
    <div className={form.authwrapper}> 
        <div className={form.authinner_trip}>
            <BackButton/>
                    <h3>Trip with {trip.driver.firstName} from {trip.origin} to {trip.destination}</h3>
                    <div><h3>For {moment(trip.date).format('MMM. D, YYYY [at] h:mm A z')}</h3></div>
                    <table className="table">
                        <tbody>
                            <tr>
                            <td>Price: €{trip.pricePerPassenger}</td>
                            </tr>
                            <tr>
                            <td>Available seats: 2/{trip.maxPassengers}</td>
                            </tr>
                            <tr>
                            <td>{trip.description}</td>
                            </tr>
                            <tr>
                                <td>
                                Driver rating:
                                <br></br>
                                <Rating name="read-only" value={3.45} precision={0.5} readOnly/>
                                <br></br>
                                <a href="reviews">Read reviews of the driver</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <center><h3><Button> Apply now!</Button></h3></center>
                    <br></br>
                    {/* <h4>Map of the ride:</h4> */}
                    <Map trip={trip} />    
        </div>
    </div>
</>
);
};

export default Trip;