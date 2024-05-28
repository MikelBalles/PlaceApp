import React from 'react';
import { espacioClienteDto, reservaUsuarioClienteDto } from '../datos/tipos';
import { obtenerImagenSubtipo } from '../logica/iconosTipos';
import { useNavigate } from 'react-router-dom';

function esTipoEspacio(objeto: espacioClienteDto | reservaUsuarioClienteDto): objeto is espacioClienteDto {
    // Comprueba si el objeto tiene las propiedades específicas de un Espacio
    return 'idEspacio' in objeto;
}

function esTipoReserva(objeto: espacioClienteDto | reservaUsuarioClienteDto): objeto is reservaUsuarioClienteDto {
    // Comprueba si el objeto tiene las propiedades específicas de una Reserva
    return 'idReserva' in objeto;
}

// Definir el tipo de props
interface Props {
    objeto: espacioClienteDto | reservaUsuarioClienteDto
}

// Componente de React
const TarjetaDetalle: React.FC<Props> = ({ objeto }) => {

    const navigate = useNavigate();

    return (
        <article className='item detalle-item-lista'>
            <div className=' icon-tipo'>
                <img src={obtenerImagenSubtipo(objeto.idSubtipo)} alt="" />
            </div>
            <div className='info-container'>
                {esTipoEspacio(objeto) && (
                    <>
                        <h2 className='titulo'>{objeto.nombre}</h2>
                        <p className='precio-hora'>{objeto.precio}€ por hora</p>
                        <p className='descripcion'>{objeto.descripcion}</p>
                        <button className='btn-primary btn-borde' onClick={() => navigate(`/reservarEspacio/${objeto.idEspacio}`)}>Reservar en este espacio</button>
                    </>
                )}

                {esTipoReserva(objeto) && (
                    <>
                    <div className='date-precio'>
                        <div className='fecha-horas'>
                            <p className='fecha'>{new Date(objeto.fechaInicio).toLocaleDateString('es-ES', {day: '2-digit', month: '2-digit', year: 'numeric'})}</p>
                            <p className='horas'><span className='hora'>{new Date(objeto.fechaInicio).getHours()}:00</span> a <span className='hora'>{new Date(objeto.fechaFin).getHours()}:00</span></p>
                        </div>
                        <p className='precio'>{objeto.precioVenta}€</p>
                    </div>
                    <h2 className='titulo'>{objeto.nombreEspacio}</h2>
                    <p className='observaciones'>{objeto.observacionesReserva}</p>
                    <button className='btn-primary btn-borde' onClick={() => navigate(`/ver-reserva/${objeto.idReserva}`)}>Ver detalle</button>
                </>
                )}

            </div>
        </article>
    );
};

export default TarjetaDetalle;