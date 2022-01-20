import React from 'react'
import carImg from '../../images/skoda-vrs.jpeg'
import { Link } from "react-router-dom"
import './card.css'
import { FaRegCalendarAlt } from "react-icons/fa";
import { FaRoad } from "react-icons/fa";
import { RiGasStationFill } from "react-icons/ri";
import { BsSpeedometer2 } from "react-icons/bs";
import { GiGearStickPattern } from "react-icons/gi";
import { UserContext } from '../../utils/UserContext'
import { useContext } from 'react';
import useToken from '../../CustomHooks/useToken';
import axios from 'axios';
// import formBg from '../../images/road1.jpg'



//Oare puteam functia asta de handlesubmit de aici sa o fac in alta parte si doar sa o trimit ca prop sau cv?
//Daca e ok sa trimit un obiect asa? ca imi creez un obiect nou in backend ca sa pot handleui asta.. puteam de fapt prin token
//functie nuc ustomhook declarat global? daca puteam sa o pun in usercontext si p aia sau cv sau sa o trimit ca props? o sa o bag daca mai trb sa o fol pe undeva, daca nu n are rost
//sa ntreb cum as fi putut implementa microservicii in proj meu, gen cum ar fi trb sa structurez, unde ar fi trb sa bag codu,a r fi trb sa am gen aplicatii separate si sa le dau drumu la toate pe rand ?

const Card = ({ad, wishlistOrMyAds}) => {
    const car = ad.car;
    const {user, setUser, getUser} = useContext(UserContext);
    // const userName = user.userName;
    const adId = ad.id
    // console.log(user);
    const { token, setToken } = useToken();



    const handleClick = async e => {
        // const {user, setUser} = useContext(UserContext);
        const userName = user.userName;
        // console.log(adId);
        e.preventDefault();
        const wishlistAd = { adId, userName};
        // console.log(wishlistAd);
        axios.post('http://localhost:8080/api/v1/wishlist', wishlistAd)
            .then(response =>  {
                console.log(response);
                console.log('added to wishlist');
                getUser();
                // axios.get('http://localhost:8080/api/v1/auth/userinfo', 
                //         {
                //             headers: {
                //             // "Access-Control-Allow-Origin" : "*",
                //             // "Content-type": "Application/json",
                //             "Authorization": `Bearer ${token}`
                //         }   
                //     })
                //     .then(response => {
                //         if (response.status !== 200)
                //             throw Error("Could not fetch data from this resource");
                //         return response.data;
                //     })
                //     .then(data => {
                //         console.log(data);  
                //         setUser(data);
                //     //   setData(data);
                //     //   setIsPending(false);
                //     //   setError(null);
                //     })
                //     .catch(err => {
                //         if (err.message !== "canceled") {
                //             // setIsPending(false);
                //             // setError(err.message);
                //             console.log(err.message);
                //         }
                //     })

            })
            .catch(error => {
                console.log(error.message);
            })    
        // console.log(manufacturer);
    }

        ////cacaat

    return (
        <div className="car-item">
            <div className="car-item__img">
                <img src={carImg} alt="" />
            </div>
            <div className="car-item__info">
                <h1 className="car-item__title">{car.manufacturer} {car.model}</h1>
                <div className="car-item__price">
                    <span style={{color:'#1B1B1B'}}>Pret: {car.price}€</span>
                </div>
                
                <div className="car-details__container">
                    <div><FaRoad /> {car.km} km</div>
                    <div><FaRegCalendarAlt /> {car.dateOfFabrication}</div>
                    <div><RiGasStationFill />{car.engineType}</div>
                    <div><GiGearStickPattern />{car.transmissionType}</div>
                    <div><BsSpeedometer2 /> {car.power} bhp </div>
                    <div>Sedan</div>
                </div>
                <p className="car-item__text">Lorem ipsum dolor sit amet consectetur adipisicing elit. Id doloremque ipsa corporis sapiente alias laboriosam tempora. Exercitationem, quasi! Dolorem voluptate eveniet esse minima rem deserunt cum rerum, ex adipisci numquam.</p>
                {!wishlistOrMyAds && <Link to = "" onClick={handleClick} className="car-item__cta">Parcheaza o masina</Link>}
            </div>
        </div>
    )
}

export default Card
