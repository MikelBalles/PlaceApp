import React, { useState, useEffect, useRef } from 'react';
import { provinciaDto, sesionIniciada, subtipoDto, tipoDto } from '../../datos/tipos';
import { URL_PETICION_BBDD } from '../../datos/constantes';
import { useLocation, useNavigate } from 'react-router-dom';
import { obtenerImagenPorNombre, obtenerImagenSubtipo } from '../../logica/iconosTipos';
import PaginaEspecial from '../modales/paginaEspecial';
import InfoModal from '../modales/InfoModal';
import { RUTAS } from '../../datos/rutas';
import { Link } from 'react-router-dom';
import VistaVacia from '../modales/VistaVacia';




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

    const navigate = useNavigate();
    const location = useLocation();

    //Petición a la base de datos para obtener los tipos y subtipos
    const obtenerTiposSubtipos = async () => {
        try {
            const response = await fetch(`${URL_PETICION_BBDD}/rest/comun/tipo/subtipo`);
            if (!response.ok) {
                throw new Error('Error al obtener tipos y subtipos del API');
            }
            const data: TipoYSubtipoProps[] = await response.json();
            setTiposSubtipos(data);
        } catch (error) {
            console.error('Error al obtener tipos y subtipos del API:', error);
            <VistaVacia irAtras mensaje='Tenemos problemas recuperando la información. Vuelve a intentarlo'/>
        }
    };


    const provinciasRef = useRef<provinciaDto[]>([]);
    const [tiposSubtipos, setTiposSubtipos] = useState<TipoYSubtipoProps[]>([]);
    const [subtipos, setSubtipos] = useState<subtipoDto[]>([]);
    const [selectedTipo, setSelectedTipo] = useState<number>();
    const [selectedSubtipo, setSelectedSubtipo] = useState<number>();
    const [selectedProvincia, setSelectedProvincia] = useState<number>(1);
    const [esValido, setEsValido] = useState<boolean>(false);


    useEffect(() => {
        obtenerTiposSubtipos();

        //Obtenemos las provincias
        fetch(`${URL_PETICION_BBDD}/rest/comun/provincias`)
            .then(response => response.json())
            .then(data => {
                provinciasRef.current = data;
            })
            .catch(error => console.error('Error al obtener provincias del API:', error));
    }, []);

    useEffect(() => {
        if (selectedTipo && selectedSubtipo && selectedProvincia) {
            setEsValido(true);
        } else {
            setEsValido(false);
        }
    }, [selectedTipo, selectedSubtipo, selectedProvincia]);

    const clickEnTipo = (idTipo: number) => {
        obtenerSubtipos(idTipo);
        setSelectedTipo(idTipo);
    }

    const clickEnSubtipo = (idSubtipo: number) => {
        setSelectedSubtipo(idSubtipo);
    }

    const clickEnBuscarEspacios = () => {
        //Comprobamos si existen los valores seleccionados
        if (!esValido) {
            alert('Debes seleccionar un tipo, un subtipo y una provincia para buscar espacios');
            return;
        }

        //Redirigimos a la vista de espacios
        navigate(`/espacios/${selectedSubtipo}/${selectedProvincia}`);
    }


    const obtenerSubtipos = (idTipo: number) => {
        const arraySubtipos: subtipoDto[] = [];

        for (const tipoSubtipo of tiposSubtipos) {
            if (tipoSubtipo.idTipo === idTipo) {
                const subtipo: subtipoDto = {
                    id: tipoSubtipo.idSubtipo,
                    nombre: tipoSubtipo.nombreSubtipo
                };
                arraySubtipos.push(subtipo);
            }
        }
        setSubtipos(arraySubtipos);
    }

    const obtenerTiposUnicos = (): tipoDto[] => {

        const tiposUnicos: tipoDto[] = [];

        tiposSubtipos.forEach(tipoSubtipo => {
            const tipo: tipoDto = {
                id: tipoSubtipo.idTipo,
                nombre: tipoSubtipo.nombreTipo
            };

            if (!tiposUnicos.some(tipoUnico => tipoUnico.id === tipo.id))
                tiposUnicos.push(tipo);
        });
        return tiposUnicos;
    };

    const tiposUnicos = obtenerTiposUnicos();

    if (!sesion) {
        return (
            <PaginaEspecial tipo='sesion' />
        );
    }

    return (
        <section className="VistaClientePpal-container contenedor-botones">
            <div className="contenedor-botones">
                <Link className="btn-primary btn-borde" to={RUTAS.misReservas}>Gestionar mis reservas</Link>
                <Link className="btn-primary btn-borde" to={RUTAS.modificarUsuario}>Modificar mis datos</Link>
                <button className="btn-primary btn-cerrar-sesion" onClick={cerrarSesion}>Cerrar Sesion</button>
            </div>
            {location.state?.mensaje && (
                <InfoModal tipo='OK' mensaje={location.state.mensaje} />
            )}
            <header className="reserva-header">
                <h2>¿Quieres realizar una reserva?</h2>
            </header>
            <article className="VistaPpl-article">
                <h2 className="subtitulo principal">Selecciona el tipo de espacio que quieres reservar</h2>
                <div className='listado-tipos'>
                    {tiposUnicos.map((tipo) => (
                        <button key={tipo.id} className={`btn-tipo ${selectedTipo === tipo.id && 'selected'}`} onClick={() => clickEnTipo(tipo.id)}>
                            <img src={obtenerImagenPorNombre(tipo.nombre)} alt={tipo.nombre} />
                            {tipo.nombre}
                        </button>
                    ))}
                </div>
            </article>

            {selectedTipo != undefined && (
                <article className="VistaPpl-article">
                    <h2 className="subtitulo principal">¿En qué estas pensando exactamente?</h2>
                    <div className='listado-tipos'>
                        {subtipos.map((subtipo) => (
                            <button key={subtipo.id} className={`btn-tipo ${selectedSubtipo === subtipo.id && 'selected'}`} onClick={() => clickEnSubtipo(subtipo.id)}>
                                <img src={obtenerImagenSubtipo(subtipo.id)} alt={subtipo.nombre} />
                                {subtipo.nombre}
                            </button>
                        ))}
                    </div>
                </article>
            )}

            {selectedSubtipo !== undefined && (
                <article className="VistaPpl-article">
                    <h2 className="subtitulo principal">¿En qué lugar quieres reservar?</h2>
                    <select name="select-lugar" onChange={e => setSelectedProvincia(parseInt(e.target.selectedOptions[0].dataset.provId!))}>
                        {provinciasRef.current.map((p) => (
                            <option key={p.idProv} value={p.nombre} data-prov-id={p.idProv}>
                                {p.nombre}
                            </option>
                        ))}
                    </select>
                </article>
            )
            }
            <button className="btn-primary btn-borde" onClick={clickEnBuscarEspacios} disabled={!esValido}>Buscar espacios</button>
        </section>
    );
}

export default VistaClientePpal;
