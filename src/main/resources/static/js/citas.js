const API = 'http://localhost:8080/api';
let seccion = '', editId = null;

async function req(url, method = 'GET', body = null) {
    const r = await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: body ? JSON.stringify(body) : null
    });
    if (!r.ok) throw new Error(r.status);
    return method === 'DELETE' ? null : r.json();
}

function showSection(s) {
    document.querySelectorAll('[id^="section-"]').forEach(el => el.classList.add('d-none'));
    document.getElementById(`section-${s}`).classList.remove('d-none');
    seccion = s;
    document.getElementById('searchId').value = '';
    cargarTabla();
}

async function cargarTabla() {
    try {
        const data = await req(`${API}/${seccion}`);
        const tbody = document.getElementById(`tabla-${seccion}`);
        tbody.innerHTML = data.map(item => fila(item)).join('');
    } catch(e) { console.error(e); }
}

async function buscarPorId() {
    const id = document.getElementById('searchId').value;
    if (!id) return cargarTabla();
    try {
        const d = await req(`${API}/${seccion}/${id}`);
        const tbody = document.getElementById(`tabla-${seccion}`);
        tbody.innerHTML = d ? fila(d) : '<tr><td colspan="10" class="text-center">No encontrado</td></tr>';
    } catch (e) {
        alert("No se encontró el registro con ese ID");
        cargarTabla();
    }
}

function limpiarBusqueda() {
    document.getElementById('searchId').value = '';
    cargarTabla();
}

function getId(d) {
    return d.idUsuario ?? d.idExpediente ?? d.idEspecialista ?? d.idhorario ?? d.idCita ?? d.idDetalleMedica ?? d.idDetalleNutricion ?? d.idDetallePsicologica;
}

function fila(d) {
    const id = getId(d);
    const btns = `<button class="btn btn-sm btn-warning me-1" onclick="openModal('${seccion}',${id})">Editar</button>
                  <button class="btn btn-sm btn-danger" onclick="eliminar(${id})">Eliminar</button>`;
    switch (seccion) {
        case 'usuarios': return `<tr><td>${d.idUsuario}</td><td>${d.matricula??''}</td><td>${d.nombre??''}</td><td>${d.correo??''}</td><td>${d.telefono??''}</td><td>${d.tipoUsuario??''}</td><td>${d.idExpediente??''}</td><td>${btns}</td></tr>`;
        case 'expedientes': return `<tr><td>${d.idExpediente}</td><td>${d.fechaDeCreacion??''}</td><td>${d.historialNotas??''}</td><td>${btns}</td></tr>`;
        case 'especialistas': return `<tr><td>${d.idEspecialista}</td><td>${d.nombre??''}</td><td>${d.correo??''}</td><td>${d.telefono??''}</td><td>${d.area??''}</td><td>${btns}</td></tr>`;
        case 'horarios': return `<tr><td>${d.idhorario}</td><td>${d.duracion??''}</td><td>${d.disponible??''}</td><td>${d.especialista??''}</td><td>${btns}</td></tr>`;
        case 'citas': return `<tr><td>${d.idCita}</td><td>${d.fecha??''}</td><td>${d.hora??''}</td><td>${d.estado??''}</td><td>${d.usuarioIdUsuario??''}</td><td>${d.especialistaIdEspecialista??''}</td><td>${d.horarioIdhorario??''}</td><td>${btns}</td></tr>`;
        case 'detalle-medica': return `<tr><td>${d.idDetalleMedica}</td><td>${d.motivoConsulta??''}</td><td>${d.alergias??''}</td><td>${d.citaIdCita??''}</td><td>${btns}</td></tr>`;
        case 'detalle-nutricion': return `<tr><td>${d.idDetalleNutricion}</td><td>${d.pesoActual??''}</td><td>${d.estatura??''}</td><td>${d.objetivo??''}</td><td>${d.citaIdCita??''}</td><td>${btns}</td></tr>`;
        case 'detalle-psicologica': return `<tr><td>${d.idDetallePsicologica}</td><td>${d.descripcionEmocional??''}</td><td>${d.motivoConsulta??''}</td><td>${d.citaIdCita??''}</td><td>${btns}</td></tr>`;
    }
}

