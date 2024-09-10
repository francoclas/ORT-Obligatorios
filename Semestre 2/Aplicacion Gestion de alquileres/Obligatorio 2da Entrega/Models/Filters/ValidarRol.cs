using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Obligatorio_2da_Entrega.Models.Filters
{
    public class ValidarRol : ActionFilterAttribute, IActionFilter
    {
        private string _rol;

        public ValidarRol(string rol)
        {
            _rol = rol;
        }

        public override void OnActionExecuting(ActionExecutingContext context)
        {
            if (context.HttpContext.Session.GetString("ROL") != _rol)
                context.Result = new RedirectToActionResult("Login", "Home", null);
        }
    }
    }
