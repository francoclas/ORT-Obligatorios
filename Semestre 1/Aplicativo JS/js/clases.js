/*Se plantea "clases.js" como el contenedor principal de las respectivas clases del sistema, a su vez constructores y funciones */
class Local {
    constructor(idLocal,nombreLocal,usuarioLocal,contrasena,tipo,direccion,cupoMax,estaHabilitado){
        this.idLocal = idLocal;
        this.nombreLocal = nombreLocal;
        this.usuarioLocal = usuarioLocal;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.direccion = direccion;
        this.cupoMax = parseInt(cupoMax);
        this.cupoActual = parseInt(cupoMax);
        this.foto = idLocal;
        this.estaHabilitado = estaHabilitado;
    }

    modificarCupo(cupo){
        this.cupoMax = cupo;
    }
    actualizarCupoActual(cupo){
        if(cupo>this.cupoActual){
            return false;
        }else{
            this.cupoActual += cupo;
            if(this.cupoActual === 0){
                this.modificarHabilitacion(false);
                console.log("Se deshabilito reservas, no hay cupo.")
            }else
            return true;
        }
    }
    modificarHabilitacion(estado){
        this.estaHabilitado = estado;
    }
    verificarCredencialesLocal(usuario,contra){
        if(this.usuarioLocal === usuario && this.contrasena === contra){
            return true
        }else{
            return false
        }
    }
}
class Cliente {
    constructor(idCliente,nombreCliente,usuarioCliente,contrasena){
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.usuarioCliente = usuarioCliente;
        this.contrasena = contrasena;
    }
    verificarCredencialesCliente(usuario,contra){
        if(this.usuarioCliente === usuario && this.contrasena === contra){
            return true
        }else{
            return false
        }
    }
    cambioContrasena(contra){
        this.contrasena = contra;
    }

}
class Reserva {
    constructor(local,cliente,idReserva,cupoReserva){
        this.local = local;
        this.cliente = cliente;
        this.idReserva = idReserva;
        this.cupoReserva = parseInt(cupoReserva);
        this.puntuacion = 0;
        this.resena = "";
        this.estado = "Pendiente";
    }

    enviarCalificacion(puntuacion,resena) {
        if(this.puntuacion != 0 || this.resena != ""){
            console.log("Ya esta calificada")
        }else {
            this.puntuacion = puntuacion;
        this.resena = resena;
        console.log("Se califico reserva")
        }
    }

    cancelarReserva(){
        this.estado = "Cancelado";
        console.log("Se cancelo reserva")
    }
    finReserva(){
        this.estado = "Finalizado";
        console.log("Se finalizo reserva");
    }
}