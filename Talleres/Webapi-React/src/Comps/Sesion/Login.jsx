import React, { useRef } from 'react'
import { useEffect, useState } from "react";
import { useSelector } from 'react-redux';
import { Link, useNavigate } from "react-router-dom"

const Login = () => {
  let navegador = useNavigate();
  const apiURl = "https://movetrack.develotion.com/";
  const usuario = useRef(null);
  const pass = useRef(null);
  
  //Inicio de sesion
  const iniciarSesion =() =>{
    //Guardo variables
    let us = usuario.current.value;
    let pa = pass.current.value;
    //Verifico valores ingresados
    //Contacto API
    fetch(apiURl + "usuarios.php",
      {
        method: 'POST',
        headers: {
          "Content-type": 'application/json',
          'usuario': us,
          'password': pa
        }
      }
    )
    .then(r => r.json())
    .then(datos => {
      //Guado en localstorage
      localStorage.setItem('UsuarioId',datos.id);
      localStorage.setItem('Token',datos.apiKey);
      //Redirecciono a menu
      navegador('/menu');
    })

  }
  return (
  <div>
    <h1>Inicio de sesion</h1>
    <div>
        <label htmlFor="txtUsuario">Usuario:</label>
        <input type="text" id="txtUsuario" ref={usuario} /><br />
        <label htmlFor="txtPass">Contraseña:</label>
        <input type="text" id="txtPass" ref={pass} /><br />
        <input type="button" value="Ingresar" onClick={iniciarSesion} />
    </div>
      <Link to="/registro">¿No tenes usuario? Crear una cuenta</Link>
  </div>
  )
}

export default Login
