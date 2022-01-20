import React, { useState, useContext, useEffect } from 'react';
import { useHistory, Link } from "react-router-dom";
import PropTypes from 'prop-types';
import axios from 'axios';
import './login.css';
import Spinner from '../../utils/Spinner';
import { UserContext } from '../../utils/UserContext';





const Login = ({token, setToken}) => {
    const [userName, setUserName] = useState();
    const [password, setPassword] = useState();
    const [badCredentials, setBadCredentials] = useState(false);
    const [isPending, setIsPending] = useState();
    const[ logInToken, setLogInToken] = useState();
    const {user, setUser} = useContext(UserContext);
    let history = useHistory();


    
  
    const handleSubmit = (e) => {
        setIsPending(true);
        e.preventDefault();
        const credentials = { userName, password }
        axios.post('http://localhost:8080/api/v1/auth/login', credentials)
            .then(response =>  {
                console.log(response.data);
                console.log('logged in');
                setToken(response.data);
                setLogInToken(response.data);
                history.push("/");
                // setIsPending(false);
            })
            .then( data => {
                console.log(data);
                console.log(logInToken + "plm");
            })
            .catch(error => {
                console.log(error.message);
                setBadCredentials(true);
                setIsPending(false);
            });

        //   const token = await loginUser({
        //     userName,
        //     password
        //   });
        //   setToken(token);
        //   if(token.token){
        //       console.log(token.token);
        //       setToken(token);
        //       history.push("/");
        //   } else {
        //       setBadCredentials(true);
        //   }
    }


  
    return(
      <div className="login-wrapper">
        <h1>Please Log In</h1>
        <form className="login-form" onSubmit={handleSubmit}>
          <label>
            <p>Email</p>
            <input type="email" required onChange={e => setUserName(e.target.value)} />
          </label>
          <label>
            <p>Password</p>
            <input type="password" required onChange={e => setPassword(e.target.value)} />
          </label>
              {badCredentials && <p className="badCredentials">Incorrect email or password, please try again</p>}
          <div>
            <button type="submit">Submit</button>
          </div>
          {isPending && <Spinner />}
          <p className="register-here">Don't have an account yet? <Link to ='/register'>Click here to sign up</Link></p>
        </form>
        {/* {token && console.log(token)} */}
      </div>
    )
}

export default Login


Login.propTypes = {
  setToken: PropTypes.func.isRequired
};