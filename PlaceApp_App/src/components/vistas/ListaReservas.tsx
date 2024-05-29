import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { reservaUsuarioClienteDto, sesionIniciada } from '../../datos/tipos';
import { URL_PETICION_BBDD } from '../../datos/constantes';
import TarjetaDetalle from '../TarjetaDetalle';
import Volver from '../Volver';
import VistaVacia from '../modales/VistaVacia';

interface ListaReservasProps {
    sesion: sesionIniciada | undefined;
}

const ListaReservas: React.FC<ListaReservasProps> = ({sesion}) => {

    const ordenarReservas = (reserva: reservaUsuarioClienteDto[]): reservaUsuarioClienteDto[] => {
        return reserva.sort((a, b) => new Date(a.fechaInicio).getTime() - new Date(b.fechaInicio).getTime());
    };


    const navigate = useNavigate();
    const idUsuario = sesion?.username;

    const [reservas, setReservas] = useState<reservaUsuarioClienteDto[] | null>(null);

    useEffect(() => {
        fetch(`${URL_PETICION_BBDD}/rest/cliente/reserva/${idUsuario}`)
            .then(response => response.json())
            .then(data => {
                setReservas(ordenarReservas(data));                
            })
            .catch(error => {
                console.error('Error:', error);
                return (
                    <VistaVacia irAtras mensaje='Tenemos problemas recuperando tus reservas. Vuelve a intentarlo más adelante'></VistaVacia>
                )
            }
            );
    }, [idUsuario]);
    
    if (!sesion) {
        return (
            <div>
                <h1>Debes iniciar sesión para ver los espacios</h1>
                <button onClick={() => navigate('/login')}>Iniciar sesión</button>
            </div>
        );
    }

    if (!reservas) {
        return (
            <VistaVacia irAtras mensaje='Tenemos problemas recuperando tus reservas. Vuelve a intentarlo más adelante'></VistaVacia>
        );
    } else if (reservas.length === 0) {
        return (
            <>
            <Volver ruta='/cliente'></Volver>
            <VistaVacia mensaje='Vaya, parece que aún no tienes ninguna reserva'></VistaVacia>
            </>
        )
    } else {
        return (

            <section className='contenedor-reservas contenedor-flex-ver'>
                <Volver ruta='/cliente'></Volver>
                <header className='subtitulo'>Estas son todas tus reservas:</header>
                    <div className='contenedor-items contenedor-flex-hor'>
                        {reservas.map(res => (
                            <TarjetaDetalle key={res.idReserva} objeto={res}>
                            </TarjetaDetalle>
                        ))}
                    </div>
                </section>
        )
    }
    //Petición a la base de datos para obtener los tipos y subtipos
}
export default ListaReservas;