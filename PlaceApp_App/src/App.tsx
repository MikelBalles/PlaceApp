
import './App.scss'
import { useEffect, useState } from 'react';

import Navbar from './components/NavBar';
function App() {
  const [espacioDto, setEspacioDto] = useState<{
    idEspacio: number,
    nombreEspacio: string,
    descripcion: string,
    cp: number,
    provincia: string,
    subtipo: number,
    precio: number,
    nombreUsername: string,
    telefonoUsername: number,
    correoUsername: string,
    extras: [
        {
            idExtra: number,
            nombre: string,
            precio: number,
            descripcion: string
        }
    ]

   }>
   ({idEspacio: 0,
    nombreEspacio: '',
    descripcion: '',
    cp: 0,
    provincia: '',
    subtipo: 0,
    precio: 0,
    nombreUsername: '',
    telefonoUsername: 0,
    correoUsername: '',
    extras: [
        {
            idExtra: 0,
            nombre: '',
            precio: 0,
            descripcion: '',
        }
    ]});

  useEffect(() => {
    fetch('http://localhost:8088/rest/cliente/espacio/cliente/1')
      .then(response => response.json())
      .then(data => setEspacioDto(data))
      .catch(error => console.log(error));
  }, []); 

  return (
    <>
      <Navbar sesionIniciada={true} nombreEspacio={espacioDto.nombreEspacio} />
    </>
  );
}



export default App;
