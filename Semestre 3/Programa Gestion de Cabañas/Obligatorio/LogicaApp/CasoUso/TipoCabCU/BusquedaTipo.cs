using LogicaAccesoDatos.Repo;
using LogicaNegocio.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaApp.CasoUso.TipoCabCU
{
    public class BusquedaTipo
    {
        private RepoTipoCabana _repo;
        public BusquedaTipo(RepoTipoCabana repo)
        {
            _repo = repo;
        }

        public IEnumerable<TipoCab> ListarTodos()
        {
            return _repo.GetAll();
        }
        public IEnumerable<TipoCab> BuscarNombre(string nom)
        {
            return _repo.BuscarNom(nom);
        }
        public TipoCab BuscarId(int id)
        {
            return _repo.Get(id);
        }
    }
}
 