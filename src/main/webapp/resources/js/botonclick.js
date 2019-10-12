var botonEnter = document.getElementById("form-customer:fname");

botonEnter.addEventListener("keyup", function (evento) {
    evento.preventDefault();

    if (evento.keyCode === 13)
    {
        document.getElementById("form-customer:save").click();
        //document.getElementById("Save").click();
    }
});

