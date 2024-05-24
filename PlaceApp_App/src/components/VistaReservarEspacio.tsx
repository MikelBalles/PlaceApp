import React, { useEffect, useState } from "react"
import { espacioDto, modeloInputs, sesionIniciada } from "../datos/tipos";
import Input from "./Input";
import SelectHoras from "./SelectHoras";
import ExtrasReserva from "./ExtrasReserva";

interface VistaReservarEspacioProps {
    sesion: sesionIniciada | undefined;
}

const inputsIniciales : modeloInputs[] = [
    { name: 'fecha', value: '', esValido: false },
    { name: 'horas', value: '', esValido: false },
  ];

const VistaReservarEspacio: React.FC<VistaReservarEspacioProps> = ( {sesion } ) => {

    const [estadoInputs, setEstadoInputs] = useState<modeloInputs[]>(inputsIniciales);
    const [espacioDto, setEspacioDto] = useState<espacioDto>();
    const [fechaSeleccionada, setFechaSeleccionada] = useState<Date | undefined>(undefined);


    const actualizarFecha = (name: string, value: string, esValido: boolean) => {
        const inputActualizado = { name, value, esValido };    
    
        const nuevosInputs = estadoInputs.map((input) => {
          if (input.name === name) {
            return inputActualizado;
          }
          return input;
        });
        setFechaSeleccionada( value === '' ? undefined : new Date(value));
        setEstadoInputs(nuevosInputs);
      }


    useEffect(() => {

        fetch('http://localhost:8088/rest/cliente/espacio/cliente/1')
            .then(response => response.json())
            .then(data => setEspacioDto(data))
            .catch(error => console.log(error));
    }, []);

    useEffect(() => {
        console.log(fechaSeleccionada);
        
    }, [fechaSeleccionada]);
    

    if (!sesion) {
        return <h1>Debes iniciar sesión para reservar un espacio</h1>
    }



    return ( 
        <>
        <h1 className="titulo-1">Reservar espacio</h1>
        <div className="contenedor-flex-hor contenedor-reserva">
            <div className="col-flex contenedor-opciones">
            <Input name={estadoInputs[0].name} type="date" label="Selecciona una fecha" value={estadoInputs[0].value} manejarCambioValor={actualizarFecha} required/>
            {fechaSeleccionada && 
                <>
                <p className="subtitulo">Escoge una hora de inicio</p>
                <SelectHoras horas={[9, 10, 11, 12, 13, 14, 15, 16, 17, 18]} noDisponible={[10, 11, 12, 13, 14]} />

                <p className="subtitulo">¿Quieres algún servicio extra?</p>

                <ExtrasReserva extras={espacioDto?.extras}/>

                </>
            }

            </div>

            <div className="col-flex info-espacio contenedor-flex-ver">
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
            </div>

        </div>
        </>
    )
}
export default VistaReservarEspacio;