const baseUrl = '/api/students'; // relative URL works when served from same origin

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('studentForm');
    const listEl = document.getElementById('studentsList');
    const msg = document.getElementById('message');

    // Load and render list
    async function loadStudents() {
        try {
            const res = await fetch(baseUrl);
            if (!res.ok) throw new Error('Failed to load students');
            const students = await res.json();
            renderList(students);
        } catch (e) {
            console.error(e);
            msg.textContent = 'Error loading students';
        }
    }

    function renderList(students) {
        listEl.innerHTML = '';
        if (students.length === 0) {
            listEl.innerHTML = '<li>No students yet.</li>';
            return;
        }
        students.forEach(s => {
            const li = document.createElement('li');
            li.textContent = `${s.rollNumber} — ${s.firstName} ${s.lastName} (${s.email || 'no email'})`;
            const del = document.createElement('button');
            del.textContent = 'Delete';
            del.addEventListener('click', () => deleteStudent(s.id));
            li.appendChild(del);
            listEl.appendChild(li);
        });
    }

    async function addStudent(data) {
        try {
            const res = await fetch(baseUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(data)
            });
            if (res.status === 201) {
                msg.textContent = 'Student added';
                form.reset();
                loadStudents();
            } else {
                const err = await res.text();
                msg.textContent = 'Add failed: ' + err;
            }
        } catch (e) {
            console.error(e);
            msg.textContent = 'Network error while adding';
        }
    }

    async function deleteStudent(id) {
        try {
            const res = await fetch(`${baseUrl}/${id}`, { method: 'DELETE' });
            if (res.status === 204) {
                msg.textContent = 'Deleted';
                loadStudents();
            } else {
                msg.textContent = 'Delete failed';
            }
        } catch (e) {
            console.error(e);
            msg.textContent = 'Network error while deleting';
        }
    }

    form.addEventListener('submit', (ev) => {
        ev.preventDefault();
        const formData = new FormData(form);
        const data = {
            rollNumber: formData.get('rollNumber').trim(),
            firstName: formData.get('firstName').trim(),
            lastName: formData.get('lastName').trim(),
            email: formData.get('email').trim()
        };
        addStudent(data);
    });

    // initial load
    loadStudents();
});
