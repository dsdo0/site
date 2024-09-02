const photoContainer = document.querySelector('.photo-container');
const photos = document.querySelectorAll('.photo');
let currentIndex = 0;

document.getElementById('nextButton').addEventListener('click', function() {
    if (currentIndex < photos.length - 1) {
        currentIndex++;
        updateGallery();
    }
});

document.getElementById('prevButton').addEventListener('click', function() {
    if (currentIndex > 0) {
        currentIndex--;
        updateGallery();
    }
});

function updateGallery() {
    const offset = -currentIndex * 100;
    photoContainer.style.transform = `translateX(${offset}%)`;
}