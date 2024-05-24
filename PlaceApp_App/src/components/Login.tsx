import React, { useEffect, useState } from 'react';
import Input from './Input';
import infografiaLogin from '../assets/images/infografia-login.svg';
import { URL_PETICION_BBDD } from '../datos/constantes';
import { modeloInputs, sesionIniciada } from '../datos/tipos';
import { useNavigate } from 'react-router-dom';

interface LoginProps {
  iniciarSesion: (data: sesionIniciada) => void;
  sesion: sesionIniciada | undefined;
}

const inputsIniciales : modeloInputs[] = [
  { name: 'username', value: '', esValido: false },
  { name: 'password', value: '', esValido: false },
];


const Login: React.FC<LoginProps> = ( {sesion, iniciarSesion} ) => {
  
  const [inputValidos, setInputValidos] = useState(false);
  const [estadoInputs, setEstadoInputs] = useState<modeloInputs[]>(inputsIniciales);
  const [msgLogin, setMsgLogin] = useState('');
  
  //Comprobamos si la sesión ya está iniciada
  const navigate = useNavigate();
  useEffect(() => {    
    sesion && navigate('/cliente');
  }, [navigate, sesion]);

  //Cada vez que cambia el estado de los inputs, comprobamos si son validos
  useEffect(() => {    
    const todosEsValido = estadoInputs.map((input) => input.esValido);
    setInputValidos(todosEsValido.every((valido) => valido));
  }, [estadoInputs]);



  const actualizarValorInput = (name: string, value: string, esValido: boolean) => {
    const inputActualizado = { name, value, esValido };    

    const nuevosInputs = estadoInputs.map((input) => {
      if (input.name === name) {
        return inputActualizado;
      }
      return input;
    });
    setEstadoInputs(nuevosInputs);
  }
  //Gestionamos el inicio de sesión cuando se hace click en el botón
  const handleLogin = async () => {
    const datos = {
      "username": estadoInputs[0].value,
      "password": estadoInputs[1].value
    }
    
    fetch(URL_PETICION_BBDD + '/rest/propietario/iniciarSesion', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(datos),
    }).then(function (response) {
      if (response.ok) {
        setMsgLogin('');
        response.json().then(function(data:sesionIniciada) {
        iniciarSesion(data);
    });
      } else {
        response.text().then(function(text) {
          setMsgLogin(text);
        });
      }
    }).catch(function () {
      setMsgLogin('Error en la petición');
    });
  };


  return (
    <div id='login-contenedor' className='contenedor-flex-hor'>
      <div id='info-login' className='col-flex'>
        <img src={infografiaLogin} alt='Infografía de login' />
      </div>
      <div id='datos-login' className='col-flex contenedor-flex-ver'>
        <h2 className='titulo-2'>Bienvenido</h2>
        <p className='error-msg'>{msgLogin}</p>
        <Input
          name={estadoInputs[0].name}
          label='Correo electrónico'
          value={estadoInputs[0].value}
          type="text"
          placeholder="Correo electrónico"
          required

          manejarCambioValor={actualizarValorInput}

        />
        <Input
          name={estadoInputs[1].name}
          label='Contraseña'
          value={estadoInputs[1].value}
          type="password"
          placeholder="Contraseña"
          required

          manejarCambioValor={actualizarValorInput}

        />
        <button className='btn-primary btn-borde' onClick={handleLogin} disabled={!inputValidos}>
          Iniciar sesión
        </button>
      </div>
    </div>
  );
};

export default Login;