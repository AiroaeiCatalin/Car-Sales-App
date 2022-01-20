import React from 'react'
import { useContext } from 'react';
import { UserContext } from '../../utils/UserContext';
import Card from '../ProductsPage/Card';

const Wishlist = () => {

    const {user, setUser} = useContext(UserContext);
    const wishlist = user.wishlist
    const ads = wishlist.ads
    // console.log(user.wishlist)


    return (
        <div>
            <h1 className="is-size-3 mt-5 mx-5">Wishlistul meu</h1>
            {wishlist && ads.map( ad => (<div className="car-card" key={ad.id}><Card ad={ad} wishlistOrMyAds={true}/></div>)) }
        </div>
    )
}

export default Wishlist
