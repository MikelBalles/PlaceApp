import React, { useState } from "react";
import { extraDto } from "../datos/tipos";
import icoCheck from "../assets/ico-check.svg";
import icoError from "../assets/ico-error.svg";

interface ExtrasReservaProps {
    extra: extraDto;
    onClickExtraProps: (extra: extraDto) => void;
}

const ExtrasReserva: React.FC<ExtrasReservaProps> = ({ extra , onClickExtraProps}) => {

    const [extraAñadido, setExtraAñadido] = useState<boolean>(false);

    const nombreClase = `extra-button btn-primary btn-sin-relleno ${extraAñadido ? 'extra-added' : ''}`;

    const onClickExtra = () => {
        setExtraAñadido(!extraAñadido);
        //Quitamos el focus del botón
        const boton = document.activeElement as HTMLElement;
        boton.blur();
        onClickExtraProps(extra);
    }

    return (
            <div className="contenedor-flex-hor item-extra">
                <div className="col-flex contenedor-flex-ver mini-gap info-extra">
                    <p className="titulo">{extra?.nombre}</p>
                    <p>{extra?.descripcion}</p>
                </div>
                <div className="col-flex contenedor-flex-ver mini-gap">
                    <p className="precio-extra">{extra?.precio} € por hora</p>
                    <button className={nombreClase}
                            onClick={onClickExtra}
                            type="button" >
                        <img className="icono-extra-anadido" src={icoCheck}/>
                        <img className="icono-extra-eliminar" src={icoError}/>
                        <span className="extra-defecto">{extraAñadido ? 'Añadido' : 'Añadir extra'}</span>
                        <span className="extra-eliminar-texto">Eliminar extra</span>
                    </button>
                </div>
            </div>
    );
};

export default ExtrasReserva;