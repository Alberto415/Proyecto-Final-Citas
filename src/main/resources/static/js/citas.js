const API = 'http://localhost:8080/api';
let seccion = '', editId = null;
let _especialistasCache = [];
let _usuariosCache     = [];
let _horariosCache     = [];
let esAdmin = false; // rol del usuario logueado

const idField = (id, label = 'ID') => id != null ? `
    <label class="small fw-bold text-secondary">${label}</label>
    <div class="input-group mb-3">
        <span class="input-group-text bg-secondary text-white">#</span>
        <input class="form-control bg-light text-muted fw-bold" value="${id}" readonly disabled>
    </div>` : '';

async function filtrarEspecialistasPorArea() {
    const area = v('f_area_filtro');
    const sel  = document.getElementById('f2');
    if (!area) { sel.innerHTML = '<option value="">Primero seleccione un área...</option>'; return; }
    try {
        const lista = await req(`${API}/especialistas`);
        const fil   = lista.filter(e => e.area === area);
        sel.innerHTML = fil.length
            ? fil.map(e => `<option value="${e.nombre}">${e.nombre}</option>`).join('')
            : '<option value="">Sin especialistas en esta área</option>';
    } catch (e) { console.error(e); }
}

function filtrarEspecialistasCita() {
    const area = v('f_area_cita');
    const cont = document.getElementById('checkboxes-especialistas');
    if (!area) { cont.innerHTML = '<p class="text-muted small mb-0">Seleccione un área primero.</p>'; return; }
    const fil = _especialistasCache.filter(e => e.area === area);
    if (!fil.length) { cont.innerHTML = '<p class="text-muted small mb-0">No hay especialistas en esta área.</p>'; return; }
    cont.innerHTML = fil.map(e => `
        <div class="form-check">
            <input class="form-check-input" type="checkbox" name="especialista_check"
                   id="esp_${e.idEspecialista}" value="${e.idEspecialista}"
                   onchange="soloUnoEspecialista(this)">
            <label class="form-check-label" for="esp_${e.idEspecialista}">${e.nombre}</label>
        </div>`).join('');
}

function soloUnoEspecialista(el) {
    document.querySelectorAll('input[name="especialista_check"]')
        .forEach(cb => { if (cb !== el) cb.checked = false; });
    if (!el.checked) { document.getElementById('seccion-detalle').innerHTML = ''; return; }
    const esp = _especialistasCache.find(e => e.idEspecialista === Number(el.value));
    if (!esp) return;
    const areaSelect = document.getElementById('f_area_cita');
    if (areaSelect) areaSelect.value = esp.area;
    const horSelect = document.getElementById('f5');
    if (horSelect) {
        const filtrados = _horariosCache.filter(h => h.especialista === esp.nombre);
        horSelect.innerHTML = filtrados.length
            ? `<option value="">— Seleccionar horario —</option>` + filtrados.map(h => `<option value="${h.idhorario}">Horario #${h.idhorario} · ${h.duracion} · ${h.disponible ? 'Disponible' : 'Ocupado'}</option>`).join('')
            : `<option value="">Sin horarios para este especialista</option>`;
    }
    const seccionDetalle = document.getElementById('seccion-detalle');
    if (seccionDetalle) seccionDetalle.innerHTML = renderDetalleFields(esp.area);
}

function getEspecialistaSeleccionado() {
    const c = document.querySelector('input[name="especialista_check"]:checked');
    return c ? Number(c.value) : null;
}

function renderDetalleFields(area, d = {}) {
    if (!area) return '';
    const titulo = { 'Medicina': 'Detalle Médico', 'Psicología': 'Detalle Psicológico', 'Nutrición': 'Detalle Nutricional' }[area] ?? 'Detalle';
    let campos = '';
    if (area === 'Medicina') {
        campos = `
            <label class="small fw-bold">Motivo de Consulta</label>
            <input class="form-control mb-2" id="det_f0" value="${d.motivoConsulta ?? ''}">
            <label class="small fw-bold">Alergias</label>
            <input class="form-control mb-2" id="det_f1" value="${d.alergias ?? ''}">`;
    } else if (area === 'Psicología') {
        campos = `
            <label class="small fw-bold">Descripción Emocional</label>
            <textarea class="form-control mb-2" id="det_f0" rows="2">${d.descripcionEmocional ?? ''}</textarea>
            <label class="small fw-bold">Motivo de Consulta</label>
            <input class="form-control mb-2" id="det_f1" value="${d.motivoConsulta ?? ''}">`;
    } else if (area === 'Nutrición') {
        campos = `
            <label class="small fw-bold">Peso Actual (kg)</label>
            <input class="form-control mb-2" id="det_f0" type="number" step="0.1" value="${d.pesoActual ?? ''}">
            <label class="small fw-bold">Estatura (m)</label>
            <input class="form-control mb-2" id="det_f1" type="number" step="0.01" value="${d.estatura ?? ''}">
            <label class="small fw-bold">Objetivo</label>
            <input class="form-control mb-2" id="det_f2" value="${d.objetivo ?? ''}">`;
    }
    return `<hr class="my-2"><p class="fw-bold text-primary mb-2">${titulo}</p>${campos}`;
}

