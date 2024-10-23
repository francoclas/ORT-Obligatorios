//Declaro mi variable constante con la direccion de mi api
const URLPrueba = "https://jsonplaceholder.typicode.com/comments";

const api = () => {
    fetch (url)
    .then((result)=>{
    return result.json()
    })
    .then((result)=>{
        //Ejemplo de recorrido para darle uso al result que recibi desde la api
        result.array.forEach(element => {
                console.log(element)
        })
    })
}

//Uso de Async, para esperar el resultado y luego poder operar
const URLPruebaAsync = "https://jsonplaceholder.typicode.com/comments";

//Se utiliza "async" en la declaracion
const apiAsync =  async() => {
    const respuesta = await fetch (url)
    //Hasta que finalice fetch no continua la ejecucion
    const resultado = await respuesta.json()
    resultado.forEach (caso =>{console.log(caso)})
}
