import React from 'react';
import logo_PlaceApp from '../assets/logo_PlaceApp.svg';

interface NavbarProps {
    sesionIniciada: boolean;
    nombreEspacio?: string;
}

const Navbar: React.FC<NavbarProps> = ({ sesionIniciada = false, nombreEspacio = 'Nombre del Espacio' }) => {
    return (
        <div className='navbar-bg'>
            <nav className='navbar'>
                <div className='logo-navbar'>
                    <a href='#'>
                        <img src={logo_PlaceApp} alt='logo' />
                    </a>
                </div>
                <div className="links-acceso">
                    <p>{nombreEspacio}</p>
                    <a className="btn-primary btn-sin-relleno" href="#">Cómo funciona</a>
                    <a className='btn-primary btn-sin-relleno' href="#">Preguntas frecuentes</a>
                </div>

                {sesionIniciada ? (
                    <div style={{ width: '100px', height: '100px', background: 'red' }}></div>
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