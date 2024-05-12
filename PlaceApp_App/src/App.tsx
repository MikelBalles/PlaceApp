
import './App.scss'
import { useEffect, useState } from 'react';
import VistaClientePpal from './components/VistaClientePpal';
import Navbar from './components/NavBar';
import Login from './components/Login';
import Footer from './components/footer';
import { sesionIniciada } from './datos/tipos';
import { cerrarSesion, iniciarSesion, obtenerSesion } from './logica/manejarSesion';
function App() {

  //ESTADOS DEL COMPONENTE
  const [estadoSesion, setEstadoSesion] = useState<sesionIniciada>();

  //REACT HOOKS
  //Comprobamos si existe la sesión cuando arrancamos la APP y la asignamos al estado
  useEffect(() => {
    obtenerSesion() && setEstadoSesion(obtenerSesion());
  }, []);

  //FUNCIONES
  function gestionarInicioSesion(data: sesionIniciada) {
    iniciarSesion(data);
    setEstadoSesion(data);
  }

  function gestionarCerrarSesion() {
    cerrarSesion();
    setEstadoSesion(undefined);
  }


  //!EJEMPLO: Esto tiene que ir fuera
  /*
  useEffect(() => {
    fetch('http://localhost:8088/rest/cliente/espacio/cliente/1')
      .then(response => response.json())
      .then(data => setEspacioDto(data))
      .catch(error => console.log(error));
  }, []); 
  */


  return (
    <>
      <Navbar sesionIniciada={estadoSesion} />
      <div className='contenedor-general'>
        <Login iniciarSesion={gestionarInicioSesion} />

        <VistaClientePpal sesion={estadoSesion} cerrarSesion={gestionarCerrarSesion}></VistaClientePpal>
      </div>
      <Footer />
    </>
  );
}

export default App;
