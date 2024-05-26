import React, { Fragment, useEffect, useMemo, useState } from "react"
import { Reserva, espacioDto, extraDto, horaConDisponibilidad, modeloInputs, reservaEspacioDto, sesionIniciada } from "../datos/tipos";
import Input from "./Input";
import SelectHoras from "./SelectHoras";
import ExtrasReserva from "./ExtrasReserva";
import { obtenerHorasDisponiblesYNoDisponibles, obtenerMaxHorasReservables } from "../logica/gestionarFechas";
import { URL_PETICION_BBDD } from "../datos/constantes";

interface VistaReservarEspacioProps {
    sesion: sesionIniciada | undefined;
}

const inputsIniciales: modeloInputs[] = [
    { name: 'fecha', value: '', esValido: false },
    { name: 'observaciones', value: '', esValido: false },
];

const VistaReservarEspacio: React.FC<VistaReservarEspacioProps> = ({ sesion }) => {

    
    const calcularHoraFin = (horaInicio: number, numHoras: number) => {
        setHoraFin(horaInicio + numHoras);
    }

    
    const [estadoInputs, setEstadoInputs] = useState<modeloInputs[]>(inputsIniciales);
    const [espacioDto, setEspacioDto] = useState<espacioDto>();
    const [horaInicio, setHoraInicio] = useState<number>(0);
    const [horaFin, setHoraFin] = useState<number>(0);
    const [fechaSeleccionada, setFechaSeleccionada] = useState<Date>();
    const [extrasSeelecionados, setExtrasSeleccionados] = useState<extraDto[]>([]);
    const [conjuntoHoras, setConjuntoHoras] = useState<horaConDisponibilidad[]>([]);
    const [maxHoras, setMaxHoras] = useState<number>(0);
    const [reservaEspacioDto, setReservaEspacioDto] = useState<Reserva[]>([]);
    
    const esValido = useMemo(() => {
        if (fechaSeleccionada) {
            const fechaActual = new Date();
            if (fechaSeleccionada < fechaActual) {
                return false;
            }
            if (horaInicio <= 0 || horaInicio > 23) {
                return false;
            } else if (horaFin <= 0 || horaFin >= 23) {
                return false;
            } else if (horaFin <= horaInicio) {
                return false;
            }
        }
        return true;
    }, [fechaSeleccionada, horaInicio, horaFin]);
    
    const actualizarFecha = (name: string, value: string, esValido: boolean) => {
        const inputActualizado = { name, value, esValido };
        
        const nuevosInputs = estadoInputs.map((input) => {
            if (input.name === name) {
                return inputActualizado;
            }
            return input;
        });
        setFechaSeleccionada(value === '' ? undefined : new Date(value));
        setEstadoInputs(nuevosInputs);
    }

    const actualizarHoraInicio = (hora: number) => {
        setHoraInicio(hora);
    }

    const precioTotal = useMemo(() => {
        if (!espacioDto) {
            return 0;
        }
        if (horaInicio === 0 || horaFin === 0) {
            return 0;
        }
        const numHoras = horaFin - horaInicio;
        const total =
            espacioDto?.precio * numHoras
            + extrasSeelecionados.reduce((total, extra) => total + (extra.precio) * numHoras, 0);
        return total
    }, [espacioDto, horaInicio, horaFin, extrasSeelecionados]);

    /**
     * Actualizamos los extras en el estado.
     *  Si encontramos el extra en el array de extras seleccionados,
     *  lo eliminamos. Si no lo encontramos, lo añadimos.
     * @param extra 
     */
    const actualizarExtras = (extra: extraDto) => {
        const extras = extrasSeelecionados;
        const extraEncontrado = extras.find(e => e.idExtra === extra.idExtra);
        if (extraEncontrado) {
            const nuevosExtras = extras.filter(e => e.idExtra !== extra.idExtra);
            setExtrasSeleccionados(nuevosExtras);
        } else {
            setExtrasSeleccionados([...extras, extra]);
        }
    }

    const actualizarObservaciones = (value: string) => {
        const inputActualizado = { name: 'observaciones', value, esValido: true };

        const nuevosInputs = estadoInputs.map((input) => {
            if (input.name === 'observaciones') {
                return inputActualizado;
            }
            return input;
        });

        setEstadoInputs(nuevosInputs);
    }

    const enviarFormulario = (e: React.FormEvent) => {
        e.preventDefault();
        if (esValido) {

            if (espacioDto && sesion && fechaSeleccionada) {
                const fechaInicio = new Date(fechaSeleccionada);
                fechaInicio.setHours(horaInicio);
                const fechaFin = new Date(fechaSeleccionada);
                fechaFin.setHours(horaFin);

                const objRes: reservaEspacioDto = {
                    idEspacio: espacioDto.idEspacio,
                    usernameReserva: sesion.username,
                    precioVenta: precioTotal,
                    fechaInicioReserva: fechaInicio.toISOString() || '',
                    fechaFinReserva: fechaFin.toISOString() || '',
                    observacionReserva: estadoInputs[1].value
                }

                fetch(`${URL_PETICION_BBDD}/rest/cliente/espacio/altaReserva`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(objRes)
                })
                    .then(response => response.json())
                    .then(data => console.log(data))
                    .catch(error => console.log(error));
            }

        } else {
            console.log('Formulario inválido');
        }

    }



    useEffect(() => {

        fetch(`${URL_PETICION_BBDD}/rest/cliente/espacio/cliente/6`)
            .then(response => response.json())
            .then(data => {
                setEspacioDto(data)
            })
            .catch(error => console.log(error));

        fetch(`${URL_PETICION_BBDD}/rest/propietario/reservas/espacio/6`)
            .then(response => response.json())
            .then(data => {
                setReservaEspacioDto(data)
            })
            .catch(error => console.log(error));
    }, []);

    useEffect(() => {
        if (fechaSeleccionada) {
            console.log('Entro aquí');
            
            const getNumeroHorasDisponibles = () => {
                return obtenerHorasDisponiblesYNoDisponibles(fechaSeleccionada, reservaEspacioDto);
            }
            setConjuntoHoras(getNumeroHorasDisponibles());

            setHoraFin(horaInicio + 1);
        } else {
            setHoraInicio(0);
            setMaxHoras(1);
            
        }
    }, [reservaEspacioDto, fechaSeleccionada, horaInicio]);


    useEffect(() => {
        if (conjuntoHoras.length > 0) {
            setMaxHoras(obtenerMaxHorasReservables(horaInicio, conjuntoHoras));
        }
    }, [conjuntoHoras, horaInicio]);



    if (!sesion) {
        return <h1>Debes iniciar sesión para reservar un espacio</h1>
    }
    return (
        <>
            <h1 className="titulo-1">Reservar espacio</h1>
            <form onSubmit={enviarFormulario}>
                <div className="contenedor-flex-hor contenedor-reserva">
                    <div className="col-flex contenedor-opciones">
                        <Input name={estadoInputs[0].name} type="date" label="Selecciona una fecha" value={estadoInputs[0].value} manejarCambioValor={actualizarFecha} required />
                        {fechaSeleccionada &&
                            <>
                                <label className="subtitulo">Escoge una hora de inicio</label>
                                <SelectHoras horas={conjuntoHoras} actualizarHora={actualizarHoraInicio} />
                                {(maxHoras > 0 && fechaSeleccionada) && (
                                    <div className="grupo-input">
                                        <label className="subtitulo">¿Cuántas horas quieres reservar?</label>
                                        <select name="num-horas" onChange={e => calcularHoraFin(horaInicio, parseInt(e.target.value))}>
                                            {Array.from({ length: maxHoras }, (_, index) => (
                                                <option key={index + 1} value={index + 1}>
                                                    {index + 1} hora{index !== 0 ? 's' : ''}
                                                </option>
                                            ))}
                                        </select>
                                    </div>
                                )}

                                {espacioDto?.extras && espacioDto.extras.length > 0 ? (
                                    <>
                                        <p className="subtitulo">¿Quieres algún servicio extra?</p>
                                        <div className="contenedor-flex-ver contenedor-extras">
                                            {espacioDto?.extras?.map(extra => (
                                                <Fragment key={extra.idExtra}>
                                                    <ExtrasReserva extra={extra} onClickExtraProps={actualizarExtras} />
                                                </Fragment>
                                            ))}
                                        </div>
                                    </>
                                ) : null}

                            </>
                        }

                    </div>

                    <div className="col-flex contenedor-opciones">
                        <section className="info-espacio contenedor-flex-ver">
                            <h2 className="subtitulo">Información del espacio</h2>
                            <div className="info-grupo">
                                <p className="titulo">Nombre del espacio:</p>
                                <p>{espacioDto?.nombreEspacio}</p>
                            </div>
                            <div className="info-grupo">
                                <p className="titulo">Descripción:</p>
                                <p>{espacioDto?.descripcion}</p>
                            </div>
                            <div className="info-grupo">
                                <p className="titulo">Dirección</p>
                                <p>{espacioDto?.direccion}, {espacioDto?.cp} ({espacioDto?.provincia})</p>
                            </div>
                        </section>

                        <section className="info-resumen contenedor-flex-ver">
                            <h2 className="subtitulo">Resumen de la reserva</h2>
                            <div className="tarjeta-resumen-reserva">
                                <div className="fecha-horas">
                                    <p className="subtitulo fecha">{fechaSeleccionada?.toLocaleDateString('es-ES', { day: 'numeric', month: 'long', year: 'numeric' })}</p>
                                    <div className="horas">
                                        <p className="hora">{horaInicio}:00</p>
                                        <p className="hora">{horaFin}:00</p>
                                    </div>
                                </div>
                                <p className="precio-total">{precioTotal}€</p>
                            </div>
                            <label htmlFor="observacionesReserva">Observaciones:</label>
                            <textarea
                                id="observacionesReserva"
                                name="observacionesReserva"
                                rows={4}
                                cols={40}
                                onChange={e => actualizarObservaciones(e.target.value)}
                            />

                        </section>
                        <button className="btn-primary btn-borde" type="submit" disabled={!esValido}>Reservar</button>
                    </div>

                </div>
            </form>
        </>
    )
}
export default VistaReservarEspacio;