const forms = {
    usuarios: d => `<input class="form-control mb-2" id="f0" placeholder="Matrícula" value="${d.matricula??''}"> <input class="form-control mb-2" id="f1" placeholder="Nombre" value="${d.nombre??''}"> <input class="form-control mb-2" id="f2" placeholder="Correo" value="${d.correo??''}"> <input class="form-control mb-2" id="f3" placeholder="Teléfono" value="${d.telefono??''}"> <input class="form-control mb-2" id="f4" placeholder="Tipo" value="${d.tipoUsuario??''}"> <input class="form-control mb-2" id="f5" placeholder="ID Expediente" type="number" value="${d.idExpediente??''}">`,
    expedientes: d => `<input class="form-control mb-2" id="f0" type="date" value="${d.fechaDeCreacion??''}"> <textarea class="form-control mb-2" id="f1" placeholder="Notas">${d.historialNotas??''}</textarea>`,
    especialistas: d => `<input class="form-control mb-2" id="f0" placeholder="Nombre" value="${d.nombre??''}"> <input class="form-control mb-2" id="f1" placeholder="Correo" value="${d.correo??''}"> <input class="form-control mb-2" id="f2" placeholder="Teléfono" value="${d.telefono??''}"> <input class="form-control mb-2" id="f3" placeholder="Área" value="${d.area??''}">`,
    horarios: d => `<input class="form-control mb-2" id="f0" placeholder="Duración" value="${d.duracion??''}"> <input class="form-control mb-2" id="f1" placeholder="Disponible" type="number" value="${d.disponible??''}"> <input class="form-control mb-2" id="f2" placeholder="Especialista" value="${d.especialista??''}">`,
    citas: (d, u, e, h) => `<input class="form-control mb-2" id="f0" type="date" value="${d.fecha??''}"> <input class="form-control mb-2" id="f1" placeholder="Hora" value="${d.hora??''}"> <select class="form-select mb-2" id="f2"><option value="Pendiente" ${d.estado==='Pendiente'?'selected':''}>Pendiente</option><option value="Confirmada" ${d.estado==='Confirmada'?'selected':''}>Confirmada</option></select> <select class="form-select mb-2" id="f3">${u.map(x=>`<option value="${x.idUsuario}" ${d.usuarioIdUsuario===x.idUsuario?'selected':''}>${x.nombre}</option>`).join('')}</select> <select class="form-select mb-2" id="f4">${e.map(x=>`<option value="${x.idEspecialista}" ${d.especialistaIdEspecialista===x.idEspecialista?'selected':''}>${x.nombre}</option>`).join('')}</select> <select class="form-select mb-2" id="f5">${h.map(x=>`<option value="${x.idhorario}" ${d.horarioIdhorario===x.idhorario?'selected':''}>${x.idhorario}</option>`).join('')}</select>`,
    'detalle-medica': d => `<textarea class="form-control mb-2" id="f0" placeholder="Motivo">${d.motivoConsulta??''}</textarea> <input class="form-control mb-2" id="f1" placeholder="Alergias" value="${d.alergias??''}"> <input class="form-control mb-2" id="f2" placeholder="ID Cita" type="number" value="${d.citaIdCita??''}">`,
    'detalle-nutricion': d => `<input class="form-control mb-2" id="f0" type="number" step="0.1" placeholder="Peso" value="${d.pesoActual??''}"> <input class="form-control mb-2" id="f1" type="number" step="0.1" placeholder="Estatura" value="${d.estatura??''}"> <textarea class="form-control mb-2" id="f2" placeholder="Objetivo">${d.objetivo??''}</textarea> <input class="form-control mb-2" id="f3" type="number" placeholder="ID Cita" value="${d.citaIdCita??''}">`,
    'detalle-psicologica': d => `<textarea class="form-control mb-2" id="f0" placeholder="Emocional">${d.descripcionEmocional??''}</textarea> <textarea class="form-control mb-2" id="f1" placeholder="Motivo">${d.motivoConsulta??''}</textarea> <input class="form-control mb-2" id="f2" type="number" placeholder="ID Cita" value="${d.citaIdCita??''}">`
};

async function openModal(s, id = null) {
    seccion = s; editId = id;
    const d = id ? await req(`${API}/${s}/${id}`) : {};
    document.getElementById("modalTitle").textContent = id ? "Editar" : "Nuevo";
    if (s === "citas") {
        const [u, e, h] = await Promise.all([req(`${API}/usuarios`), req(`${API}/especialistas`), req(`${API}/horarios`)]);
        document.getElementById("modalBody").innerHTML = forms[s](d, u, e, h);
    } else document.getElementById("modalBody").innerHTML = forms[s](d);
    new bootstrap.Modal(document.getElementById("modalForm")).show();
}

const v = id => document.getElementById(id)?.value;
const n = id => v(id) !== '' ? Number(v(id)) : null;

const getBody = {
    usuarios: () => ({ matricula: v('f0'), nombre: v('f1'), correo: v('f2'), telefono: v('f3'), tipoUsuario: v('f4'), idExpediente: n('f5') }),
    expedientes: () => ({ fechaDeCreacion: v('f0'), historialNotas: v('f1') }),
    especialistas: () => ({ nombre: v('f0'), correo: v('f1'), telefono: v('f2'), area: v('f3') }),
    horarios: () => ({ duracion: v('f0'), disponible: n('f1'), especialista: v('f2') }),
    citas: () => ({ fecha: v('f0'), hora: v('f1'), estado: v('f2'), usuarioIdUsuario: n('f3'), especialistaIdEspecialista: n('f4'), horarioIdhorario: n('f5') }),
    'detalle-medica': () => ({ motivoConsulta: v('f0'), alergias: v('f1'), citaIdCita: n('f2') }),
    'detalle-nutricion': () => ({ pesoActual: n('f0'), estatura: n('f1'), objetivo: v('f2'), citaIdCita: n('f3') }),
    'detalle-psicologica': () => ({ descripcionEmocional: v('f0'), motivoConsulta: v('f1'), citaIdCita: n('f2') })
};

async function guardar() {
    const body = getBody[seccion]();
    const url = editId ? `${API}/${seccion}/${editId}` : `${API}/${seccion}`;
    await req(url, editId ? 'PUT' : 'POST', body);
    bootstrap.Modal.getInstance(document.getElementById('modalForm')).hide();
    cargarTabla();
}

async function eliminar(id) {
    if (confirm('¿Eliminar?')) { await req(`${API}/${seccion}/${id}`, 'DELETE'); cargarTabla(); }
}

window.onload = () => showSection('usuarios');