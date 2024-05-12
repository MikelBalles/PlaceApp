import React from 'react';

function VistaClientePpal() {
return(
<section className = "VistaClientePpal-container">
    <article className="VistaPpl-article">
        <div className = "VistaPpl-left">
            <button className = "btn-vistappl"> Gestionar mis reservas </button>
        </div>
        <div className = "VistaPpl-center">
            <button className = "btn-vistappl"> Modificar mis datos </button>
        </div>
        <div className = "VistaPpl-right">
            <button className = "btn-vistappl"> Cerrar Sesion </button>
        </div>
    </article>
     <header className="reserva-header">
         <h2>¿Quieres realizar una reserva?</h2>
     </header>



</section>



    )
}

export default VistaClientePpal;
