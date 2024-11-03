using LogicaAccesoDatos.Repo;
using LogicaNegocio.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaApp.CasoUso.MantenimientoCU
{
    public class BusquedaMantenimiento
    {
        private RepoMantenimiento _repo;
        public BusquedaMantenimiento(RepoMantenimiento repo)
        {
            _repo = repo;
        }

        public IEnumerable<Mantenimiento> ListarMantenimientosFechas(DateTime F1,DateTime F2, int IdCabana) {
            return _repo.BuscarFecha(F1, F2, IdCabana);
        }
        public IEnumerable<Mantenimiento> ListarMantenimientosDeCabana(int IdCabana)
        {
            return _repo.ListarTodosCabana(IdCabana);
        }
        public IEnumerable<Mantenimiento> ListarTodo()
        {
            return _repo.GetAll();
        }
    }
}