const forms = {
    usuarios: d => `
        ${idField(d.idUsuario, 'ID Usuario')}
        <label class="small fw-bold">Matrícula</label>
        <input class="form-control mb-2" id="f0" value="${d.matricula ?? ''}">
        <label class="small fw-bold">Nombre</label>
        <input class="form-control mb-2" id="f1" value="${d.nombre ?? ''}">
        <label class="small fw-bold">Correo</label>
        <input class="form-control mb-2" id="f2" value="${d.correo ?? ''}">
        <label class="small fw-bold">Teléfono</label>
        <input class="form-control mb-2" id="f3" value="${d.telefono ?? ''}">
        <label class="small fw-bold">Tipo de Usuario</label>
        <select class="form-select mb-2" id="f4">
            <option value="">— Seleccionar —</option>
            <option value="Alumno"     ${d.tipoUsuario === 'Alumno'     ? 'selected' : ''}>Alumno</option>
            <option value="Profesor"   ${d.tipoUsuario === 'Profesor'   ? 'selected' : ''}>Profesor</option>
            <option value="Trabajador" ${d.tipoUsuario === 'Trabajador' ? 'selected' : ''}>Trabajador</option>
        </select>`,

    expedientes: (d, usuarios = []) => `
        ${idField(d.idExpediente, 'ID Expediente')}
        <label class="small fw-bold">Fecha de Creación</label>
        <input class="form-control mb-2" id="f0" type="date" value="${d.fechaDeCreacion ?? ''}">
        <label class="small fw-bold">Historial / Notas</label>
        <textarea class="form-control mb-2" id="f1" rows="3">${d.historialNotas ?? ''}</textarea>
        ${!d.idExpediente ? `
        <label class="small fw-bold text-primary">Asignar a Usuario</label>
        <select class="form-select mb-2 border-primary" id="f2">
            <option value="">— Seleccionar usuario con cita —</option>
            ${usuarios.map(u => `<option value="${u.idUsuario}">${u.nombre} · ${u.tipoUsuario} · ${u.matricula ?? 'Sin matrícula'}</option>`).join('')}
        </select>
        ${usuarios.length === 0 ? '<p class="text-warning small">Todos los usuarios con cita ya tienen expediente.</p>' : ''}
        ` : `<p class="text-muted small">Este expediente ya está asignado a un usuario.</p>`}`,

    especialistas: d => `
        ${idField(d.idEspecialista, 'ID Especialista')}
        <label class="small fw-bold">Nombre Completo</label>
        <input class="form-control mb-2" id="f0" value="${d.nombre ?? ''}">
        <label class="small fw-bold">Correo</label>
        <input class="form-control mb-2" id="f1" value="${d.correo ?? ''}">
        <label class="small fw-bold">Teléfono</label>
        <input class="form-control mb-2" id="f2" value="${d.telefono ?? ''}">
        <label class="small fw-bold">Área</label>
        <select class="form-select mb-2" id="f3">
            <option value="">— Seleccionar área —</option>
            <option value="Medicina"   ${d.area === 'Medicina'   ? 'selected' : ''}>Medicina</option>
            <option value="Psicología" ${d.area === 'Psicología' ? 'selected' : ''}>Psicología</option>
            <option value="Nutrición"  ${d.area === 'Nutrición'  ? 'selected' : ''}>Nutrición</option>
        </select>`,

    horarios: d => `
        ${idField(d.idhorario, 'ID Horario')}
        <label class="small fw-bold text-primary">1. Área del Especialista</label>
        <select class="form-select mb-2 border-primary" id="f_area_filtro" onchange="filtrarEspecialistasPorArea()">
            <option value="">— Seleccionar área —</option>
            <option value="Medicina">Medicina</option>
            <option value="Psicología">Psicología</option>
            <option value="Nutrición">Nutrición</option>
        </select>
        <label class="small fw-bold text-primary">2. Especialista</label>
        <select class="form-select mb-2 border-primary" id="f2">
            <option value="${d.especialista ?? ''}">${d.especialista ?? 'Seleccione área primero...'}</option>
        </select>
        <label class="small fw-bold">Duración</label>
        <input class="form-control mb-2" id="f0" value="${d.duracion ?? '30 min'}">
        <label class="small fw-bold">Disponibilidad</label>
        <select class="form-select mb-2" id="f1">
            <option value="1" ${d.disponible === 1 ? 'selected' : ''}>Disponible</option>
            <option value="0" ${d.disponible === 0 ? 'selected' : ''}>Ocupado</option>
        </select>`,

    citas: (d, u, e, h) => {
        _especialistasCache = e; _usuariosCache = u; _horariosCache = h;
        const espActual  = e.find(x => x.idEspecialista === d.especialistaIdEspecialista);
        const areaActual = espActual ? espActual.area : '';
        const espsFiltrados = areaActual ? e.filter(x => x.area === areaActual) : [];
        const horFiltrados  = areaActual ? h.filter(x => x.especialista === espActual.nombre) : h;
        const checkboxesHtml = espsFiltrados.length
            ? espsFiltrados.map(x => `
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="especialista_check"
                           id="esp_${x.idEspecialista}" value="${x.idEspecialista}"
                           onchange="soloUnoEspecialista(this)"
                           ${d.especialistaIdEspecialista === x.idEspecialista ? 'checked' : ''}>
                    <label class="form-check-label" for="esp_${x.idEspecialista}">${x.nombre}</label>
                </div>`).join('')
            : '<p class="text-muted small mb-0">Seleccione un área primero.</p>';
        return `
        ${idField(d.idCita, 'ID Cita')}
        <label class="small fw-bold">Fecha</label>
        <input class="form-control mb-2" id="f0" type="date" value="${d.fecha ?? ''}">
        <label class="small fw-bold">Hora</label>
        <input class="form-control mb-2" id="f1" type="time" value="${d.hora ? d.hora.substring(0,5) : ''}">
        <label class="small fw-bold">Estado</label>
        <select class="form-select mb-2" id="f2">
            <option value="">— Seleccionar —</option>
            <option value="Pendiente"  ${d.estado === 'Pendiente'  ? 'selected' : ''}>Pendiente</option>
            <option value="Confirmada" ${d.estado === 'Confirmada' ? 'selected' : ''}>Confirmada</option>
        </select>
        <label class="small fw-bold">Usuario / Alumno</label>
        <select class="form-select mb-2" id="f3">
            <option value="">— Seleccionar usuario —</option>
            ${u.map(x => `<option value="${x.idUsuario}" ${d.usuarioIdUsuario === x.idUsuario ? 'selected' : ''}>${x.nombre} (${x.tipoUsuario})</option>`).join('')}
        </select>
        <label class="small fw-bold text-primary">Área del Especialista</label>
        <select class="form-select mb-2 border-primary" id="f_area_cita" onchange="filtrarEspecialistasCita()">
            <option value="">— Seleccionar área —</option>
            <option value="Medicina"   ${areaActual === 'Medicina'   ? 'selected' : ''}>Medicina</option>
            <option value="Psicología" ${areaActual === 'Psicología' ? 'selected' : ''}>Psicología</option>
            <option value="Nutrición"  ${areaActual === 'Nutrición'  ? 'selected' : ''}>Nutrición</option>
        </select>
        <label class="small fw-bold text-primary d-block mb-1">Especialista</label>
        <div id="checkboxes-especialistas" class="border rounded p-2 mb-2 bg-white" style="min-height:42px;">${checkboxesHtml}</div>
        <label class="small fw-bold">Horario</label>
        <select class="form-select mb-2" id="f5">
            <option value="">— Seleccionar horario —</option>
            ${horFiltrados.map(x => `<option value="${x.idhorario}" ${d.horarioIdhorario === x.idhorario ? 'selected' : ''}>Horario #${x.idhorario} · ${x.duracion} · ${x.disponible ? 'Disponible' : 'Ocupado'}</option>`).join('')}
        </select>
        <div id="seccion-detalle">${areaActual ? renderDetalleFields(areaActual, {}) : ''}</div>`;
    },

    'detalle-medica': (d, c) => `
        ${idField(d.idDetalleMedica, 'ID Detalle Médico')}
        <label class="small fw-bold">Motivo de Consulta</label>
        <input class="form-control mb-2" id="f0" value="${d.motivoConsulta ?? ''}">
        <label class="small fw-bold">Alergias</label>
        <input class="form-control mb-2" id="f1" value="${d.alergias ?? ''}">
        <label class="small fw-bold">Cita</label>
        <select class="form-select mb-2" id="f2">
            <option value="">— Seleccionar cita —</option>
            ${c.map(x => `<option value="${x.idCita}" ${d.citaIdCita === x.idCita ? 'selected' : ''}>Cita #${x.idCita} · ${x.fecha} ${x.hora} · ${x.estado}</option>`).join('')}
        </select>`,

    'detalle-nutricion': (d, c) => `
        ${idField(d.idDetalleNutricion, 'ID Detalle Nutrición')}
        <label class="small fw-bold">Peso Actual (kg)</label>
        <input class="form-control mb-2" id="f0" type="number" step="0.1" value="${d.pesoActual ?? ''}">
        <label class="small fw-bold">Estatura (m)</label>
        <input class="form-control mb-2" id="f1" type="number" step="0.01" value="${d.estatura ?? ''}">
        <label class="small fw-bold">Objetivo</label>
        <input class="form-control mb-2" id="f2" value="${d.objetivo ?? ''}">
        <label class="small fw-bold">Cita</label>
        <select class="form-select mb-2" id="f3">
            <option value="">— Seleccionar cita —</option>
            ${c.map(x => `<option value="${x.idCita}" ${d.citaIdCita === x.idCita ? 'selected' : ''}>Cita #${x.idCita} · ${x.fecha} ${x.hora} · ${x.estado}</option>`).join('')}
        </select>`,

    'detalle-psicologica': (d, c) => `
        ${idField(d.idDetallePsicologica, 'ID Detalle Psicología')}
        <label class="small fw-bold">Descripción Emocional</label>
        <textarea class="form-control mb-2" id="f0" rows="2">${d.descripcionEmocional ?? ''}</textarea>
        <label class="small fw-bold">Motivo de Consulta</label>
        <input class="form-control mb-2" id="f1" value="${d.motivoConsulta ?? ''}">
        <label class="small fw-bold">Cita</label>
        <select class="form-select mb-2" id="f2">
            <option value="">— Seleccionar cita —</option>
            ${c.map(x => `<option value="${x.idCita}" ${d.citaIdCita === x.idCita ? 'selected' : ''}>Cita #${x.idCita} · ${x.fecha} ${x.hora} · ${x.estado}</option>`).join('')}
        </select>`
};

async function req(url, method = 'GET', body = null) {
    const r = await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: body ? JSON.stringify(body) : null
    });
    if (r.status === 401) { window.location.href = '/login.html'; return; }
    if (!r.ok) throw new Error(r.status);
    return method === 'DELETE' ? null : r.json();
}

// Verifica permisos ANTES de abrir modal o borrar
function verificarAdmin(accion) {
    if (!esAdmin) {
        alert(`No tienes permisos para ${accion}. Solo el administrador puede modificar datos.`);
        return false;
    }
    return true;
}

async function openModal(s, id = null) {
    // Bloquear edición y creación si no es admin
    if (!verificarAdmin(id ? 'editar registros' : 'crear registros')) return;

    seccion = s;
    editId  = id;
    const d = id ? await req(`${API}/${s}/${id}`) : {};
    document.getElementById("modalTitle").textContent = (id ? "Editar " : "Nuevo ") + s;

    let html = '';
    if (s === 'citas') {
        const [u, e, h] = await Promise.all([req(`${API}/usuarios`), req(`${API}/especialistas`), req(`${API}/horarios`)]);
        html = forms[s](d, u, e, h);
    } else if (s === 'expedientes') {
        const [usuarios, citas] = await Promise.all([req(`${API}/usuarios`), req(`${API}/citas`)]);
        const idsConCita = new Set(citas.map(c => c.usuarioIdUsuario));
        const usuariosElegibles = usuarios.filter(u =>
            idsConCita.has(u.idUsuario) && (u.idExpediente == null || (id && u.idExpediente === d.idExpediente))
        );
        html = forms[s](d, usuariosElegibles);
    } else if (['detalle-medica', 'detalle-nutricion', 'detalle-psicologica'].includes(s)) {
        const c = await req(`${API}/citas`);
        html = forms[s](d, c);
    } else {
        html = forms[s](d);
    }

    document.getElementById("modalBody").innerHTML = html;
    new bootstrap.Modal(document.getElementById("modalForm")).show();
}

const v = id => document.getElementById(id)?.value;
const n = id => v(id) !== '' ? Number(v(id)) : null;

const getBody = {
    usuarios:              () => ({ matricula: v('f0'), nombre: v('f1'), correo: v('f2'), telefono: v('f3'), tipoUsuario: v('f4') }),
    expedientes:           () => ({ fechaDeCreacion: v('f0'), historialNotas: v('f1') }),
    especialistas:         () => ({ nombre: v('f0'), correo: v('f1'), telefono: v('f2'), area: v('f3') }),
    horarios:              () => ({ duracion: v('f0'), disponible: n('f1'), especialista: v('f2') }),
    citas:                 () => ({ fecha: v('f0'), hora: v('f1'), estado: v('f2'), usuarioIdUsuario: n('f3'), especialistaIdEspecialista: getEspecialistaSeleccionado(), horarioIdhorario: n('f5') }),
    'detalle-medica':      () => ({ motivoConsulta: v('f0'), alergias: v('f1'), citaIdCita: n('f2') }),
    'detalle-nutricion':   () => ({ pesoActual: parseFloat(v('f0')), estatura: parseFloat(v('f1')), objetivo: v('f2'), citaIdCita: n('f3') }),
    'detalle-psicologica': () => ({ descripcionEmocional: v('f0'), motivoConsulta: v('f1'), citaIdCita: n('f2') })
};

async function guardar() {
    try {
        const body = getBody[seccion]();
        if (seccion === 'citas') {
            if (!body.especialistaIdEspecialista) { alert("Por favor selecciona un especialista."); return; }
            if (!body.horarioIdhorario)            { alert("Por favor selecciona un horario.");      return; }
            if (body.hora && body.hora.length === 5) body.hora = body.hora + ':00';
            const url  = editId ? `${API}/citas/${editId}` : `${API}/citas`;
            const cita = await req(url, editId ? 'PUT' : 'POST', body);
            if (!editId) {
                const esp    = _especialistasCache.find(e => e.idEspecialista === body.especialistaIdEspecialista);
                const area   = esp?.area;
                const idCita = cita.idCita;
                if (area === 'Medicina') {
                    await req(`${API}/detalle-medica`, 'POST', { motivoConsulta: v('det_f0'), alergias: v('det_f1'), citaIdCita: idCita });
                } else if (area === 'Psicología') {
                    await req(`${API}/detalle-psicologica`, 'POST', { descripcionEmocional: v('det_f0'), motivoConsulta: v('det_f1'), citaIdCita: idCita });
                } else if (area === 'Nutrición') {
                    await req(`${API}/detalle-nutricion`, 'POST', { pesoActual: parseFloat(v('det_f0')), estatura: parseFloat(v('det_f1')), objetivo: v('det_f2'), citaIdCita: idCita });
                }
            }
        } else if (seccion === 'expedientes' && !editId) {
            const idUsuario = n('f2');
            if (!idUsuario) { alert("Por favor selecciona un usuario."); return; }
            const expCreado = await req(`${API}/expedientes`, 'POST', { fechaDeCreacion: v('f0'), historialNotas: v('f1') });
            const usuario   = await req(`${API}/usuarios/${idUsuario}`);
            await req(`${API}/usuarios/${idUsuario}`, 'PUT', {
                idUsuario: usuario.idUsuario, matricula: usuario.matricula, nombre: usuario.nombre,
                correo: usuario.correo, telefono: usuario.telefono, tipoUsuario: usuario.tipoUsuario,
                idExpediente: expCreado.idExpediente
            });
        } else {
            const url = editId ? `${API}/${seccion}/${editId}` : `${API}/${seccion}`;
            await req(url, editId ? 'PUT' : 'POST', body);
        }
        bootstrap.Modal.getInstance(document.getElementById('modalForm')).hide();
        cargarTabla();
    } catch (e) {
        console.error(e);
        alert("Error al guardar. Revisa que todos los campos estén completos.");
    }
}

function showSection(s) {
    document.querySelectorAll('[id^="section-"]').forEach(el => el.classList.add('d-none'));
    const el = document.getElementById(`section-${s}`);
    if (el) el.classList.remove('d-none');
    seccion = s;
    cargarTabla();
}

async function cargarTabla() {
    try {
        if (seccion === 'citas') {
            const [data, u, e] = await Promise.all([req(`${API}/${seccion}`), req(`${API}/usuarios`), req(`${API}/especialistas`)]);
            _usuariosCache = u; _especialistasCache = e;
            document.getElementById(`tabla-${seccion}`).innerHTML = data.map(item => fila(item)).join('');
        } else {
            const data = await req(`${API}/${seccion}`);
            document.getElementById(`tabla-${seccion}`).innerHTML = data.map(item => fila(item)).join('');
        }
    } catch (e) { console.error(e); }
}

function getId(d) {
    return d.idUsuario ?? d.idExpediente ?? d.idEspecialista ?? d.idhorario ?? d.idCita
        ?? d.idDetalleMedica ?? d.idDetalleNutricion ?? d.idDetallePsicologica;
}

function fila(d) {
    const id   = getId(d);
    const btns = `
        <button class="btn btn-sm btn-warning me-1" onclick="openModal('${seccion}',${id})">Editar</button>
        <button class="btn btn-sm btn-danger"        onclick="eliminar(${id})">Borrar</button>`;
    const nombreUsuario      = uid => _usuariosCache.find(u => u.idUsuario === uid)?.nombre          ?? `ID: ${uid}`;
    const nombreEspecialista = eid => _especialistasCache.find(e => e.idEspecialista === eid)?.nombre ?? `ID: ${eid}`;
    switch (seccion) {
        case 'usuarios':          return `<tr><td>${d.idUsuario}</td><td>${d.matricula}</td><td>${d.nombre}</td><td>${d.correo}</td><td>${d.telefono}</td><td>${d.tipoUsuario}</td><td>${d.idExpediente ?? '—'}</td><td>${btns}</td></tr>`;
        case 'expedientes':       return `<tr><td>${d.idExpediente}</td><td>${d.fechaDeCreacion}</td><td>${d.historialNotas}</td><td>${btns}</td></tr>`;
        case 'especialistas':     return `<tr><td>${d.idEspecialista}</td><td>${d.nombre}</td><td>${d.correo}</td><td>${d.telefono}</td><td>${d.area}</td><td>${btns}</td></tr>`;
        case 'horarios':          return `<tr><td>${d.idhorario}</td><td>${d.duracion}</td><td>${d.disponible ? 'SI' : 'NO'}</td><td>${d.especialista}</td><td>${btns}</td></tr>`;
        case 'citas':             return `<tr><td>${d.idCita}</td><td>${d.fecha}</td><td>${d.hora}</td><td>${d.estado}</td><td>${nombreUsuario(d.usuarioIdUsuario)}</td><td>${nombreEspecialista(d.especialistaIdEspecialista)}</td><td>${d.horarioIdhorario}</td><td>${btns}</td></tr>`;
        case 'detalle-medica':    return `<tr><td>${d.idDetalleMedica}</td><td>${d.motivoConsulta}</td><td>${d.alergias}</td><td>${d.citaIdCita}</td><td>${btns}</td></tr>`;
        case 'detalle-nutricion': return `<tr><td>${d.idDetalleNutricion}</td><td>${d.pesoActual}</td><td>${d.estatura}</td><td>${d.objetivo}</td><td>${d.citaIdCita}</td><td>${btns}</td></tr>`;
        case 'detalle-psicologica': return `<tr><td>${d.idDetallePsicologica}</td><td>${d.descripcionEmocional}</td><td>${d.motivoConsulta}</td><td>${d.citaIdCita}</td><td>${btns}</td></tr>`;
        default: return '';
    }
}

async function eliminar(id) {
    if (!verificarAdmin('eliminar registros')) return;
    if (confirm('¿Seguro que deseas eliminar este registro?')) {
        try {
            await req(`${API}/${seccion}/${id}`, 'DELETE');
            cargarTabla();
        } catch (e) {
            alert("Error al eliminar.");
        }
    }
}

async function buscarPorId() {
    const id = document.getElementById('searchId').value;
    if (!id || !seccion) return;
    try {
        const d = await req(`${API}/${seccion}/${id}`);
        document.getElementById(`tabla-${seccion}`).innerHTML = fila(d);
    } catch (e) {
        alert(`No se encontró el ID ${id} en ${seccion}`);
    }
}

// Al cargar la página: obtener el rol del usuario logueado
window.onload = async () => {
    try {
        const sesion = await req(`${API}/sesion/yo`);
        esAdmin = sesion.esAdmin === true;
        const span = document.getElementById('usuario-activo');
        if (span) span.textContent = `${sesion.usuario} (${esAdmin ? 'Admin' : 'Alumno'})`;
    } catch (e) {
        window.location.href = '/login.html';
    }
    showSection('usuarios');
};