import React, { useState, useEffect } from 'react';   
import { sesionIniciada } from '../datos/tipos';
import Deportivo from '../assets/Deportivo.svg';
import Salas from '../assets/Salas_privadas.svg';
import Gastronomia from '../assets/Gastronomia.svg';

interface VistaClientePpalProps {
    sesion: sesionIniciada | undefined;
    cerrarSesion: () => void;
}

interface TipoYSubtipoProps {
    idSubtipo: number;
    nombreSubtipo: string;
    idTipo: number;
    nombreTipo: string;
}

const VistaClientePpal: React.FC<VistaClientePpalProps> = ({ sesion, cerrarSesion }) => {
    const [tiposSubtipos, setTiposSubtipos] = useState<TipoYSubtipoProps[]>([]);

    useEffect(() => {
        obtenerTiposSubtipos();
    }, []);

    const obtenerTiposSubtipos = async () => {
        try {
            const response = await fetch('http://localhost:8088/rest/cliente/tipo/subtipo');
            if (!response.ok) {
                throw new Error('Error al obtener tipos y subtipos del API');
            }
            const data: TipoYSubtipoProps[] = await response.json();
            setTiposSubtipos(data);
        } catch (error) {
            console.error('Error al obtener tipos y subtipos del API:', error);
        }
    };

    const obtenerTiposUnicos = (): string[] => {
        const tiposUnicos = new Set<string>();
        tiposSubtipos.forEach(tipoSubtipo => {
            tiposUnicos.add(tipoSubtipo.nombreTipo);
        });
        return Array.from(tiposUnicos);
    };

    const tiposUnicos = obtenerTiposUnicos();

    if (!sesion) {
        console.log('No hay sesión iniciada');
        return null;
    }

    return (
        <section className="VistaClientePpal-container">
            <article className="VistaPpl-article">
                <button className="btn-primary btn-borde">Gestionar mis reservas</button>
                <button className="btn-primary btn-borde">Modificar mis datos</button>
                <button className="btn-primary btn-borde" onClick={cerrarSesion}>Cerrar Sesion</button>
            </article>
            <header className="reserva-header">
                <h2>¿Quieres realizar una reserva?</h2>
            </header>
            <p className="subtitulo principal">Selecciona el tipo de evento que quieres reservar</p>
            <article className="VistaPpl-article">
                {tiposUnicos.map((nombreTipo, index) => (
                    <button key={index} className="btn-reservar">
                        <img src={obtenerImagenPorNombre(nombreTipo)} alt={nombreTipo} />
                        {nombreTipo}
                    </button>
                ))}
            </article>
            <p className="subtitulo principal">¿En qué estás pensando exactamente?</p>
        </section>
    );
}

const obtenerImagenPorNombre = (nombreTipo: string): string => {
    switch (nombreTipo) {
        case 'Espacios Deportivos':
            return Deportivo;
        case 'Salas Privadas':
            return Salas;
        case 'Gastronomia':
            return Gastronomia;
        default:
            return '';
    }
};

export default VistaClientePpal;
