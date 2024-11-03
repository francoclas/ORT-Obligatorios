using LogicaNegocio.Entidades;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaAccesoDatos.Config
{
    internal class NomConfig : IEntityTypeConfiguration<Cabana>
    {
        public void Configure(Microsoft.EntityFrameworkCore.Metadata.Builders.EntityTypeBuilder<Cabana> builder)
        {
            builder.OwnsOne(Cabana => Cabana.Nombre).Property(nombre => nombre.Valor).HasColumnName("Nombre");
        }
    }

}
