import React from 'react'
import Card from './Card'
import './carspage.css'
import formBg from '../../images/road1.jpg'
import useAxios from '../../CustomHooks/useAxios'
import { useState, useContext, useEffect } from 'react'
import { UserContext } from '../../utils/UserContext'
// import useToken from '../../CustomHooks/useToken'
import Spinner from '../../utils/Spinner'


const CarsPage = () => {


    const [selectedManufacturer, setSelectedManufacturer] = useState('All');
    const [selectedModel, setSelectedModel] = useState('');
    const{data: ads, isPending, error} = useAxios('http://localhost:8080/api/v1/ad');
    const{data: manufacturers} = useAxios('http://localhost:8080/api/v1/car/manufacturers');
    const{data: models} = useAxios(`http://localhost:8080/api/v1/car/${selectedManufacturer}/models`);
    // const{token} = useToken();
    const {user, setUser} = useContext(UserContext);
    // const [priceLow, setPriceLow] = useState('');
    // const [priceHigh, setPriceHigh] = useState('');
    
    // console.log(user);

    // useEffect(() => {
        // if(cars!= null){
        // console.log(cars);
        // setCars1(cars);
        // console.log(cars);
        // cars.map(car => {
        //                  console.log(car.car);
        //                  const{masina} = car.car;
        //                  console.log("abc");
        //                  setCars1({...cars1, masina});
        //                  console.log(cars1);
        //         });
        // }
        // cars.map(car => {
        //     console.log(car);
        // })
    // }, [cars])





     const getAllCars = () => {

        if(selectedManufacturer === 'All'){
            return ads.map(ad => (<div className="car-card" key={ad.id}><Card ad={ad} /></div>)) 
        } else if(selectedManufacturer !== 'All' && selectedModel === 'All') {
            return ads.filter(ad => ad.car.manufacturer === selectedManufacturer).map(filteredAd => (<div className="car-card" key={filteredAd.id}><Card ad={filteredAd} /></div>))
        } else {
            return ads.filter(ad => ad.car.manufacturer === selectedManufacturer && ad.car.model === selectedModel).map(filteredAd => (<div className="car-card" key={filteredAd.id}><Card ad={filteredAd} /></div>))
        }
    }



    return (
        <>
            {/* <div className="form-bg__img">
                <img src={formBg} alt="" />
            </div> */}
            {/* <h1>{token}</h1> */}
            {/* <div>{user.firstName} </div> */}
            <div className="main-container">
                <div className="form-bg__img">
                    <img src={formBg} alt="" />
                    <div className="form-content">
                        <h1 className="is-size-4">Cauta o masina</h1>
                        <div className="select-form-content">
                        <label className="label">Selecteaza marca</label>
                            <div className="select is-info is-small">
                                <select
                                value={selectedManufacturer}
                                onChange={(e) => {setSelectedManufacturer(e.target.value); 
                                                  setSelectedModel('All');}}
                                >
                                    <option value="All">All</option>
                                    {manufacturers && manufacturers.map((manufacturer, i) => (<option key ={i}>{manufacturer}</option>))}
                                </select>
                            </div>
                            <label className="label">Selecteaza modelul</label>
                            <div className="select is-info is-small">
                                <select
                                    value={selectedModel}
                                    onChange={(e) => setSelectedModel(e.target.value)}
                                >
                                    {selectedManufacturer === "All" && <option>Please choose a manufactuer first</option>}
                                    {selectedManufacturer !== 'All' && <option>All</option> }
                                    {selectedManufacturer !== "All" && models.map(model => (<option key ={model}>{model}</option>))}
                                </select>
                            </div>
                        </div>
                        <label className="label">Selecteaza intervalul de pret</label>
                        <div className="price-form-content">
                            <input className="input is-info is-small" type="text" placeholder="Pret de la">
                            </input>
                            <input className="input is-info is-small" type="text" placeholder="Pret pana la">
                            </input>
                        </div>
                    </div>
                    {/* <div className="select form-content"> */}
                        {/* <div className="ceva"> */}

                        {/* </div> */}
                    {/* </div> */}
                </div>
                {/* <div className="select blabla" style={{backgroundImage: `url(${formBg})`}}>
                        <select>
                            <option>Select dropdown</option>
                            <option>With options</option>
                        </select>
                    </div> */}
                { error && <div className="is-size-3">{ error }</div> }
                <h1 className="is-size-3">Vezi ultimele anunturi</h1>
                {/* {isPending && (<div className="is-size-3">Loading...</div>)} */}
                {isPending && <Spinner />}
                {ads && getAllCars()}
            </div>
        </>
    )
}

export default CarsPage
