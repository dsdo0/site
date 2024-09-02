function createSmoke() {
    const smokeContainer = document.getElementById('smoke');

    for (let i = 0; i < 20; i++) {
        const smokeElement = document.createElement('div');
        smokeElement.className = 'smoke';
        smokeElement.style.left = `${Math.random() * 100}%`;
        smokeElement.style.animationDelay = `${Math.random() * 5}s`;
        smokeContainer.appendChild(smokeElement);
    }
}

createSmoke();