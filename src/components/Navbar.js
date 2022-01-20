import React, { useState } from 'react';
import { Link } from "react-router-dom"
import logo from '../images/unibrow.png'
import styles from '../styling/navbar.module.css';
import '../styling/navbar.css'
import useToken from '../CustomHooks/useToken'
import { UserContext } from '../utils/UserContext';
import { useContext } from 'react';



const Navbar = ({ token, setToken }) => {
    const [click, setClick] = useState(false);
    const handleClick = () => setClick(!click);
    const {user, setUser} = useContext(UserContext);
    


    const handleLogOut = () => {
        setToken('');
        setUser(null);
    }




    // const { token, setToken } = useToken();

    


    return (
        <>
        <nav className={`navbar has-shadow is-info ${styles['navbar-bg']}`}>
        {/* <nav className={`navbar has-shadow is-info`}> */}
            <div className="navbar-brand">
                <Link to="/" className="navbar-item">
                    <img src={logo} alt="uniborw-logo" style={{maxHeight: "60px"}} className="py-2 px-2"/>
                </Link>
                <a onClick={handleClick} className="navbar-burger">
                    <span></span>
                    <span></span>
                    <span></span>
                </a>
            </div>

            <div className={`navbar-menu font-link ${click ? "is-active" : ""}`} id="nav-links">
                <div className="navbar-end"  style={{fontSize: "1.2rem", fontWeight:"600"}}>
                    <Link to="/" className="navbar-item">Acasa</Link>
                    <div className="navbar-item has-dropdown is-hoverable">
                        <a className="navbar-link">
                            Masini
                        </a>
                        <div className="navbar-dropdown" style={{backgroundColor: "rgb(255, 255, 230)"}}>
                            <Link to="/" className="navbar-item">
                                Autovehicule
                            </Link>
                            <Link to="/" className="navbar-item">
                                Autoutilitare
                            </Link>
                            <hr className="navbar-divider"/>
                            <div className="navbar-item">
                                Version 0.9.3
                            </div>
                        </div>
                    </div>
                    <Link to="/" className="navbar-item">Despre noi</Link>
                    <Link to="/" className="navbar-item">Echipa</Link>
                    <Link to="/" className="navbar-item">Contact</Link>
                    {/* <Link to="/add-car" className="navbar-item">Adauga +</Link> */}
                    {user && <Link to="/add-car" className="navbar-item">Adauga +</Link>}
                    {user && <Link to="/wishlist" className="navbar-item">Parcarea mea({user.wishlist.ads.length})</Link>}
                    {/* <Link to="/" className="navbar-item">{value}</Link> */}
                    {!user && <Link to="/login" className="navbar-item">Log in</Link>}
                    {/* {token && <Link to ="/" onClick={handleLogOut} className="navbar-item">Log out</Link>} */}
                    {user && 
                        <div className="navbar-item has-dropdown is-hoverable">
                            <a className="navbar-link">
                                {user.firstName}
                            </a>
                            <div className="navbar-dropdown" style={{backgroundColor: "rgb(255, 255, 230)", 
                                                                         position: "absolute", left: "-45px"}}>
                                    <Link to="/" className="navbar-item">
                                        Profil
                                    </Link>                                    
                                    <Link to="/my-ads" className="navbar-item">
                                        Anunturile mele
                                    </Link>
                                    <hr className="navbar-divider"/>
                                    <Link to ="/" onClick={handleLogOut} className="navbar-item">Log out</Link>
                            </div>
                        </div>}
                    {/* <Link to ="/" onClick={handleChange} className="navbar-item">{user}</Link> */}

                    
                </div>
            </div>
        </nav>
        </>
    );
}

export default Navbar;
