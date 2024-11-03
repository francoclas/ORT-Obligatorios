using LogicaAccesoDatos.Excepciones;
using LogicaNegocio.Entidades;
using LogicaNegocio.Excepciones;
using LogicaNegocio.InterfacesRepo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaAccesoDatos.Repo
{
    public class RepoTipoCabana : IRepositorioTipoCab
    {
        private ProyContext _contex;
        private RepoCabana _repoCabana;
        public RepoTipoCabana(ProyContext contex, RepoCabana repoCabana)
        {
            _contex = contex;
            _repoCabana = repoCabana;
        }

        public void Add(TipoCab obj)
        {
            if (obj == null) throw new ArgumentNullException(nameof(obj));
            obj.Validar();
            _contex.Add(obj);
            _contex.SaveChanges();
        }


        public IEnumerable<TipoCab> BuscarNom(string nom)
        {
            if (string.IsNullOrEmpty(nom))
                throw new DomainException("No se recibio nombre");
            IEnumerable<TipoCab> todos = GetAll();
            IEnumerable<TipoCab> TiposConNom = todos.Where(T => T.Nombre.Contains(nom.Trim())).ToList();
            if (TiposConNom.Count() == 0)
                throw new NotFoundException("No se encontraron resultados");
            return TiposConNom;
        }

        public void Delete(int idtipo)
        {
            if (idtipo == 0)
                throw new DomainException("No se recibio ID");
            bool TieneHijas = _contex.Cabanas.Any(c => c.Id == idtipo);
            if (TieneHijas)
            {
                throw new ADException("No se puede eliminar el tipo si tiene cabañas asociadas.");
            }
            else
            {
                TipoCab obj = new TipoCab() { Id = idtipo };
                _contex.TipoCabs.Attach(obj);
                _contex.TipoCabs.Remove(obj);
                _contex.SaveChanges(true);
            }

        }
        public TipoCab Get(int id)
        {
            if (id == 0)
                throw new DomainException("No se recibio ID");
            var Tipo = _contex.TipoCabs.FirstOrDefault(c => c.Id == id);
            if (Tipo == null)
                throw new NotFoundException("No se encontraron resultados.");
            return Tipo;
        }

        public IEnumerable<TipoCab> GetAll()
        {
            return _contex.TipoCabs.ToList();
        }

        public void Update(TipoCab t)
        {
            try
            {
                var Tipo = Get(t.Id);
                if (Tipo == null) 
                    throw new NotFoundException("No se encontro Tipo a cambiar");
                Tipo.Desc = t.Desc;
                Tipo.CostoP = t.CostoP;
                Tipo.Validar();
                _contex.SaveChanges();

            }
            catch (Exception)
            {

                throw;
            }
        }
    }
}
