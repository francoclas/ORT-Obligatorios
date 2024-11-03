using LogicaNegocio.Entidades;
using LogicaNegocio.InterfacesRepo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel;
using LogicaNegocio.Excepciones;
using LogicaAccesoDatos.Excepciones;

namespace LogicaAccesoDatos.Repo
{
    public class RepoCabana : IRepositorioCabana
    {
        private ProyContext _contex;
        public RepoCabana(ProyContext proyContext)
        {
            _contex = proyContext;
        }

        public void Add(Cabana obj)
        {
            try
            {
                if (obj == null)
                {
                    throw new ADException(nameof(obj));
                }
                obj.Validar();
                _contex.Cabanas.Add(obj);
                _contex.SaveChanges();
            }
            catch (ADException A)
            {

                throw A; 
            }catch (Exception)
            {
                throw new ADException("Error interno");
            }
            
        }

        public IEnumerable<Cabana> BuscarCMax(int c)
        {
            if (c == 0)
                throw new ADException("No se recibio capacidad minima para buscar");
            var lista = _contex.Cabanas.ToList();
            var salida = lista.Where(cl => cl.CantPersMax >= c).ToList();
            if (salida.Count() == 0)
                throw new NotFoundException("No se encontraron resultados");
            return salida;
        }

        public IEnumerable<Cabana> BuscarHabilitadas()
        {
            var lista = _contex.Cabanas.ToList();
            var salida = lista.Where(c => c.HayReserva == true).ToList();
            if (salida.Count == 0)
                throw new NotFoundException("No se encontraron resultados");
            return salida;
        }

        public IEnumerable<Cabana> BuscarTipo(int tipo)
        {
            if (tipo == 0)
                throw new ADException("No se recibio tipo");
            var lista = _contex.Cabanas.ToList();
            var salida = lista.Where(c => c.TipoCabId == tipo).ToList();
            if (salida.Count == 0)
                throw new NotFoundException("No se encontraron resultados");
            return salida;
        }

        public IEnumerable<Cabana> BusNombre(string nom)
        {
            if (string.IsNullOrEmpty(nom))
                throw new ADException("No se recibio el nombre");
            var lista = _contex.Cabanas.ToList();
            var salida = lista.Where(c => c.Nombre.Valor.Contains(nom)).ToList();
            if (salida.Count == 0)
                throw new NotFoundException("No se encontraron resultados");
            return salida;
        }

        public void Delete(int id)
        {
            throw new NotImplementedException();
        }

        public Cabana Get(int id)
        {
            if (id == 0)
            {
                throw new ADException("No se recibio el id");
            }
            var cabana = _contex.Cabanas.FirstOrDefault(cabana => cabana.Id == id);
            if (cabana == null)
            {
                throw new NotFoundException("No se encontro el id");
            }
            return cabana;
        }

        public IEnumerable<Cabana> GetAll()
        {
            try
            {
                return _contex.Cabanas.ToList();
            }
            catch (Exception)
            {

                throw new NotFoundException("No se encontraron resultados");
            }
            
        }

        public void Update(Cabana obj)
        {
            throw new NotImplementedException();
        } 
    }
}
