import React from 'react';
import { useNavigate } from 'react-router-dom';

interface TarjetaPerfilHomeProps {
    titulo: string;
    imagen: string;
    texto: string;
    ventajas: string[];
    ruta: string;
}

const TarjetaPerfilHome: React.FC<TarjetaPerfilHomeProps> = ({ titulo, imagen, texto, ventajas, ruta }) => {
    const navigate = useNavigate();
    return (
        <article className='item-perfil contenedor-flex-ver col-flex'>
            <div className='contenido-texto contenedor-flex-ver'>
                <h3 className='titulo-2'>{titulo}</h3>
                <img src={imagen} alt=''/>
                <p>{texto}</p>
                <ul className='lista-ventajas'>
                    {ventajas.map((ventaja, index) => <li key={index}>{ventaja}</li>)}
                </ul>
            </div>
            <button className='btn-primary btn-relleno' onClick={() => {navigate(ruta)}}>Quiero crear una cuenta</button>
        </article>
    );
}

export default TarjetaPerfilHome;