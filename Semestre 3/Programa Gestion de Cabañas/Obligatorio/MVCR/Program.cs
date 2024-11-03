using LogicaNegocio.Entidades;
using LogicaApp.CasoUso;
using LogicaAccesoDatos.Repo;
using LogicaApp.CasoUso.TipoCabCU;
using Microsoft.EntityFrameworkCore;
using LogicaApp.CasoUso.CabanaCU;
using LogicaApp.CasoUso.MantenimientoCU;
using LogicaApp.CasoUso.UsuarioCU;
using Microsoft.AspNetCore.Session;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddDbContext<ProyContext>(options => options.UseSqlServer(builder.Configuration.GetConnectionString("conexionLibreria")));
builder.Services.AddControllersWithViews();
builder.Services.AddSession(options => options.IdleTimeout = TimeSpan.FromMinutes(30));


//Builder Repos
builder.Services.AddScoped<RepoTipoCabana, RepoTipoCabana>();
builder.Services.AddScoped<RepoMantenimiento, RepoMantenimiento>();
builder.Services.AddScoped<RepoCabana, RepoCabana>();
builder.Services.AddScoped<RepoUsuarios, RepoUsuarios>();
//Builder Usuario
builder.Services.AddScoped<Registro, Registro>();
builder.Services.AddScoped<InicioSesion, InicioSesion>();
//Builder CUTipoCab
builder.Services.AddScoped<AltaTipoCab, AltaTipoCab>();
builder.Services.AddScoped<BajaTipo, BajaTipo>();
builder.Services.AddScoped<BusquedaTipo, BusquedaTipo>();
builder.Services.AddScoped<EditarTipo, EditarTipo>();

//Builder CUCabana
builder.Services.AddScoped<AltaCabana, AltaCabana>();
builder.Services.AddScoped<BusquedaCabana, BusquedaCabana>();

//Builder CuMantenimiento
builder.Services.AddScoped<AltaMantenimiento,AltaMantenimiento>();
builder.Services.AddScoped<BusquedaMantenimiento,BusquedaMantenimiento>();

var app = builder.Build();
// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}
var config = new ConfigurationBuilder()
    .AddJsonFile("info.json", optional: true, reloadOnChange: true)
    .Build();
Data.LimMinDescTipo = config.GetValue<int>("MinTipoCab");
Data.LimMaxDescTipo = config.GetValue<int>("MaxTipoCab");
Data.LimMinDescCab = config.GetValue<int>("MinCab");
Data.LimMaxDescCab = config.GetValue<int>("MaxCab");





app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");
app.UseSession();
app.Run();
