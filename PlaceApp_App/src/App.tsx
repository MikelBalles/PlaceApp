
import './App.scss'
import { useEffect, useState } from 'react';
import VistaClientePpal from './components/vistas/VistaClientePpal';
import Navbar from './components/NavBar';
import Login from './components/Login';
import Footer from './components/footer';
import { sesionIniciada } from './datos/tipos';
import { cerrarSesion, iniciarSesion, obtenerSesion } from './logica/manejarSesion';
import { Route, Routes, useNavigate } from 'react-router-dom';
import ListaEspacios from './components/vistas/ListaEspacios';
import ListaReservas from './components/vistas/ListaReservas';
import VistaReservarEspacio from './components/vistas/VistaReservarEspacio';
import { RUTAS } from './datos/rutas';
import LandingPage from './components/vistas/landingPage';
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


    return (    
      <>
      <Navbar sesionIniciada={estadoSesion} />
      <div className='contenedor-general'>
        <Routes >
          <Route path="/" element={<LandingPage />} />
          <Route path={RUTAS.login} element={<Login sesion={estadoSesion} iniciarSesion={gestionarInicioSesion} />} />
          <Route path={RUTAS.cliente} element={<VistaClientePpal sesion={estadoSesion} cerrarSesion={gestionarCerrarSesion} />} />
          <Route path={`${RUTAS.reservarEspacio}/:idEspacio`} element={<VistaReservarEspacio sesion={estadoSesion} />} />
          <Route path="/espacios/:idSubtipo/:idProv" element={<ListaEspacios sesion={estadoSesion} />} />
          <Route path="/mis-reservas" element={<ListaReservas sesion={estadoSesion}/>}/>
        </Routes>
      </div>
      <Footer />
    </>
  );
}

export default App;
