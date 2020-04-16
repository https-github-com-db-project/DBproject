let imgArr = new Array();
let carouselId = document.querySelector("#carousel");
for (var i = 0; i < 5; i++) {
    imgArr[i] = carouselId.querySelectorAll("img")[i];
}
var bigImage = document.querySelector("#bigImage");
for (var i = 0; i < 5; i++) {
    imgArr[i].addEventListener("click",function(e){
        console.log(e.currentTarget.src);
        bigImage.querySelectorAll("img")[0].src = e.currentTarget.src;
    })
}

function validateForm() {
  var x = document.forms["myForm"]["fname"].value;
  if (x == "") {
    alert("Введите количество книг!");
    return false;
  }
}


