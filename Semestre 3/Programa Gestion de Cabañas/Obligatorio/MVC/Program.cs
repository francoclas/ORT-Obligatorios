using LogicaAccesoDatos.Repo;
using LogicaApp.CasoUso.TipoCabCU;
using LogicaNegocio.Entidades;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();
//Builder Repos
builder.Services.AddScoped<RepoTipoCabana, RepoTipoCabana>();
builder.Services.AddScoped<RepoMantenimiento, RepoMantenimiento>();
builder.Services.AddScoped<RepoCabana, RepoCabana>();
builder.Services.AddScoped<RepoUsuarios, RepoUsuarios>();

//CasoUso TipoCab
builder.Services.AddScoped<AltaTipoCab, AltaTipoCab>();
//CasoUso Cabana


builder.Services.AddControllers();


var config = new ConfigurationBuilder()
    .AddJsonFile("info.json", optional: true, reloadOnChange: true)
    .Build();
Data.LimMinDescTipo = config.GetValue<int>("MinTipoCab");
Data.LimMaxDescTipo = config.GetValue<int>("MaxTipoCab");
Data.LimMinDescCab = config.GetValue<int>("MinCab");
Data.LimMaxDescCab = config.GetValue<int>("MaxCab");


builder.Services.AddDbContext<ProyContext>(options => options.UseSqlServer(builder.Configuration.GetConnectionString("conexionLibreria")));
var app = builder.Build();
// Configure the HTTP request pipeline.


app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=TC}/{action=Alta}");

app.Run();