import React from 'react';

interface Props {
  tipo: 'ERROR' | 'OK';
  mensaje: string;
}

const InfoModal: React.FC<Props> = ({ tipo, mensaje }) => {
  const claseTipo = tipo === 'ERROR' ? 'error' : 'ok';  
  let titulo = ''
  switch (tipo) {
    case 'ERROR':
      titulo = 'Lo sentimos, ha ocurrido un error:';
      break;
    case 'OK':
        titulo = '¡Genial! Todo ha ido bien:';
        break;
    default:
        break;
    }

    if (mensaje === '') {
        return null;
    }

    return (
        <div className={`contenedor-info-modal ${claseTipo}`}>
        <h3 className='titulo-info-modal'>{titulo}</h3>
        <p>{mensaje}</p>
        </div>
    );
};

export default InfoModal;