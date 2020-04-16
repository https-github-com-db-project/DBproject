<!DOCTYPE html>
<html>

  <head>
    <meta charset="utf-8"/>
    <link rel="icon" type="text/css" href="icon.png">
    <title>Berchonyn kitaptary</title>
    <link rel="stylesheet" href="final.css">
    <style >body{
    
    color: blue;
}

h1{
    margin:11px 20px;
}

form{
    margin: 30px 20px;
}

.form-input{
    margin: 20px 0;
    padding: 0 2px;
}

.form-textarea{
    margin: 0 0 20px;
    padding: 2px;
}

.form-button{
    margin: 20px 0;
    color: blue;
}
.error {color: #FF0000;}
</style>
  </head>

  <body>



    
   <div class="p1"> Berchonyn kitaptary
    <img class="logo" src="icon.png" width="130" height="170" />
    <a href="publis.html"><img class="user"  src="man-user.png" width="40" height="60" /></a>
   </div>


    <div id = "conteiners ">
      <header>
        <nav>
          <a href="index.html" class="active">HOME</a><a href="#aboutus">ABOUT</a><a href="#news">NEWS & EVENTS</a><a href="#index-gallery">CONTACT</a>
        </nav>
      </header>
    </div>
  
<body>
  <?php
// define variables and set to empty values
$nameErr = $aboutErr = $priceErr = $imageErr = "";
$name = $about = $price =  $image = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  if (empty($_POST["name"])) {
    $nameErr = "Name is required";
  } else {
    $name = test_input($_POST["name"]);
  }
  
  if (empty($_POST["about"])) {
    $aboutErr = "Text is required";
  } else {
    $about = test_input($_POST["about"]);
  }
    
  if (empty($_POST["image"])) {
    $image = "";
  } else {
    $website = test_input($_POST["image"]);
  }

  if (empty($_POST["price"])) {
    $genderErr = "Price is required";
  } else {
    $gender = test_input($_POST["price"]);
  }
}

function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
?>
    <h1>Введите данные книги</h1>
    <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
        <label for="name">Название:</label>
        <input class="form-input" type="text" id="name" placeholder="Введите название" > <br>
          <span class="error">* <?php echo $nameErr;?></span>
          <br><br>

        <label  for="about">Описание:</label> <br>
        <textarea class="form-textarea" id="about" name="about"  cols="32" rows="10" ></textarea> <br>
          <span class="error">* <?php echo $aboutErr;?></span>
  <br><br>

        <label for="price">Цена:</label>
        <input class="form-input" type="number" id="price" placeholder="Введите цену"> <br>
          <span class="error">* <?php echo $priceErr;?></span>
  <br><br>

        <label for="image">Рисунок:</label>
        <input class="form-input" type="text" id="image" > <br>
          <span class="error">* <?php echo $imageErr;?></span>
  <br><br>

        

        <button class="form-button" type="submit">Сохранить</button>
    </form>

   
</body>

  


<div id="aboutus">
	<div class="item3"> <h1>Sponsors</h1> <p>Meloman</p> <p>Marwin</p> <p>Disney</p></div>
	<div class="item3"> <h1>Contact</h1> <p>Telefon:8747 322 73 83</p>
    <p>E-mail:berikbeisen98@gmail.com</p> <p>Vk:Beisen Berik</p> <p>Instagram:beisen_b_m</p> </div>
</div>

 </body>
</html>
