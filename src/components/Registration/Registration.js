import axios from 'axios';
import React from 'react'
import { useState } from "react";
import useToken from '../../CustomHooks/useToken';
import { Link } from 'react-router-dom';
import './registration.css'
import Spinner from '../../utils/Spinner';

const Registration = () => {

    const [email, setEmail] = useState();
    const [firstName, setFirstName] = useState();
    const [lastName, setLastName] = useState();
    const [password, setPassword] = useState();
    const [confirmedPassword, setConfirmedPassword] = useState();
    const [accountAlreadyTaken, setAccountAlreadyTaken] = useState();
    const [verifyEmail, setVerifyEmail] = useState();
    const [isPending, setIsPending] = useState();
    const {token} = useToken();


    const checkIfPasswordsMatch = () => {
        if(password === confirmedPassword){
            return true;
        } else {
            return false;
        }
    }


    const handleSubmit = (e)=> {
        e.preventDefault();
        setIsPending(true);
        if(checkIfPasswordsMatch() === true ){
            const user = { firstName, lastName, email, password };
            axios.post('http://localhost:8080/api/v1/registration', user)
                .then(response =>  {
                    console.log(response);
                    console.log('new user added');
                    setVerifyEmail(true);
                    setIsPending(false);
                })
                .catch(error => {
                    console.log(error.message.message);
                    setAccountAlreadyTaken(true);
                    setIsPending(false);
                });
        }
    }

    if(token) {
        return( 
            <div>
                    <p>You are already logged in</p>
                    <Link to ="/"> Go home </Link> 
            </div>
        )
   }

    return (<>
            <div className="registration-wrapper">
              <h1>Create an account</h1>
              <form className="registration-form" onSubmit={handleSubmit}>
                <label>
                  <p>Email</p>
                  <input type="email" required onChange={e => {setEmail(e.target.value); 
                                                               setAccountAlreadyTaken(null);
                                                               setVerifyEmail(null);
                                                               }} />
                </label>
                <label>
                  <p>First Name</p>
                  <input type="text" required onChange={e => setFirstName(e.target.value)} />
                </label>   
                <label>
                  <p>Last Name</p>
                  <input type="text" required onChange={e => setLastName(e.target.value)} />
                </label>
                <label>
                  <p>Password</p>
                  <input type="password" required onChange={e => setPassword(e.target.value)} />
                </label>
                <label>
                  <p>Confirm password </p>
                  <input type="password" required onChange={e => setConfirmedPassword(e.target.value)} />
                </label>
                    {!checkIfPasswordsMatch() && <p className="bad-password">Passwords don't match, please try again</p>}
                    {accountAlreadyTaken && <p className="account-already-taken">Account already taken, please choose another email</p>}
                    {verifyEmail && <p className="account-already-taken">Registration successful, please check your e-mail in order to verify your account. <Link to ="/">Go home</Link></p>}
                    {isPending && <Spinner/>}
                <div>
                  <button type="submit">Submit</button>
                </div>
              </form>
            </div>
          </>
    )
}

export default Registration
