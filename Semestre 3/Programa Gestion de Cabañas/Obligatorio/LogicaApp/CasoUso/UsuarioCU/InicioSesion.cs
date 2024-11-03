using LogicaAccesoDatos.Repo;
using LogicaNegocio.Entidades;
using LogicaNegocio.VO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaApp.CasoUso.UsuarioCU
{
    public class InicioSesion
    {
        private RepoUsuarios _repo;
        public InicioSesion(RepoUsuarios repo) {
            _repo = repo;
        }

        public bool IniciarSesion(string Correo,string Pass)
        {
            return _repo.ValidarCredenciales(Correo,Pass);
        }
        public Usuario DevolverUsuario(string Correo,string Pass) {
            return _repo.BuscarUPorCorreo(Correo);
        }
    }
}
