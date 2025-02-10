import React from 'react'
import Modulo from '../modulo'

const Registro = () => {
  return (
    <div>
        <Modulo nombre={"Ingrese nuevo usuario"}tipo={"text"}/>
        <Modulo nombre={"Ingrese su contraseña"}tipo={"password"}/>
        <Modulo nombre={"Vuelva a ingresar la contraseña"}tipo={"password"}/>

        
    </div>
  )
}

export default Registro
