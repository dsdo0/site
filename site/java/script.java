let currentQuestion = 0;
const totalQuestions = 15;

function nextQuestion() {
    const questions = document.querySelectorAll('.question');
    const current = questions[currentQuestion];
    const nextBtn = document.getElementById('next-btn');

    if (!current.querySelector('input[type="radio"]:checked')) {
        alert('Por favor, selecione uma resposta.');
        return;
    }

    current.classList.add('fade-out');
    setTimeout(() => {
        current.classList.remove('active', 'fade-out');
        currentQuestion++;
        
        if (currentQuestion < totalQuestions) {
            questions[currentQuestion].classList.add('active', 'fade-in');

            if (currentQuestion === totalQuestions - 1) {
                nextBtn.textContent = 'Resultado';
            }
        } else {
            calculateScore();
            nextBtn.style.display = 'none';
            document.getElementById('prev-btn').style.display = 'none';
        }

        togglePrevButton();
    }, 500); // Tempo para a animação de fade out
}

function prevQuestion() {
    const questions = document.querySelectorAll('.question');
    const current = questions[currentQuestion];

    current.classList.add('fade-out');
    setTimeout(() => {
        current.classList.remove('active', 'fade-out');
        currentQuestion--;

        questions[currentQuestion].classList.add('active', 'fade-in');

        const nextBtn = document.getElementById('next-btn');
        if (currentQuestion < totalQuestions - 1) {
            nextBtn.textContent = 'Próxima Pergunta';
        }

        togglePrevButton();
    }, 500); // Tempo para a animação de fade out
}

function togglePrevButton() {
    const prevBtn = document.getElementById('prev-btn');
    prevBtn.style.display = currentQuestion === 0 ? 'none' : 'inline-block';
}

function calculateScore() {
    const form = document.getElementById('quiz-form');
    let score = 0;

    for (let i = 1; i <= totalQuestions; i++) {
        const answer = form['q' + i];
        for (const option of answer) {
            if (option.checked) {
                score += parseInt(option.value);
            }
        }
    }

    const percentage = (score / (totalQuestions * 10)) * 100;
    const resultDiv = document.getElementById('result');
    
    resultDiv.classList.add('fade-in');
    
    if (percentage > 70) {
        showVideo();
    } else if (percentage <= 20) {
        resultDiv.innerHTML = `Parabéns! Sua pontuação de poluição é apenas ${percentage.toFixed(2)}%. Suas ações já estão ajudando muito o meio ambiente! Continue assim!`;
    } else if (percentage > 20 && percentage <= 40) {
        resultDiv.innerHTML = `Muito bem! Sua pontuação de poluição é ${percentage.toFixed(2)}%. Suas ações são boas, mas ainda há espaço para melhorar!`;
    } else if (percentage > 40 && percentage <= 70) {
        resultDiv.innerHTML = `Você está indo bem! Sua pontuação de poluição é ${percentage.toFixed(2)}%. No entanto, é importante melhorar suas práticas para ajudar ainda mais o meio ambiente!`;
    }
}

function showVideo() {
    const videoContainer = document.getElementById('video-container');
    videoContainer.style.display = 'flex';
    videoContainer.classList.add('fade-in');

    const video = document.getElementById('warning-video');
    video.play();

    try {
        const context = new (window.AudioContext || window.webkitAudioContext)();
        const gainNode = context.createGain();
        gainNode.gain.value = 1;  // Volume máximo permitido

        const source = context.createMediaElementSource(video);
        source.connect(gainNode).connect(context.destination);

        video.volume = 1.0;
        video.addEventListener('volumechange', function() {
            if (video.volume < 1.0) {
                video.volume = 1.0;
            }
        });

        video.addEventListener('ended', function() {
            videoContainer.style.display = 'none';
            const resultDiv = document.getElementById('result');
            resultDiv.innerHTML = `Sua pontuação foi alta. Por favor, repense suas atitudes para ajudar a salvar o planeta Terra!`;
            resultDiv.classList.add('fade-in');
        });
    } catch (error) {
        console.log("O controle total de volume não é suportado no navegador atual.");
        alert("Por favor, aumente o volume do seu dispositivo ao máximo para assistir o vídeo.");
    }
}

// Inicializa o estado dos botões ao carregar a página
togglePrevButton();
