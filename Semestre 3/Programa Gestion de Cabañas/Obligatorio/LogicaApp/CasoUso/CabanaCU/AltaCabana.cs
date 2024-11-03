using LogicaAccesoDatos.Repo;
using LogicaNegocio.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaApp.CasoUso.CabanaCU
{
    public class AltaCabana
    {
        private RepoCabana _repo;
        public AltaCabana(RepoCabana repo)
        {
            _repo = repo;
        }

        public void Nuevo(Cabana C) {
            _repo.Add(C);      
        }
        
       
    }
}
