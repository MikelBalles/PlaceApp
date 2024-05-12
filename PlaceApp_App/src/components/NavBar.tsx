import React from 'react';
import logo_PlaceApp from '../assets/logo_PlaceApp.svg';
import { sesionIniciada } from '../datos/tipos';

interface NavbarProps {
    sesionIniciada: sesionIniciada | undefined;
    mostrarEnlaceAreaPriv?: boolean;
}

const Navbar: React.FC<NavbarProps> = ({ sesionIniciada, mostrarEnlaceAreaPriv=true} ) => {
    return (
        <div className='navbar-bg'>
            <nav className='navbar'>
                <div className='logo-navbar'>
                    <a href='#'>
                        <img src={logo_PlaceApp} alt='logo' />
                    </a>
                </div>
                <div className="links-acceso">
                    <a className="btn-primary btn-sin-relleno" href="#">Cómo funciona</a>
                    <a className='btn-primary btn-sin-relleno' href="#">Preguntas frecuentes</a>
                </div>

                {sesionIniciada ? (
                    <div className='acceso-area-privada'>
                        <p> Bienvenido, {sesionIniciada.nombreUsuario} <span className='enfasis'> {sesionIniciada.nombrePerfil}</span> </p>
                       { mostrarEnlaceAreaPriv && <p>Acceder a mi área personal</p>}
                    </div>
                ) : (
                    <div className='links-cuenta'>
                        <a className="btn-primary btn-borde" href='#'>Iniciar sesión</a>
                        <a className="btn-primary btn-relleno" href='#'>Registrarse</a>
                    </div>
                )}
            </nav>
        </div>
    );
};

export default Navbar;