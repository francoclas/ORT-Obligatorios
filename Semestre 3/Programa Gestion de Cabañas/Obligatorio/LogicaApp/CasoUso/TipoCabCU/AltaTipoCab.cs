using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using LogicaAccesoDatos.Repo;
using LogicaNegocio.Entidades;

namespace LogicaApp.CasoUso.TipoCabCU
{
    public class AltaTipoCab
    {
        private RepoTipoCabana _repo;
        public AltaTipoCab(RepoTipoCabana repo)
        { _repo = repo; }

        public void Nuevo(TipoCab Ntc)
        {
            _repo.Add(Ntc);
        }

    }

}
