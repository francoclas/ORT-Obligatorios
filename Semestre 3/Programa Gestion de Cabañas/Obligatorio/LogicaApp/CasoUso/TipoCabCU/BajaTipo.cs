using LogicaAccesoDatos.Repo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaApp.CasoUso.TipoCabCU
{
    public class BajaTipo 
    {
        private RepoTipoCabana _repo;
        public BajaTipo(RepoTipoCabana repo) { 
            _repo = repo;
        }

        public void Baja(int IdTipo)
        {
            _repo.Delete(IdTipo);
        }
    }
}
