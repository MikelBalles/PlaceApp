export interface objSesion {
    username: string;
    role: string;
}

export interface sesionIniciada {
    sesionIniciada: boolean;
    idPerfil : number;
    nombrePerfil : string;
    username : string;
    nombreUsuario: string;
}

export interface modeloInputs {
    name: string;
    value: string;
    esValido: boolean;
}

export interface espacioDto {
    idEspacio: number,
    nombreEspacio: string,
    descripcion: string,
    cp: number,
    direccion: string,
    provincia: string,
    subtipo: number,
    precio: number,
    nombreUsername: string,
    telefonoUsername: number,
    correoUsername: string,
    extras: extraDto[];
}
export interface reservaEspacioDto {
    idEspacio: number,
    usernameReserva: string,
    precioVenta: number,
    fechaInicioReserva: string,
    fechaFinReserva: string,
    observacionReserva: string,

}
export interface tipoSubtipoDto   {
    idTipo :number,
    nombreTipo : string,
    idSubtipo: number,
    nombreSubtipo : string,
}
export interface tipoDto   {
    id:number,
    nombre: string,
}
export interface subtipoDto {
    id: number,
    nombre: string,
}
export interface extraDto {
    idExtra: number,
    nombre: string,
    precio: number,
    descripcion: string
}

export interface Reserva {
    fechaInicioReserva: string,
    fechaFinReserva: string
 }

 export interface horaConDisponibilidad {
    horaDate: Date,
    disponible: boolean;
 }
export interface provinciaDto {
    idProv: number,
    nombre: string,
}

export interface espacioClienteDto {
    idEspacio: number,
    nombre: string,
    descripcion: string,
    direccion: string,
    precio: number,
    idSubtipo: number,
}

export interface reservaUsuarioClienteDto {
    idReserva: number,
    nombreEspacio: string,
    observacionesReserva: string,
    fechaInicio: string,
    fechaFin: string,
    precioVenta: number,
    idSubtipo: number,
}
    
