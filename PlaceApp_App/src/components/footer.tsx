
import logo_PlaceApp from '../assets/logo_PlaceApp.svg';

function Footer() {
    return (
      <footer>
        <div className="footer-left">
        <img src={logo_PlaceApp} alt="Logo" />
          <p>Calle de las Amapolas, 36. Madrid</p>
        </div>
        <div className="footer-center">
          <button className='btn-footer'>¿Necesitas ayuda?</button>
          <p>Contacto</p>
          <p>Preguntas frecuentes</p>
          <p>Texto legal</p>
        </div>
        <div className="footer-right">
          <button className='btn-footer'>Accesos directos</button>
          <p>Cómo funciona PlaceApp</p>
          <p>Ir a mi área personal</p>
        </div>
      </footer>
    );
  }
  
  export default Footer;