using LogicaNegocio.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using LogicaNegocio.VO;
using LogicaAccesoDatos.Config;

namespace LogicaAccesoDatos.Repo
{
    public class ProyContext : DbContext
    {
       public DbSet<Cabana> Cabanas { get; set; }
        public DbSet<TipoCab> TipoCabs { get; set; }
        public DbSet<Mantenimiento> Mantenimientos { get; set; }
        public DbSet<Usuario> Usuarios { get; set; }
        public ProyContext (DbContextOptions options) : base (options) { }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new DescTipoConfig());
            modelBuilder.ApplyConfiguration(new NomConfig());
            modelBuilder.ApplyConfiguration(new TecnicoConfig());
            modelBuilder.ApplyConfiguration(new UserConfig());
            base.OnModelCreating(modelBuilder);
        }
        //protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        //{
        //    base.OnConfiguring(optionsBuilder);
        //    {
        //        base.OnConfiguring(optionsBuilder);
        //        optionsBuilder.UseSqlServer(@"Data Source = (localdb)\MSSQLLocalDB;Initial Catalog = BOFG; Integrated Security = True;");
        //    }
        //}

    }
}
