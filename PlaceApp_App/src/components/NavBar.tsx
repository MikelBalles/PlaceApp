import React from 'react';
import logo_PlaceApp from '../assets/logo_PlaceApp.svg';
import iconoPlaceApp from '../assets/icono_PlaceApp.svg';
import { sesionIniciada } from '../datos/tipos';
import { Link } from 'react-router-dom';
import { HashLink } from 'react-router-hash-link';
import { RUTAS } from '../datos/rutas';

interface NavbarProps {
    sesionIniciada: sesionIniciada | undefined;
    mostrarEnlaceAreaPriv?: boolean;
}

const Navbar: React.FC<NavbarProps> = ({ sesionIniciada, mostrarEnlaceAreaPriv=true} ) => {
    return (
        <div className='navbar-bg'>
            <nav className='navbar'>
                <div className='cont-logo-navbar'>
                    <Link to='/'>
                        <img className='logo-navbar' src={logo_PlaceApp} alt='Ir a home' />
                        <img className='icono-navbar' src={iconoPlaceApp} alt='Ir a home' />
                    </Link>
                </div>
                <div className="links-acceso">
                    <HashLink className="btn-primary btn-sin-relleno" smooth to={RUTAS.comoFunciona}>Cómo funciona</HashLink>
                    <HashLink className='btn-primary btn-sin-relleno' smooth to={RUTAS.faqs}>Preguntas frecuentes</HashLink>
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