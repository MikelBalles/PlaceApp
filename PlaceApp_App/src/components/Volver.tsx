import React from 'react';
import iconoVolver from '../assets/Volver.svg';
import { useNavigate } from 'react-router-dom';
import { IR_ATRAS } from '../datos/constantes';

interface VolverProps {
    ruta: string;
}

const Volver: React.FC<VolverProps> = ({ ruta }) => {
    const navigate = useNavigate();

    //Utilizamos una función propia porque el elemento LINK
    //añade la ruta sobre la URL actual, y queremos que se
    //sustituya por completo
    const handleOnClick = (event: React.MouseEvent) => {
        event.preventDefault();
        if (ruta === IR_ATRAS) {
            navigate(-1);
        } else {
        navigate(ruta, { replace: true });
        }
    }

    return (
        <div className='nav-volver' onClick={handleOnClick}>
            <img src={iconoVolver} alt="Volver" />
            <a href={ruta}>Volver</a>
        </div>
    );
};

export default Volver;