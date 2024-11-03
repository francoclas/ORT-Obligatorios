using LogicaAccesoDatos.Repo;
using LogicaNegocio.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaApp.CasoUso.TipoCabCU
{
    public class EditarTipo
    {
        private RepoTipoCabana _repo;
        public EditarTipo(RepoTipoCabana repo)
        {
            _repo = repo;
        }   

        public void Editar(TipoCab T)
        {
            _repo.Update(T);
        }
    }
}
