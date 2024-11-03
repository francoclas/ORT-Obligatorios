using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using LogicaNegocio.Entidades;
using LogicaApp.CasoUso.TipoCabCU;
using LogicaAccesoDatos.Repo;
var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

//Builder Repos
builder.Services.AddScoped<RepoTipoCabana, RepoTipoCabana>();
builder.Services.AddScoped<RepoMantenimiento, RepoMantenimiento>();
builder.Services.AddScoped<RepoCabana, RepoCabana>();
builder.Services.AddScoped<RepoUsuarios, RepoUsuarios>();

//Builder CUTipoCab

builder.Services.AddScoped<AltaTipoCab, AltaTipoCab>();

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var config = new ConfigurationBuilder()
    .AddJsonFile("info.json", optional: true, reloadOnChange: true)
    .Build();
Data.LimMinDescTipo = config.GetValue<int>("MinTipoCab") ;
Data.LimMaxDescTipo = config.GetValue<int>("MaxTipoCab");
Data.LimMinDescCab = config.GetValue<int>("MinCab");
Data.LimMaxDescCab = config.GetValue<int>("MaxCab");


builder.Services.AddDbContext<ProyContext>(options => options.UseSqlServer(builder.Configuration.GetConnectionString("conexionLibreria")));
var app = builder.Build();


// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
