import React, { useRef } from 'react'

const modulo = ({nombre,tipo}) => {
    const valor = useRef();
    return (
    <div>
        <label htmlFor={valor}>{nombre}</label>
        <input type={tipo} name={valor} id={valor} />
    </div>)
}
export default modulo
