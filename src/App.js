import './styling/App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import NotFound from './components/NotFound';
import Navbar from './components/Navbar';
import CarDetails from './components/CarDetails'
import CarsPage from './components/ProductsPage/CarsPage'
import AddCar from './components/AddCar/AddCar';
import useToken from './CustomHooks/useToken';
import Login from './components/Login/Login'
import { useState, useEffect, useMemo } from 'react'
import Registration from './components/Registration/Registration';
import { UserContext } from './utils/UserContext';
import axios from 'axios';
import Wishlist from './components/Wishlist/Wishlist';
import MyAds from './components/MyAds/MyAds';


// export const UserContext = React.createContext();

function App() {

  const { token, setToken } = useToken();

  const [user, setUser] = useState(null);

  // const value = useMemo(() => ({user, setUser, getUser}), [user, setUser, getUser]);

  const getUser = () => {
    axios.get('http://localhost:8080/api/v1/auth/userinfo', 
    {
        headers: {
        // "Access-Control-Allow-Origin" : "*",
        // "Content-type": "Application/json",
        "Authorization": `Bearer ${token}`
    }   
    })
    .then(response => {
      if (response.status !== 200)
        throw Error("Could not fetch data from this resource");
      return response.data;
    })
    .then(data => {
      console.log(data);  
      setUser(data);
    //   setData(data);
    //   setIsPending(false);
    //   setError(null);
    })
    .catch(err => {
      if (err.message !== "canceled") {
        // setIsPending(false);
        // setError(err.message);
        console.log(err.message);
      }
    })
  }

  const value = useMemo(() => ({user, setUser, getUser}), [user, setUser, getUser]);
  

  useEffect(() => {
    if(token){

      getUser();
    }
  }, [token])

  return (
    <UserContext.Provider value={value}>
      <Router>
      <div className="App">
        <Navbar token={token} setToken={setToken}/>
        <div className="content">
          <Switch>
            <Route exact path="/">
              <CarsPage token={token}/>
            </Route> 
            <Route path="/add-car">
              <AddCar />
            </Route>
            <Route path="/login">
              <Login token={token} setToken={setToken}/>
            </Route>          
            <Route path="/register">
              <Registration/>
            </Route>       
            <Route path="/wishlist">
              <Wishlist/>
            </Route>              
            <Route path="/my-ads">
              <MyAds/>
            </Route>       
            {/* <Route path="/car/:id">
              <CarDetails />
            </Route>  */}
            {/* <Route exact path="/">
              <Home />
            </Route>
            <Route path="/add">
              <Add />
            </Route>
            <Route path="/car/:id">
              <CarDetails />
            </Route> */}
            <Route path="*">
              <NotFound />
            </Route>
          </Switch>
        </div>
      </div>
    </Router>
  </UserContext.Provider>
  );
}



export default App;
