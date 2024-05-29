import React from 'react';
import ilustracion from '../../assets/empty_illustration.svg';
import Volver from '../Volver';
import { IR_ATRAS } from '../../datos/constantes';

/**
 * @param irAtras Solo es necesaria si se quiere mostrar un botón de volver
 */
interface Props {
    mensaje: string;
    irAtras?: boolean;
}

const VistaVacia: React.FC<Props> = ({mensaje, irAtras}) => {
    return (
        <section className='contenedor-vista-vacia'>
            {irAtras && 
            <Volver ruta={IR_ATRAS}></Volver>
            }
            <h1 className='subtitulo'>{mensaje}</h1>
            <img src={ilustracion} alt=''></img>
        </section>
    );
};

export default VistaVacia;