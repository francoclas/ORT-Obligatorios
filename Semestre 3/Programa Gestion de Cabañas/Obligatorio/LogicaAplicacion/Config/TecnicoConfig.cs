using LogicaNegocio.Entidades;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaAccesoDatos.Config
{
    internal class TecnicoConfig : IEntityTypeConfiguration<Mantenimiento>
    {
        public void Configure(Microsoft.EntityFrameworkCore.Metadata.Builders.EntityTypeBuilder<Mantenimiento> builder)
        {
            builder.OwnsOne(Mant => Mant.Tecnico).Property(Tecnico => Tecnico.Valor).HasColumnName("Tecnico");
        }
    }
}
