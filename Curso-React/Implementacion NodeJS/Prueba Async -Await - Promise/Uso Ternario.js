//variable para prueba
const IsLogin = true

//Planteo de uso de ternario
//Si esta logeado va por la primer parte, los ":" separan las dos posibles respuestas.
IsLogin ? 
    //Caso verdadero
    console.log("Esta logeado"):
    //Caso falso
    console.log("Usuario no esta logeado")

const saldo = 700, pagar = 600, paypal = true

saldo > pagar ? 
    console.log("Puede pagar el curso"):
    console.log("No puede pagar el curso")
    
