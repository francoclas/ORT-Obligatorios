package sistemaAutogestion;

import java.util.Date;


public interface IObligatorio {
    
    /*
    **************** REGISTROS **************************************
    */
    
    //pre:      post:
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico);
     //pre:      post:
    public Retorno registrarMedico(String nombre,int codMedico,int tel, int especialidad); 
     //pre:      post:
    public  Retorno eliminarMedico(int codMedico); 
     //pre:      post:
    public Retorno agregarPaciente(String nombre, int CI, String direccion); 
     //pre:      post:
    public Retorno eliminarPaciente(int CI); 

     /*
    **************** GESTIÓN DE CONSULTAS **************************************
    */
      
    //pre:      post:
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha);
    //pre:      post:
    public Retorno cancelarReserva(int codMedico, int ciPaciente); 
    //pre:      post:
    public Retorno anunciaLlegada(int codMedico, int CIPaciente); 
    //pre:      post:
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta);   
    //pre:      post:
    public Retorno cerrarConsulta(String codMédico, Date fechaConsulta); 
    
 
      /*
    **************** LISTADO Y REPORTES **************************************
    */
    
    //pre:      post:
    public Retorno listarMédicos(); 
    //pre:      post:
    public Retorno listarPacientes();     
    //pre:      post:
    public Retorno listarConsultas(int codMedico); 
    //pre:      post:
    public Retorno listarPacientesEnEspera (String codMedico, Date fecha);    
    //pre:      post:
    public Retorno consultasPendientesPaciente(int CIPaciente);   
    //pre:      post:
    public Retorno historiaClínicaPaciente (int ci);    
    //pre:      post:
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año); 
     
}
