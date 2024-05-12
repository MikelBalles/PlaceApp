import React from 'react';
import { sesionIniciada } from '../datos/tipos';

interface VistaClientePpalProps {
    sesion : sesionIniciada | undefined;
    cerrarSesion: () => void;
}

const VistaClientePpal:  React.FC<VistaClientePpalProps> = ( {sesion , cerrarSesion} ) => {

    if (!sesion) {
        console.log('No hay sesion iniciada');
        return null;
    }

return (
<section className = "VistaClientePpal-container">
    <article className="VistaPpl-article">
        <button className = "btn-primary btn-borde"> Gestionar mis reservas </button>
        <button className = "btn-primary btn-borde"> Modificar mis datos </button>
        <button className = "btn-primary btn-borde" onClick={cerrarSesion}> Cerrar Sesion </button>
    </article>
     <header className="reserva-header">
         <h2>¿Quieres realizar una reserva?</h2>
     </header>
</section>

    )
}

export default VistaClientePpal;
