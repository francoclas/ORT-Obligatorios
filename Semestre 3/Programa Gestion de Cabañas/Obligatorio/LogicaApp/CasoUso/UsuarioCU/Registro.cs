using LogicaAccesoDatos.Repo;
using LogicaNegocio.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaApp.CasoUso.UsuarioCU
{
    public class Registro
    {
        private RepoUsuarios _repo;

        public Registro(RepoUsuarios repo)
        {
            _repo = repo;
        }

        public void Nuevo(Usuario Usuario) {
            _repo.Add(Usuario);
        }
    }
}
