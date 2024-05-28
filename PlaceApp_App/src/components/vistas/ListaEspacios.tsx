import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { espacioClienteDto, sesionIniciada } from '../../datos/tipos';
import { URL_PETICION_BBDD } from '../../datos/constantes';
import TarjetaDetalle from '../TarjetaDetalle';
import Volver from '../Volver';
import VistaVacia from '../modales/VistaVacia';

interface ListaEspaciosProps {
    sesion: sesionIniciada | undefined;
}

const ListaEspacios: React.FC<ListaEspaciosProps> = ({sesion}) => {
    const navigate = useNavigate();
    const {idSubtipo, idProv} = useParams();

    const [espacios, setEspacios] = useState<espacioClienteDto[] | null>(null);

    useEffect(() => {
        fetch(`${URL_PETICION_BBDD}/rest/comun/espacio/subtipo/provincia/${idSubtipo}/${idProv}`)
            .then(response => response.json())
            .then(data => {
                setEspacios(data);
                console.log(data);
                
            })
            .catch(error => console.error('Error:', error));
    }, [idSubtipo, idProv]);

    if (!sesion) {
        return (
            <div>
                <h1>Debes iniciar sesión para ver los espacios</h1>
                <button onClick={() => navigate('/login')}>Iniciar sesión</button>
            </div>
        );
    }

    if (!espacios) {
        return (
            <h1>Cargando espacios...</h1>
        );
    } else if (espacios.length === 0) {
        return (
            <>
            <Volver ruta='/cliente'></Volver>
            <VistaVacia mensaje='Vaya, parece que aún no tenemos ningún espacio de ese tipo en este lugar'></VistaVacia>
            </>
        )
    } else {
        return (

            <section className='contenedor-espacios contenedor-flex-ver'>
                <Volver ruta='/cliente'></Volver>
                <header className='subtitulo'>Estos son los espacios disponibles:</header>
                    {espacios.map(espacio => (
                        <TarjetaDetalle key={espacio.idEspacio} objeto={espacio}>
                        </TarjetaDetalle>
                    ))}
                </section>
        )
    }
    //Petición a la base de datos para obtener los tipos y subtipos
}
export default ListaEspacios;