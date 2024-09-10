using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaNegocio.InterfacesRepo
{
    public interface IRepositorio<T>
    {
        //Funciones de CRUD
        public void Add(T obj);
        public void Update(T obj);
        public void Delete(int id);
        public T Get(int id);
        public IEnumerable<T> GetAll();
    }
}
