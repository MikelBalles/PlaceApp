
import './App.scss'
import logo_PlaceApp from './assets/logo_PlaceApp.svg'

function App() {

  return (
    <>
      <nav className='navbar'>
      <div className='logo-navbar'>
          <a href='#'>
            <img src={logo_PlaceApp} alt='logo' />
          </a>
        </div>
        <div className="links-acceso">
          <a className="btn-primary btn-sin-relleno" href="#">Cómo funciona</a>
          <a className='btn-primary btn-sin-relleno' href="#">Preguntas frecuentes</a>          
        </div>

        <div className='links-cuenta'>
          <a className="btn-primary btn-borde" href='#'>Iniciar sesión</a>
          <a className="btn-primary btn-relleno" href='#'>Registrarse</a>
        </div>
      </nav>
    </>
  )
}

export default App
