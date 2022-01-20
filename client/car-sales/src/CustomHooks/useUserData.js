import { useState, useEffect } from "react";
import axios from "axios";

const useUserData = (token) => {
  const [data, setData] = useState(null);



  useEffect(() => {
    if(token){


        console.log(token);
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
          setData(data);
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
  }, [token])

  return { data };
}


export default useUserData;