using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;

namespace Cliente.Views.Filtro
{
    public class Filtro : ActionFilterAttribute
    {
        public override void OnActionExecuting(ActionExecutingContext context)
        {
            // Verificar si el usuario está autenticado
            if (context.HttpContext.Session.GetString("CorreoUsuario") == null)
            {

                // Si no está autenticado, redirigir a la página de inicio de sesión
                context.Result = new RedirectResult("/home/Login");
            }

            base.OnActionExecuting(context);

        }
    }
}
