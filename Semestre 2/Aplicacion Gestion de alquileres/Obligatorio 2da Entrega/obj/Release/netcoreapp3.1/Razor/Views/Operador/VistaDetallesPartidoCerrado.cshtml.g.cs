#pragma checksum "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "fb7f7d6cf564c607c1cdc85d2030e02addccc29f"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Operador_VistaDetallesPartidoCerrado), @"mvc.1.0.view", @"/Views/Operador/VistaDetallesPartidoCerrado.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\_ViewImports.cshtml"
using Obligatorio_2da_Entrega;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\_ViewImports.cshtml"
using Obligatorio_2da_Entrega.Models;

#line default
#line hidden
#nullable disable
#nullable restore
#line 1 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
using Biblioteca.Clases;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"fb7f7d6cf564c607c1cdc85d2030e02addccc29f", @"/Views/Operador/VistaDetallesPartidoCerrado.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"7177ed7dccaf7fbd065fb23baa70bbbd2fb4b9a1", @"/Views/_ViewImports.cshtml")]
    public class Views_Operador_VistaDetallesPartidoCerrado : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<Biblioteca.Partido>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral("\r\n");
#nullable restore
#line 4 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
  
    ViewData["Title"] = "VistaDetallesPartidoCerrado";

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n<h1>Detalles de partido ");
#nullable restore
#line 8 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
                   Write(Model.SalResena());

#line default
#line hidden
#nullable disable
            WriteLiteral("</h1>\r\n\r\n<div>\r\n    <h4>Partido</h4>\r\n    <hr />\r\n    <dl class=\"row\">\r\n        <dt class = \"col-sm-2\">\r\n            ");
#nullable restore
#line 15 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayNameFor(model => model.IdPartido));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dt>\r\n        <dd class = \"col-sm-10\">\r\n            ");
#nullable restore
#line 18 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayFor(model => model.IdPartido));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dd>\r\n        <dt class=\"col-sm-2\">\r\n            ");
#nullable restore
#line 21 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayNameFor(model => model.SeleccionLocal.Pais.Nombre));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dt>\r\n        <dd class=\"col-sm-10\">\r\n            ");
#nullable restore
#line 24 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayFor(model => model.SeleccionLocal.Pais.Nombre));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dd>\r\n        <dt class = \"col-sm-2\">\r\n            ");
#nullable restore
#line 27 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayNameFor(model => model.SeleccionVisitante.Pais.Nombre));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dt>\r\n        <dd class = \"col-sm-10\">\r\n            ");
#nullable restore
#line 30 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayFor(model => model.SeleccionVisitante.Pais.Nombre));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dd>\r\n        <dt class = \"col-sm-2\">\r\n            ");
#nullable restore
#line 33 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayNameFor(model => model.FechaPartido));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dt>\r\n        <dd class = \"col-sm-10\">\r\n            ");
#nullable restore
#line 36 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayFor(model => model.FechaPartido));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dd>\r\n        <dt class = \"col-sm-2\">\r\n            ");
#nullable restore
#line 39 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayNameFor(model => model.Resultado));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dt>\r\n        <dd class = \"col-sm-10\">\r\n            ");
#nullable restore
#line 42 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayFor(model => model.Resultado));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dd>\r\n        <dt class = \"col-sm-2\">\r\n            ");
#nullable restore
#line 45 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayNameFor(model => model.Ganador));

#line default
#line hidden
#nullable disable
            WriteLiteral("\r\n        </dt>\r\n        <dd class = \"col-sm-10\">\r\n            ");
#nullable restore
#line 48 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
       Write(Html.DisplayFor(model => model.Ganador));

#line default
#line hidden
#nullable disable
            WriteLiteral(@"
        </dd>
    </dl>
</div>

<div>
<h2>Seleccion Local</h2>
        <table>
            <thead>  
               <tr>
                   <th>Nombre</th>
                   <th>Jugadores</th>
               </tr>
            </thead>
            <tbody>
                <tr>
                    <td>");
#nullable restore
#line 64 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
                   Write(Html.DisplayFor(model => model.SeleccionLocal.Pais.Nombre));

#line default
#line hidden
#nullable disable
            WriteLiteral("</td>\r\n                    <td>");
#nullable restore
#line 65 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
                   Write(Html.DisplayFor(model => model.SeleccionLocal.Jugadoressalida));

#line default
#line hidden
#nullable disable
            WriteLiteral(@"</td>
                </tr>
            </tbody>
        </table>
        <hr />
        <br>
<hr />
</div>

<div>
    <h2>Seleccion visitante</h2>
        <table>
            <thead>
               <tr>
                   <th>Nombre</th>
                   <th>Jugadores</th>
               </tr>
            </thead>
            <tbody>
                <tr>
                <td>");
#nullable restore
#line 85 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
               Write(Html.DisplayFor(model => model.SeleccionVisitante.Pais.Nombre));

#line default
#line hidden
#nullable disable
            WriteLiteral("</td>\r\n                <td>");
#nullable restore
#line 86 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
               Write(Html.DisplayFor(model => model.SeleccionVisitante.Jugadoressalida));

#line default
#line hidden
#nullable disable
            WriteLiteral(@"</td>
                </tr>
            </tbody>
        </table>
<hr />
</div>
<div>
    
    <h2>Lista de incidencias</h2>
    <table>
        <thead>
            <tr>
                <th>Seleccion</th>
                <th>Jugador</th>
                <th>Minuto</th>
                <th>Descripcion</th>
            </tr>
        </thead>
        <tbody>
");
#nullable restore
#line 105 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
             foreach (var item in Model.ListaIncidencias)
                {

#line default
#line hidden
#nullable disable
            WriteLiteral("                    <tr>\r\n                        <td>");
#nullable restore
#line 108 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
                       Write(Model.ObtenerSeleccion(item.JugadorInci));

#line default
#line hidden
#nullable disable
            WriteLiteral("</td>\r\n                        <td>");
#nullable restore
#line 109 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
                       Write(item.JugadorInci.NomCompleto);

#line default
#line hidden
#nullable disable
            WriteLiteral("</td>\r\n                        <td>");
#nullable restore
#line 110 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
                       Write(item.Minuto);

#line default
#line hidden
#nullable disable
            WriteLiteral("</td>\r\n                        <td>");
#nullable restore
#line 111 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
                       Write(item.Desc);

#line default
#line hidden
#nullable disable
            WriteLiteral("</td>\r\n                    </tr>\r\n");
#nullable restore
#line 113 "C:\Users\frgomez\Desktop\ort\Semestre 2\programacion\Obligatorio 2\Obligatorio 2\Obligatorio 2da Entrega\Views\Operador\VistaDetallesPartidoCerrado.cshtml"
                }

#line default
#line hidden
#nullable disable
            WriteLiteral("        </tbody>\r\n    </table>\r\n\r\n\r\n</div>\r\n");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<Biblioteca.Partido> Html { get; private set; }
    }
}
#pragma warning restore 1591
