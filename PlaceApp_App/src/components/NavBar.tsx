import React from 'react';
import logo_PlaceApp from '../assets/logo_PlaceApp.svg';
import { sesionIniciada } from '../datos/tipos';
import { Link } from 'react-router-dom';

interface NavbarProps {
    sesionIniciada: sesionIniciada | undefined;
    mostrarEnlaceAreaPriv?: boolean;
}

const Navbar: React.FC<NavbarProps> = ({ sesionIniciada, mostrarEnlaceAreaPriv=true} ) => {
    return (
        <div className='navbar-bg'>
            <nav className='navbar'>
                <div className='logo-navbar'>
                    <Link to='/'>
                        <img src={logo_PlaceApp} alt='logo' />
                    </Link>
                </div>
                <div className="links-acceso">
                    <a className="btn-primary btn-sin-relleno" href="#">Cómo funciona</a>
                    <a className='btn-primary btn-sin-relleno' href="#">Preguntas frecuentes</a>
                </div>

                {sesionIniciada ? (
                    <div className='acceso-area-privada'>
                        <p className='bienvenida-login'> Bienvenido, {sesionIniciada.nombreUsuario} <span className='enfasis'> {sesionIniciada.nombrePerfil.slice(5)}</span> </p>
                       { mostrarEnlaceAreaPriv && <p className='subtitulo-login'>Acceder a mi área personal</p>}
                    </div>
                ) : (
                    <div className='links-cuenta'>
                        <Link className="btn-primary btn-borde" to='/login'>Iniciar sesión</Link>
                        <a className="btn-primary btn-relleno" href='#'>Registrarse</a>
                    </div>
                )}
            </nav>
        </div>
    );
};

export default Navbar;