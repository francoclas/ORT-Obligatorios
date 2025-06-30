//Funcionalidades
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { useState } from 'react'
import { store } from './store/store'

//Componentes
import './App.css'

import Contenedor from './Comps/App/Contenedor'
import Registro from './Comps/Sesion/Registro'
import Login from './Comps/Sesion/Login'
import MenuApp from './Comps/App/Menu'

const App = () => {


  return (
    <BrowserRouter>
    <Routes>
        <Route path = "/Login" element = {<Login/>}/>
        <Route path = "/Registro" element = {<Registro/>}/>
        <Route path = "/Menu" element = {<MenuApp/>}/>
    </Routes>
  </BrowserRouter>

  
  )
}

export default App
