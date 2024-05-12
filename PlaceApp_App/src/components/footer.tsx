
import logo_PlaceApp from '../assets/logo_PlaceApp.svg';

function Footer() {
    return (
      <div className='footer-bg'>
      <footer>
        <div className="footer-col">
        <img src={logo_PlaceApp} alt="Logo" />
          <p>Calle de las Amapolas, 36. Madrid</p>
        </div>
        <div className="footer-col">
          <h3 className='titulo-footer'>¿Necesitas ayuda?</h3>
          <p>Contacto</p>
          <p>Preguntas frecuentes</p>
          <p>Texto legal</p>
        </div>
        <div className="footer-col">
          <h3 className='titulo-footer'>Accesos directos</h3>
          <p>Cómo funciona PlaceApp</p>
          <p>Ir a mi área personal</p>
        </div>
      </footer>
      </div>
    );
  }
  
  export default Footer;