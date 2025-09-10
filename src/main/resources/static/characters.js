const apiUrl = 'http://localhost:8181/character';

async function fetchCharacters() {
    const response = await fetch(`${apiUrl}/all`);
    const characters = await response.json();
    const tbody = document.getElementById('charactersTableBody');
    tbody.innerHTML = '';

    characters.forEach(char => {
        const tr = document.createElement('tr');

        tr.innerHTML = `
            <td><img src="${char.imageUrl || 'https://via.placeholder.com/80'}" alt="Image"/></td>
            <td>${char.name}</td>
            <td>${char.email}</td>
            <td>${char.age}</td>
            <td>${char.faction}</td>
            <td>${char.rank}</td>
            <td>${char.homeland}</td>
            <td>${char.mission ? char.mission.title : 'None'}</td>
            <td>
                <button onclick="deleteCharacter('${char.id}')">Delete</button>
                <button onclick="updateCharacterPrompt('${char.id}')">Update</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

// CREATE
document.getElementById('createForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const character = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        age: parseInt(document.getElementById('age').value),
        faction: document.getElementById('faction').value,
        rank: document.getElementById('rank').value,
        homeland: document.getElementById('homeland').value
    };

    await fetch(`${apiUrl}/create`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(character)
    });

    e.target.reset();
    fetchCharacters();
});

// DELETE
async function deleteCharacter(id) {
    if(confirm('Are you sure you want to delete this character?')) {
        await fetch(`${apiUrl}/delete/${id}`, { method: 'DELETE' });
        fetchCharacters();
    }
}

// UPDATE (simple prompt)
async function updateCharacterPrompt(id) {
    const newName = prompt('Enter new name:');
    if(!newName) return;

    const updatedCharacter = { name: newName }; // for simplicity, only updating name
    await fetch(`${apiUrl}/update/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedCharacter)
    });

    fetchCharacters();
}

// Initial fetch
fetchCharacters();
