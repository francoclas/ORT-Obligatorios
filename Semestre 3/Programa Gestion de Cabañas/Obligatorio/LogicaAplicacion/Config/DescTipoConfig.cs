using LogicaNegocio.Entidades;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicaAccesoDatos.Config
{
    public class DescTipoConfig : IEntityTypeConfiguration<TipoCab>
    {

            public void Configure(Microsoft.EntityFrameworkCore.Metadata.Builders.EntityTypeBuilder<TipoCab> builder)
            {
                builder.OwnsOne(Tp => Tp.Desc).Property(Desc => Desc.Valor).HasColumnName("Desc");
            }
    }
}

