using LogicaNegocio.Entidades;
using LogicaApp.CasoUso;
using LogicaAccesoDatos.Repo;
using LogicaApp.CasoUso.TipoCabCU;
using Microsoft.EntityFrameworkCore;
using LogicaApp.CasoUso.CabanaCU;
using LogicaApp.CasoUso.MantenimientoCU;
using LogicaApp.CasoUso.UsuarioCU;
using Microsoft.AspNetCore.Session;
using Microsoft.OpenApi.Models;
using System.Reflection;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using System.Text.Json.Serialization;

var builder = WebApplication.CreateBuilder(args);


// Ignoro las dependencias circulares del json
builder.Services.AddControllers().AddJsonOptions(
    option =>
    option.JsonSerializerOptions.ReferenceHandler = ReferenceHandler.IgnoreCycles
    );

// Add services to the container.
builder.Services.AddDbContext<ProyContext>(options => options.UseSqlServer(builder.Configuration.GetConnectionString("conexionLibreria")));
builder.Services.AddControllersWithViews();
builder.Services.AddSession(options => options.IdleTimeout = TimeSpan.FromMinutes(30));

// Agrego Swagger
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(
    options =>
    {
        options.SwaggerDoc("v1", new OpenApiInfo
        {
            Version = "v1",
            Title = "ToDo API",
            Description = "An ASP.NET Core Web API for managing ToDo items",
            TermsOfService = new Uri("https://example.com/terms"),
            Contact = new OpenApiContact
            {
                Name = "Shayne Boyer",
                Email = string.Empty,
                Url = new Uri("https://twitter.com/spboyer"),
            },
        });
        var xmlFile = "Doc.xml";
        var xmlPath = Path.Combine(AppContext.BaseDirectory, xmlFile);
        options.IncludeXmlComments(xmlPath);
    }
);


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
builder.Services.AddScoped<AltaMantenimiento, AltaMantenimiento>();
builder.Services.AddScoped<BusquedaMantenimiento, BusquedaMantenimiento>();


builder.Services.AddAuthentication(aut =>
{
    aut.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
    aut.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
})
.AddJwtBearer(aut =>
{
    aut.RequireHttpsMetadata = false;
    aut.SaveToken = true;
    aut.TokenValidationParameters = new TokenValidationParameters
    {
        ValidateIssuerSigningKey = true,
        IssuerSigningKey = new SymmetricSecurityKey(Encoding.ASCII.GetBytes(Data.PrivateKey)),
        ValidateIssuer = false,
        ValidateAudience = false
    };
});

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();
var config = new ConfigurationBuilder()
    .AddJsonFile("info.json", optional: true, reloadOnChange: true)
    .Build();
Data.LimMinDescTipo = config.GetValue<int>("MinTipoCab");
Data.LimMaxDescTipo = config.GetValue<int>("MaxTipoCab");
Data.LimMinDescCab = config.GetValue<int>("MinCab");
Data.LimMaxDescCab = config.GetValue<int>("MaxCab");
Data.PrivateKey = config.GetValue<string>("privateKey");



// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(
        c =>
        {
            c.SwaggerEndpoint("/swagger/v1/swagger.json", "ObliFRGOV1");
        });
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
