
fetch('http://localhost/webka%20final/image.json')
.then(response => response.json())
.then(data => {
    var divImage = document.getElementById('fotoanimation');
    divImage.innerHTML = data.image;
    var mainImg = document.getElementById('mainImg');
})
.catch(error => console.error(error))