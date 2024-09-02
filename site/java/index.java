const users = JSON.parse(localStorage.getItem('users')) || {};

document.getElementById('loginButton').addEventListener('click', function() {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();

    if (!username || !password) {
        document.getElementById('message').innerText = 'Preencha todos os campos.';
        return;
    }

    if (users[username] && users[username] === password) {
        document.getElementById('message').innerText = 'Login bem-sucedido!';
        animateLoginSuccess();
    } else {
        document.getElementById('message').innerText = 'Usuário ou senha inválidos.';
    }
});

document.getElementById('createAccountButton').addEventListener('click', function() {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();

    if (!username || !password) {
        document.getElementById('message').innerText = 'Preencha todos os campos.';
        return;
    }

    if (users[username]) {
        document.getElementById('message').innerText = 'Usuário já existe.';
    } else {
        users[username] = password;
        localStorage.setItem('users', JSON.stringify(users)); // Salva os usuários no localStorage
        document.getElementById('message').innerText = 'Conta criada com sucesso!';
    }
});

function animateLoginSuccess() {
    const loginForm = document.querySelector('.login-form');
    loginForm.classList.add('login-success');
    setTimeout(() => {
        loginForm.classList.remove('login-success');
        window.location.href = 'index7.html'; 
    }, 1000);
}