import React from 'react';
import ilustracion404 from '../../assets/404-illustration.svg';
import ilustracionConstruccion from '../../assets/pagina_pendiente.svg';
import ilustracionSesion from '../../assets/login_necesario.svg';
import { Link } from 'react-router-dom';
import { RUTAS } from '../../datos/rutas';

interface Props {
    tipo: '404' | 'construccion' | 'sesion';
    contexto?: string; //Por si queremos añadir un indicador de donde se ha producido el error
}

const PaginaEspecial: React.FC<Props> = ({tipo, contexto}) => {

    let mensaje = <></>;
    let ilustracion = '';

    if (tipo === '404') {
        mensaje = <>Lo sentimos, parece que esta página no existe o ha sido eliminada.</>;
        ilustracion = ilustracion404;
    } else if (tipo === 'construccion') {
        mensaje = <>¡Necesitamos un respiro! <br /> Esta funcionalidad aún no está disponible.<br />¡Vuelve pronto!</>;
        ilustracion = ilustracionConstruccion;
    } if (tipo === 'sesion') {
        mensaje = <>¡Ups! Parece que no tienes permisos para acceder a esta página.<br />Inicia sesión para continuar.</>;
        ilustracion = ilustracionSesion;
    }

    return (
        <section className='contenedor-pagina-especial'>
            <div className='item-flex'>
            {contexto &&
                <p className='contexto'>{contexto}</p>
            }
            <h1 className='subtitulo'>{mensaje}</h1>
            {tipo === 'sesion' ? 
                <Link className='btn-primary btn-borde' to={RUTAS.login} >Iniciar sesión</Link> 
                : 
                <Link className='btn-primary btn-borde' to={RUTAS.home} >Volver a la página principal</Link>
            }
            </div>
            <img src={ilustracion} alt={''}></img>
            
        </section>
    );
};

export default PaginaEspecial;