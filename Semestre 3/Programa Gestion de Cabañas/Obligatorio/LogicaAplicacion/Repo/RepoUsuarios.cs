using LogicaNegocio.Entidades;
using LogicaNegocio.Excepciones;
using LogicaNegocio.InterfacesRepo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaAccesoDatos.Repo
{
    public class RepoUsuarios : IRepositorio<Usuario>
    {   
        private ProyContext _contex;
        public RepoUsuarios(ProyContext P) {
            _contex = P;
        }
        public void Add(Usuario obj)
        {
            if (obj == null)
            {
                throw new ArgumentNullException(nameof(obj));
            }           
            obj.Validar();
            if (VerificarCorreo(obj.Correo))
                throw new Exception("El correo ya se encuentra registrado");
            _contex.Usuarios.Add(obj);
            _contex.SaveChanges();

        }
        private bool VerificarCorreo(string Correo)
        {
            var lista = GetAll();
            return lista.Any(u => u.Correo == Correo);
        }
        public void Delete(int id)
        {
            throw new NotImplementedException();
        }

        public Usuario Get(int id)
        {
            if (id == 0)
            {
                throw new Exception("No se recibio el id");
            }
            var Us = _contex.Usuarios.FirstOrDefault(U => U.Id == id);
            if (Us == null)
                throw new Exception("No se encontro mantenimiento con ese id");
            return Us;
        }

        public IEnumerable<Usuario> GetAll()
        {
            return _contex.Usuarios.ToList();
        }

        public void Update(Usuario obj)
        {
            var usViejo = Get(obj.Id);
            if (usViejo == null)
                throw new Exception("No existe el usuario a modificar");
            usViejo.Correo = obj.Correo;
            usViejo.Pass = obj.Pass;
            obj.Validar();
            _contex.SaveChanges();
        }

        public bool ValidarCredenciales(string Correo, string Pass)
        {
            if (string.IsNullOrEmpty(Correo))
                throw new Exception("No se recibio correo");
            if (string.IsNullOrEmpty(Pass))
                throw new Exception("No se recibio contrasena");
            string passOG = BuscarCorreo(Correo);
            if (passOG != Pass)
                throw new Exception("Credenciales incorrectas");
            return true;

        }
        private string BuscarCorreo(string correo)
        {
            var lista = GetAll();
            Usuario U = lista.FirstOrDefault(u =>  u.Correo == correo);
            if (U == null)
                throw new Exception("Credenciales incorrectas");
            return U.Pass;
        }
        public Usuario BuscarUPorCorreo(string correo)
        {
            try
            {
                var lista = GetAll();
                if (string.IsNullOrEmpty(correo))
                    throw new DomainException("revise correo ingresado");
                
                Usuario U = lista.FirstOrDefault(u => u.Correo == correo);
                return U;
            }
            catch (Exception)
            {

                throw;
            }
            
        }
    }
}
