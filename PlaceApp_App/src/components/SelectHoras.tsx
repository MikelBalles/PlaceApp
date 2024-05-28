import React, {useState } from 'react';
import { horaConDisponibilidad } from '../datos/tipos';

interface SelectHorasProps {
  horas: horaConDisponibilidad[];
  actualizarHora: (hora: number) => void;
}

const SelectHoras: React.FC<SelectHorasProps> = ({ horas, actualizarHora }) => {
  const [horaSeleccionada, setHoraSeleccionada] = useState<number | null>(null);

  const handleClick = (hora: number) => {
    setHoraSeleccionada(hora);
    actualizarHora(hora);
  };

return (
    <div className='contenedor-horas'>
        {horas.map((hora, index) => (
            <button 
                type='button'
                key={index} 
                onClick={() => handleClick(hora.horaDate.getHours())} 
                disabled={!hora.disponible}
                className={`select-hora ${horaSeleccionada === hora.horaDate.getHours() ? 'selected' : ''}`}
                            >
                {hora.horaDate.getHours()}:00
            </button>
        ))}
    </div>
);
};

export default SelectHoras;