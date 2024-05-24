import React, { useState } from "react";
import { extraDto } from "../datos/tipos";

interface ExtrasReservaProps {
    extras: extraDto[] | undefined;
}

const ExtrasReserva: React.FC<ExtrasReservaProps> = ({ extras }) => {

    const [extraAñadido, setExtraAñadido] = useState<boolean>(false);

    const onClickExtra = () => {
        setExtraAñadido(!extraAñadido);
    }

    return (
        <div className="contenedor-flex-ver contenedor-extras">
        {extras?.map((extra) => (
            <li key={extra.idExtra} className="contenedor-flex-hor item-extra">
                <div className="col-flex contenedor-flex-ver mini-gap info-extra">
                    <p className="titulo">{extra.nombre}</p>
                    <p>{extra.descripcion}</p>
                </div>
                <div className="col-flex contenedor-flex-ver mini-gap">
                    <p>{extra.precio} € por hora</p>
                    <button className={`extra-button ${extraAñadido ? 'extra-added' : ''}`}
                            onClick={onClickExtra} >
                        {extraAñadido ? 'Añadido' : 'Añadir extra'}
                    </button>
                </div>
            </li>

        ))}

        </div>
    );
};

export default ExtrasReserva;