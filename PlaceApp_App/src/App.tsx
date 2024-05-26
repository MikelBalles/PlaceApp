
import './App.scss'
import { useEffect, useState } from 'react';
import VistaClientePpal from './components/VistaClientePpal';
import VistaReservarEspacio from './components/VistaReservarEspacio';
import Navbar from './components/NavBar';
import Login from './components/Login';
import Footer from './components/footer';
import { sesionIniciada } from './datos/tipos';
import { cerrarSesion, iniciarSesion, obtenerSesion } from './logica/manejarSesion';
import { Route, Routes, useNavigate } from 'react-router-dom';
import ListaEspacios from './components/ListaEspacios';
function App() {

  const navigate = useNavigate();

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
    navigate('/cliente');
  }

  function gestionarCerrarSesion() {
    cerrarSesion();
    setEstadoSesion(undefined);
    navigate('/login');
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
        <Routes >
          <Route path="/login" element={<Login sesion={estadoSesion} iniciarSesion={gestionarInicioSesion} />} />
          <Route path="/cliente" element={<VistaClientePpal sesion={estadoSesion} cerrarSesion={gestionarCerrarSesion} />} />
          <Route path="/reservarEspacio/:idEspacio" element={<VistaReservarEspacio sesion={estadoSesion} />} />
          <Route path="/espacios/:idSubtipo/:idProv" element={<ListaEspacios sesion={estadoSesion} />} />
        </Routes>
      </div>
      <Footer />
    </>
  );
}

export default App;
