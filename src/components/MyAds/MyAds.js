import React from 'react'
import { useContext } from 'react';
import { UserContext } from '../../utils/UserContext';
import Card from '../ProductsPage/Card';

const MyAds = () => {
    const {user, setUser} = useContext(UserContext);
    const ads = user.ads



    return (
        <div>
            <h1 className="is-size-3 mt-5 mx-5">Anunturile mele</h1>
            {ads && ads.map( ad => (<div className="car-card" key={ad.id}><Card ad={ad} wishlistOrMyAds={true}/></div>)) }
        </div>
    )
}


export default MyAds
