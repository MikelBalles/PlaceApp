import React from 'react';
import { sesionIniciada } from '../datos/tipos';
import Deportivo from '../assets/Deportivo.svg';
import Salas from   '../assets/Salas_privadas.svg';
import Gastronomia from '../assets/Gastronomia.svg';
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
     <p className="subtitulo principal">Selecciona el tipo de evento que quieres reservar</p>
     <article className="VistaPpl-article">
     
        <button className = "btn-reservar "> <img src={Deportivo} alt='deportivo' />Deportivo  </button>
        <button className = "btn-reservar "> <img src={Salas} alt='Salas' />Salas Privadas  </button>
        <button className = "btn-reservar "> <img src={Gastronomia} alt='Gastronomia'/> Gastronomia </button>
    </article>
        <p className="subtitulo principal">¿En que estas pensando exactamente?</p>

     <article className="VistaPpl-article">
     
        <button className = "btn-reservar "> <img src={Deportivo} alt='deportivo' />Deportivo  </button>
        <button className = "btn-reservar "> <img src={Salas} alt='Salas' />Salas Privadas  </button>
        <button className = "btn-reservar "> <img src={Gastronomia} alt='Gastronomia'/> Gastronomia </button>


        
    </article>
</section>

    )
}

export default VistaClientePpal;
