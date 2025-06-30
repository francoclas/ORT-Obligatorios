import { NavLink, Outlet } from "react-router-dom"

const Contenedor = () => {
  return (
    <div className="container">
        <header className="row">
            <nav>
                <NavLink to="/">Login</NavLink> - &nbsp;
                <NavLink to="/Registro">Registro</NavLink> - &nbsp;
            </nav>
        </header>
        <main className="row">
            <Outlet />
        </main>
    </div>
  )
}

export default Contenedor