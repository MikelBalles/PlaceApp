import React, { useEffect, useState } from 'react';

interface SelectHorasProps {
  horas: number[];
  noDisponible: number[];
}

const SelectHoras: React.FC<SelectHorasProps> = ({ horas, noDisponible }) => {
  const [horaSeleccionada, setHoraSeleccionada] = useState<number | null>(null);

  const handleClick = (hora: number) => {
    setHoraSeleccionada(hora);
  };

  useEffect(() => {
    console.log(horaSeleccionada);
  }, [horaSeleccionada]);

return (
    <div className='contenedor-horas'>
        {horas.map((hora, index) => (
            <button 
                key={index} 
                onClick={() => handleClick(hora)} 
                disabled={noDisponible.includes(hora)}
                className={`select-hora ${horaSeleccionada === hora ? 'selected' : ''}`}
                            >
                {hora}:00
            </button>
        ))}
    </div>
);
};

export default SelectHoras;