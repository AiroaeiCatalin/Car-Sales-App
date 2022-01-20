import axios from 'axios';
import React from 'react'
import { useCallback } from 'react';
import { useDropzone } from 'react-dropzone';

const Dropzone = ({adResponse}) => {
    //Aici cred ca pot csa bag si pe rand tot cate o poza, doar ca trb din acceptedFiles sa le bag intr un alt state si tot adaug la stateu ala cand dau drag and drop, pana cand dau ok si atunci imi face post si ma duce back p homepage
    
    console.log(adResponse);
    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        // console.log(file);
        console.log( acceptedFiles);

        const formData = new FormData();
        formData.append("file", file);

        console.log(`http://localhost:8080/api/v1/ad/${adResponse}/image/upload`)
    
        axios.post(`http://localhost:8080/api/v1/ad/${adResponse}/image/upload`, formData,
            {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            }
        ).then(() => {
            console.log("File uploaded successfully");
        }).catch(err => {
            console.log(err);
        })
    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

    return (
    <div {...getRootProps()}>
        <input {...getInputProps()} />
        {
        isDragActive ?
            <p>Drop the images here ...</p> :
            <p>Drag 'n' drop images here, or click to select images</p>
        }
    </div>
    )
}

export default Dropzone
