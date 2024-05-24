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
export interface tipoSubtipoDto   {
    idTipo :number,
    nombreTipo : string,
    idSubtipo: number,
    nombreSubtipo : string,

}
export interface extraDto {
    idExtra: number,
    nombre: string,
    precio: number,
    descripcion: string
}


