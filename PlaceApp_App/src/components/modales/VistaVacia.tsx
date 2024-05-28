import React from 'react';
import ilustracion from '../../assets/empty_illustration.svg';

interface Props {
    mensaje: string;
}

const VistaVacia: React.FC<Props> = ({mensaje}) => {
    return (
        <section className='contenedor-vista-vacia'>
            <h1 className='subtitulo'>{mensaje}</h1>
            <img src={ilustracion} alt=''></img>
        </section>
    );
};

export default VistaVacia;