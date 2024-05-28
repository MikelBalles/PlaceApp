import React from 'react';
import ilusHome from '../../assets/infografia_home.svg';
import ilusPropietario from '../../assets/ilus-perfil-propietario.svg';
import ilusCliente from '../../assets/ilus-perfil-usuario.svg';
import { useNavigate } from 'react-router-dom';
import TarjetaPerfilHome from '../partes/TarjetaPerfilHome';
import { RUTAS } from '../../datos/rutas';
import '../styles/estiloLanding.scss';
import ItemDuda from '../partes/ItemDuda';

const LandingPage: React.FC = () => {
    const navigate = useNavigate();
    return (
        <>
        <section className='info-ppal contenedor-flex-hor'>
            <div className='col-flex'>
                <img src={ilusHome} alt='' />
            </div>
            <div className='col-flex texto-landing seccion-landing contenedor-flex-ver'>
                <h1 className='titulo-page'>¡Bienvenido a PlaceApp!</h1>
                <p className='subtitulo'>Bienvenido a <span className='enfasis-home'>PlaceApp</span>, la plataforma que pone en la palma de tu mano todos tus espacios cercanos, y te permite centralizar las reservas</p>
                <button className='btn-primary btn-borde' onClick={()=> {navigate('/cliente')}}>Quiero empezar ahora</button>
            </div>
        </section>

        <section className='seccion-landing como-funciona contenedor-flex-ver'>
            <h2 className='titulo-1'>Cómo funciona PlaceApp</h2>
            <p className='subtitulo'>Conectamos a propietarios y a usuarios, con la intención de facilitar los procesos y unificar en un único lugar la gestión de reservas de tus espacios favoritos. </p>
            <div className='contenedor-perfiles contenedor-flex-hor'>
                <TarjetaPerfilHome titulo='¿Eres propietario?'
                 imagen={ilusPropietario}
                 texto='¿Tienes  espacios disponibles para alquilar? Ponemos a tu disposición una aplicación desde la que poder centralizar todas tus reservas:'
                 ventajas={['Digitaliza y unifica la gestión de tus espacios con una única aplicación',
                           'Acceso a la plataforma de reservas sin necesidad de una inversión en un sistema propio',
                            'Ofrece a tus usuarios una experiencia digital única']}
                 ruta={RUTAS.login} />
                <TarjetaPerfilHome titulo='¿Eres usuario?'
                imagen={ilusCliente}
                texto='No es necesario crear una cuenta para cada uno de los espacios que quieres reservar. Todo se realiza desde el mismo lugar:'
                ventajas={['Consulta todo tipo de espacios disponibles para reservar',
                           'Todas tus reservas desde un único lugar',
                           'Una cuenta única para todo lo que necesites']}
                 ruta={RUTAS.login}
                />
            </div>
        </section>
        <section className='seccion-landing preguntas-frecuentes contenedor-flex-ver'>
            <h2 className='titulo-1'>Preguntas frecuentes</h2>
            <p className='subtitulo'>¿Hay algo que no ha quedado claro? Estas son algunas de las dudas más frecuentes de nuestros usuarios</p>
            <div className='contenedor-dudas'>
                <ItemDuda 
                    numero="01" 
                    titulo="¿Qué tipos de espacio puedo encontrar o registrar?" 
                    texto="En PlaceApp puedes encontrar gran variedad de espacios. Desde espacios destinados a la práctica de deportes hasta salas privadas para realizar una gran celebración. Nuestra plataforma cuenta con unos filtros que te permiten categorizar el espacio que quieres registrar o el tipo de evento para el que quieres realizar una reserva."
                />
                <ItemDuda 
                    numero="02" 
                    titulo="¿Cómo puedo realizar una reserva?" 
                    texto="Para realizar una reserva en PlaceApp, primero debes registrarte como usuario. Luego, puedes utilizar nuestro buscador para encontrar el espacio que deseas, seleccionar la fecha y hora disponibles, y confirmar la reserva a través de nuestra plataforma. Recibirás una confirmación por correo electrónico con todos los detalles de tu reserva."
                />
                <ItemDuda 
                    numero="03" 
                    titulo="¿Qué métodos de pago aceptan?" 
                    texto="En PlaceApp aceptamos una variedad de métodos de pago para tu comodidad, incluyendo tarjetas de crédito y débito, PayPal y transferencias bancarias. Nuestro sistema de pago es seguro y garantiza la protección de tus datos financieros durante toda la transacción."
                />
                <ItemDuda 
                    numero="04" 
                    titulo="¿Cómo puedo registrar mi espacio?" 
                    texto="Registrar tu espacio en PlaceApp es sencillo. Primero, crea una cuenta como propietario. Luego, ingresa a tu panel de control y selecciona 'Agregar Nuevo Espacio'. Completa la información requerida sobre tu espacio, incluyendo la descripción, fotos, disponibilidad y tarifas. Una vez enviado, nuestro equipo revisará tu solicitud y, tras la aprobación, tu espacio estará listo para recibir reservas."
                />
            </div>
        </section>

        <section className='seccion-landing contacto contenedor-flex-hor'>
            <h2 className='titulo-1'>Contacta con nosotros</h2>
        </section>
        </>
    );
};

export default LandingPage;