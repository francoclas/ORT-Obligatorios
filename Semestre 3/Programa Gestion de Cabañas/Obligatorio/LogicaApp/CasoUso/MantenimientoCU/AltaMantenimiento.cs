using LogicaAccesoDatos.Repo;
using LogicaNegocio.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaApp.CasoUso.MantenimientoCU
{
    public class AltaMantenimiento
    {
        private RepoMantenimiento _repo;
        public AltaMantenimiento(RepoMantenimiento repo)
        {
            _repo = repo;
        }

        public void Nuevo(Mantenimiento M)
        {
            _repo.Add(M);
        }
    }
}
