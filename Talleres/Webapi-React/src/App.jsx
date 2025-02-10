import { useState } from 'react'
import LoginPers from './Comps/Sesion/Login'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <LoginPers/>
    </>
  )
}

export default App
