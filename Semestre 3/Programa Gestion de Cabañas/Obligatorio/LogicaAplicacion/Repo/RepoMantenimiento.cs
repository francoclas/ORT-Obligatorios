using LogicaNegocio.Entidades;
using LogicaNegocio.InterfacesRepo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaAccesoDatos.Repo
{
    public class RepoMantenimiento : IRepositorioMantenimiento
    {
        private ProyContext _contex;
        public RepoMantenimiento(ProyContext contex)
        {
          _contex = contex;
        }

        public void Add(Mantenimiento obj)
        {
            if (obj == null)
            {
                throw new ArgumentNullException(nameof(obj));
            }
            obj.Validar();
            if (TieneLimiteMantenimientos(obj.CabanaId, obj.Fecha))
                throw new Exception("Ya se registraron 3 mantenimientos para esta cabaña el dia solicitado");
            _contex.Mantenimientos.Add(obj);
            _contex.SaveChanges();
        }
        private bool TieneLimiteMantenimientos(int IdCabana,DateTime Fecha)
        {
            var Lista = _contex.Mantenimientos.ToList();
            var Salida = Lista.Where(M => M.CabanaId == IdCabana).ToList();
            Salida = Salida.Where(M => M.Fecha.Date == Fecha.Date).ToList();
            if (Salida.Count() < 3)
                return false;
            else
                return true;  
        }
        public void Delete(int id)
        {
            var mant = Get(id);
            if (mant == null)
                throw new ArgumentNullException(nameof(id));
            _contex.Mantenimientos.Remove(mant);
            _contex.SaveChanges() ;
        }

        public Mantenimiento Get(int id)
        {
            if (id == 0)
            {
                throw new Exception("No se recibio el id");
            }
            var mant = _contex.Mantenimientos.FirstOrDefault(mant => mant.Id == id);
            if (mant == null)
                throw new Exception("No se encontro mantenimiento con ese id");
            return mant;
        }

        public IEnumerable<Mantenimiento> GetAll()
        {
            return _contex.Mantenimientos.ToList();
        }

        public void Update(Mantenimiento obj)
        {
            var mant = Get(obj.Id);
            mant.Desc = obj.Desc;
            mant.Costo = obj.Costo;
            _contex.SaveChanges();
            

        }

        public IEnumerable<Mantenimiento> BuscarFecha(DateTime F1,DateTime F2, int idCabana) {
            if (idCabana == 0)
                throw new Exception("No se recibio la cabaña");
            var Lista = _contex.Mantenimientos.ToList();
            Lista = Lista.Where(M => M.CabanaId == idCabana).ToList();
            var Salida = Lista.Where(M => M.Fecha >= F1 && M.Fecha <= F2);
            if (Salida.Count() == 0)
                throw new Exception("No se encontraron resultados");
            return Salida.OrderByDescending(P=> P.Costo);
        }

        public IEnumerable<Mantenimiento> ListarTodosCabana(int idCabana)
        {
            if (idCabana == 0)
                throw new Exception("No se recibio la cabaña");
            var Lista = _contex.Mantenimientos.ToList();
            var Salida = Lista.Where(M => M.CabanaId == idCabana).ToList();
            if (Salida.Count() == 0)
                throw new Exception("No se encontraron resultados");
            return Salida;
        } 
    }
}
