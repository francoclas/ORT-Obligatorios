import React from 'react'
import Inicio from './Inicio'
import Modulo from '../modulo'
const Login = () => {
  return (
    <div>
        <Modulo nombre={"Ingrese usuario: "}tipo={"text"}/>
        <Modulo nombre={"Ingrese contraseña: "}tipo={"password"}/>
        <Inicio/>
    </div>
  )
}

export default Login
