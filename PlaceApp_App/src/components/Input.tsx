import React, { useState } from 'react';

//Definimos las propiedades del componente
interface InputProps {
    name: string;
    label: string;
    type: string;
    value: string;
    manejarCambioValor: (name: string, value: string, esValido: boolean) => void;
    required?: boolean;
    minLength?: number;
    maxLength?: number;
    placeholder?: string;
}

//Definimos el componente
const Input: React.FC<InputProps> = ({
    name,
    label,
    type,
    required = false,
    manejarCambioValor,
    value,
    minLength,
    maxLength,
    placeholder,

}) => {
    const [errorMessage, setErrorMessage] = useState<string>('');
    const [inputValido, setInputValido] = useState<boolean>(false);


    const validateInput = (value:string) => {  
        const  status = {
            text : '✅ Campo validado',
            valid : true,
        }        
             
        if (required && value.trim() === '') {           
            status.text = '❌ Este campo es obligatorio';
            status.valid = false;
            return status;
        }

    
        if (minLength && value.length < minLength) {
            status.text = `❌ La longitud mínima es de ${minLength} caracteres`;
            status.valid = false;
            return status;
        }
    
        if (maxLength && value.length > maxLength) {
            status.text = `❌ La longitud máxima es de ${maxLength} caracteres`;
            status.valid = false;
            return status;
        }    
        return status;
    };

    const comprobarCambios = (e: React.ChangeEvent<HTMLInputElement>) => {
        const checkInput = validateInput(e.target.value); 
        manejarCambioValor(name, e.target.value, checkInput.valid)
       
        setErrorMessage(checkInput.text);
        setInputValido(checkInput.valid);           
    }

    return (
        <div className='grupo-input'>
            <label htmlFor={name}>{label}</label>
            <input  name={name} type={type} value={value}
                    onChange={comprobarCambios} placeholder={placeholder}
                    />
            {!inputValido && 
            <p className='input-error-msg'>{errorMessage}</p>
        }
            {inputValido && 
                <p className='input-valid-msg'>{errorMessage}</p>
            }
        </div>
    );
};

export default Input;