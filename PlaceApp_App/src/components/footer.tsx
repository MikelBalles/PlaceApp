
import { HashLink } from 'react-router-hash-link';
import logo_PlaceApp from '../assets/logo_PlaceApp.svg';
import { RUTAS } from '../datos/rutas';
import { Link } from 'react-router-dom';

function Footer() {
    return (
      <div className='footer-bg'>
      <footer>
        <div className="footer-col con-logo">
        <img src={logo_PlaceApp} alt="Logo" />
          <p>Calle de las Amapolas, 36. Madrid</p>
        </div>
        <div className="footer-col">
          <h3 className='titulo-footer'>¿Necesitas ayuda?</h3>
          <div className='texto-col'>
            <HashLink smooth to={RUTAS.contacto}>Contacto</HashLink>
            <HashLink smooth to={RUTAS.faqs}>Preguntas frecuentes</HashLink>
            <Link to="/texto-legal">Texto legal</Link>
          </div>
        </div>
        <div className="footer-col">
          <h3 className='titulo-footer'>Accesos directos</h3>
          <div className='texto-col'>
            <HashLink smooth to={RUTAS.comoFunciona}>Cómo funciona PlaceApp</HashLink>
            <Link to={RUTAS.cliente}>Ir a mi área personal</Link>
          </div>
        </div>
      </footer>
      </div>
    );
  }
  
  export default Footer;