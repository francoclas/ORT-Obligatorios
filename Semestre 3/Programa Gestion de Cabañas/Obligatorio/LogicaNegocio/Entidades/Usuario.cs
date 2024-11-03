using LogicaNegocio.InterfacesDom;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.RegularExpressions;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using LogicaNegocio.VO;

namespace LogicaNegocio.Entidades
{
    public class Usuario : IValidable, IEntity
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Pass { get; set; }
        public string Correo { get; set; }
        public User User { get; set; }
        public void Validar()
        {
            ValNom();
            ValPass();
            ValCorreo();
            ValUser();
        }
        private void ValNom() {
            if (string.IsNullOrEmpty(Nombre))
                throw new Exception("El nombre no puede ser vacio");
        }
        private void ValPass() {
            if (!Regex.IsMatch(Pass, @".{6,}"))
                throw new Exception("La contraseña debe tener mas de 6 caracteres");
            if (!Regex.IsMatch(Pass, @"[a-z]"))
                throw new Exception("La contraseña debe contener almenos un caracter en minuscula.");
            if (!Regex.IsMatch(Pass, @"[A-Z]"))
                throw new Exception("La contraseña debe contener almenos un caracter en mayuscula.");
            if (!Regex.IsMatch(Pass, "[!@#$%^&*()_+{}\\[\\]:;'\"\\\\|,.<>/?~`\\-=]"))
                throw new Exception("La contraseña debe contar con un caracter especial Por ejemplo ¨@$!%*?&¨.");
        }
        private void ValCorreo()
        {
            int I = Correo.IndexOf('@');
            if (!(I > 0 && I < Correo.Length - 1))
                throw new Exception("El correo no cuenta con el formato adecuado");
        }
        private void ValUser() {
            if (string.IsNullOrEmpty(Correo))
                throw new Exception("El nombre no puede ser vacio");
        }

    }
}
