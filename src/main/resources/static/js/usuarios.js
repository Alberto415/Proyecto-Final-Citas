const baseUrl = "http://localhost:8080/api/usuarios";

// Mostrar resultado en el div
function mostrarResultado(msg, error=false) {
  const div = document.getElementById("resultado");
  div.style.display = "block";
  div.className = error ? "alert alert-danger mt-4" : "alert alert-success mt-4";
  div.innerText = msg;
}

// Leer (GET)
function buscarUsuario() {
  const id = document.getElementById("usuarioInput").value;
  fetch(`${baseUrl}/${id}`)
    .then(res => {
      if (!res.ok) throw new Error("Usuario no encontrado");
      return res.json();
    })
    .then(user => {
      const u = Array.isArray(user) ? user[0] : user;
      mostrarResultado(`ID: ${u.idUsuario}, Nombre: ${u.nombre}, Matrícula: ${u.matricula}, Correo: ${u.correo}, Teléfono: ${u.telefono}, Tipo: ${u.tipoUsuario}, Expediente: ${u.idExpediente}`);
    })
    .catch(err => mostrarResultado(err, true));
}

// Crear (POST)
function crearUsuario() {
  const data = {
    nombre: document.getElementById("nombre").value,
    matricula: document.getElementById("matricula").value,
    correo: document.getElementById("correo").value,
    telefono: document.getElementById("telefono").value,
    tipoUsuario: document.getElementById("tipoUsuario").value,
    idExpediente: document.getElementById("idExpediente").value
  };

  fetch(baseUrl, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(data)
  })
    .then(res => res.json())
    .then(u => mostrarResultado(`Usuario creado con ID: ${u.idUsuario}`))
    .catch(err => mostrarResultado("Error al crear usuario", true));
}

// Actualizar (PUT)
function actualizarUsuario() {
  const id = document.getElementById("updateId").value;
  const data = {
    nombre: document.getElementById("updateNombre").value,
    matricula: document.getElementById("updateMatricula").value,
    correo: document.getElementById("updateCorreo").value,
    telefono: document.getElementById("updateTelefono").value,
    tipoUsuario: document.getElementById("updateTipoUsuario").value,
    idExpediente: document.getElementById("updateExpediente").value
  };

  fetch(`${baseUrl}/${id}`, {
    method: "PUT",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(data)
  })
    .then(res => res.json())
    .then(u => mostrarResultado(`Usuario actualizado: ${u.nombre}`))
    .catch(err => mostrarResultado("Error al actualizar usuario", true));
}

// Eliminar (DELETE)
function eliminarUsuario() {
  const id = document.getElementById("deleteId").value;

  fetch(`${baseUrl}/${id}`, {method: "DELETE"})
    .then(res => {
      if (res.status === 204) {
        mostrarResultado(`Usuario con ID ${id} eliminado`);
      } else {
        throw new Error("No se pudo eliminar (verifica dependencias o ruta)");
      }
    })
    .catch(err => mostrarResultado(err, true));
}