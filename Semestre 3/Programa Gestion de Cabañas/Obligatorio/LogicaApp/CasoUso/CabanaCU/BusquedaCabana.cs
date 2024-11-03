using LogicaAccesoDatos.Repo;
using LogicaNegocio.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaApp.CasoUso.CabanaCU
{
    public class BusquedaCabana
    {
        private RepoCabana _repo;
        public BusquedaCabana(RepoCabana repo)
        {
            _repo = repo;
        }
        public Cabana BuscarCabana(int Id)
        {
            return _repo.Get(Id);
        }
        public IEnumerable<Cabana> ListarTodas() {
            return _repo.GetAll();
        }
        public IEnumerable<Cabana> BuscarNombre(string nom) {
            return _repo.BusNombre(nom);
        }

        public IEnumerable<Cabana> BuscarTipo(int Tipo) {
            return _repo.BuscarTipo(Tipo);
        }

        public IEnumerable<Cabana> BuscarCantMax(int C)
        {
            return _repo.BuscarCMax(C);
        }
        public IEnumerable<Cabana> BuscarHabilitadas()
        {
            return _repo.BuscarHabilitadas();
        }
    }
}
