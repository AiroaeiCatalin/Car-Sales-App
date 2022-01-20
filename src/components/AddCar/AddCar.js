import axios from 'axios';
import React from 'react'
import { useState, useCallback } from "react";
import './addcar.css'
import useToken from '../../CustomHooks/useToken';
import {Link} from 'react-router-dom';
import { UserContext } from '../../utils/UserContext'
import { useContext } from 'react';
import Dropzone from '../Dropzone';


const AddCar = () => {


    //sa pun label la pret si la ce mai pot la input fielduri as putea si la placeholder
    //modal sa se deschida canda m dat add si s a reusit add-u sau cv notificare
    //imgu ala neaparat
    

    //si aici ar trb sa fac fetch user dupa ce adaug o masina ca sa am anuntu nou in anunturile mele cred,testez maine

    const [manufacturer, setManufacturer] = useState('');
    const [model, setModel] = useState('');
    const [km, setKm] = useState('');
    const [price, setPrice] = useState('');
    const [power, setPower] = useState('');
    const [dateOfFabrication, setDateOfFabrication] = useState('');
    const [transmissionType, setTransmissionType] = useState('MANUAL');
    const [engineType, setEngineType] = useState('DIESEL');
    const {token, setTokenaa} = useToken();
    const [description, setDescription] = useState('tralalalala');
    const {user, setUser} = useContext(UserContext);
    const [email, setEmail] = useState(user.userName);
    const [adResponse, setAdResponse] = useState();

    // console.log(user);
  
    const handleSubmit = async e => {
        e.preventDefault();
        const car = { manufacturer, model, price, dateOfFabrication, km, engineType, transmissionType, power};
        const appUser = {email};
        const ad = {description, car, appUser}
        axios.post('http://localhost:8080/api/v1/ad', ad)
            .then(response =>  {
                console.log(response.data);
                console.log('new car added');
                setAdResponse(response.data);
            })
            .catch(error => {
                console.log(error.message);
            });

        console.log(manufacturer);
    }



    
   if(!token) {
        return <Link to ="/login">Log in if you want to access this page</Link>
   }

    return (
        <>
            <div className="create">
                <h1 className="is-size-4">Adauga o masina</h1>
                <form onSubmit={handleSubmit}>
                    <div className="car-form-content">
                        <div className="control car-input-content">
                            <label className="label">Marca:</label>
                            <input className="input is-info is-small" type="text" placeholder="Marca"
                                required
                                value={manufacturer}
                                onChange={(e) => setManufacturer(e.target.value)}
                            />
                        </div>
                        <div className="control car-input-content">
                            <label className="label">Modelul:</label>
                            <input className="input is-info is-small" type="text" placeholder="Modelul" 
                                required
                                value={model}
                                onChange={(e) => setModel(e.target.value)}/>
                        </div>
                    </div>
                    <div className="car-input-content-2">            
                        <label className="label">Anul fabricatiei</label>
                        <input className="input is-info is-small" type="date"      
                                required
                                value={dateOfFabrication}
                                onChange={(e) => setDateOfFabrication(e.target.value)}/>
                        <label className="label">Numarul de km</label>
                        <input className="input is-info is-small" type="text" placeholder="KM" 
                                required
                                value={km}
                                onChange={(e) => setKm(e.target.value)}/>
                        <label className="label">Pret</label>
                        <input className="input is-info is-small" type="text" placeholder="Pret" 
                                required
                                value={price}
                                onChange={(e) => setPrice(e.target.value)}/>
                        <label className="label">Putere</label>
                        <input className="input is-info is-small" type="text" placeholder="Putere" 
                                required
                                value={power}
                                onChange={(e) => setPower(e.target.value)}/>
                    </div>
                    <div className="select-form-content">
                        <label className="label">Selecteaza tipul transmisiei</label>
                        <div className = "control">
                            <div className="select is-info is-small">
                                <select
                                value={transmissionType}
                                onChange={(e) => setTransmissionType(e.target.value)}
                                >
                                    <option value="MANUAL">Manual</option>
                                    <option value="AUTOMATIC">Automata</option>
                                </select>
                            </div>
                        </div>
                        <label className="label">Selecteaza combustibilul</label>
                        <div className ="control">
                            <div className="select is-info is-small">
                                <select
                                value={engineType}
                                onChange={(e) => setEngineType(e.target.value)}
                                >
                                    <option value="DIESEL">Diesel</option>
                                    <option value="PETROL">Benzina</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    {!adResponse && <button className="submit-car__btn">Add Car</button>}
                    {adResponse && 
                    <div className='pb-6 mt-2 dropzone'>
                        <h2>Car added!</h2>
                        <h3>You may now upload photos of it below</h3>
                        <Dropzone adResponse = {adResponse}/>
                    </div>}
                </form>
                {/* <div className='dropzone'><Dropzone /></div> */}
                
            </div>
        </>
    )
}

export default AddCar
