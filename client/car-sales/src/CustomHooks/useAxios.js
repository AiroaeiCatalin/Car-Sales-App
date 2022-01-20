import { useState, useEffect } from "react";
import axios from "axios";

const useAxios = (url) => {
  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);
  const [error, setError] = useState(null);


  useEffect(() => {
    const abortCont = new AbortController();

    if (url !== null) {
      axios.get(url, { signal: abortCont.signal })
      .then(response => {
        if (response.status !== 200)
          throw Error("Could not fetch data from this resource");
        return response.data;
      })
      .then(data => {
        setData(data);
        setIsPending(false);
        setError(null);
      })
      .catch(err => {
        if (err.message !== "canceled") {
          setIsPending(false);
          setError(err.message);
        }
      })

      return () => abortCont.abort();
    }
  }, [url])

  return { data, isPending, error };
}


export default useAxios;