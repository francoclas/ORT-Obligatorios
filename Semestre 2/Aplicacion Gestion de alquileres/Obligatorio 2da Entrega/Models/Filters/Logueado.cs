using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;


namespace Obligatorio_2da_Entrega.Models.Filters
{
    public class Logueado : ActionFilterAttribute, IActionFilter
    {

        //Este método se va a ejecutar previo a entrar al endpoint o Action
        public override void OnActionExecuting(ActionExecutingContext context)
        {
            if (string.IsNullOrEmpty(context.HttpContext.Session.GetString("USERNAME")))
                context.Result = new RedirectToActionResult("Login", "Home", null);
        }

        //Este método se va a ejecutar después a entrar al endpoint o Action
        public override void OnActionExecuted(ActionExecutedContext context)
        {
            base.OnActionExecuted(context);
        }
    }
}
