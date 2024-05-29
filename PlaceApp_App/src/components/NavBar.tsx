import React from 'react';
import logo_PlaceApp from '../assets/logo_PlaceApp.svg';
import iconoPlaceApp from '../assets/icono_PlaceApp.svg';
import { sesionIniciada } from '../datos/tipos';
import { Link, useLocation } from 'react-router-dom';
import { HashLink } from 'react-router-hash-link';
import { RUTAS } from '../datos/rutas';

interface NavbarProps {
    sesionIniciada: sesionIniciada | undefined;
}

const Navbar: React.FC<NavbarProps> = ({ sesionIniciada }) => {
    const location = useLocation();

    const esVistaCliente = location.pathname === RUTAS.cliente;

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
                        {!esVistaCliente && (
                            <Link to={RUTAS.cliente} className='subtitulo-login'>Accede a tu área personal</Link>
                        )}
                    </div>
                ) : (
                    <div className='links-cuenta'>
                        <Link className="btn-primary btn-borde" to={RUTAS.login}>Iniciar sesión</Link>
                        <Link className="btn-primary btn-relleno" to={RUTAS.registro}>Registrarse</Link>
                    </div>
                )}
            </nav>
        </div>
    );
};

export default Navbar;