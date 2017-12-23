<?php
session_start();
if(isset($_SESSION['id'])){
	header("location:home.php");
}
?>
<html>
<head>
<title>
Login
</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">


<!--Bootstrap Files-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/styles.css">

</head>

<body>

<div class="container-fluid">
<h3>Login</h3>

<form action="login.php" method="POST">
  <div class="form-group">
    <label for="username">Username</label>
    <input type="text" class="form-control" required name="username" id="username" placeholder="Enter Username">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" class="form-control" required name="password" id="password" placeholder="Enter Password">
  </div>
  <div class="form-group">
    <label for="type">Type</label>
    <select class="form-control" name="type" id="type">
      <option value="admin">Admin</option>
	  <option value="medic">Medic</option>
      <option value="hospital">Hospital</option>
    </select>
  </div>
  
	<?php
		if(isset($_GET['login']) && $_GET['login']=="failed"){
			echo "<p> Wrong username or password </p>";
		}
	?>
  <button type="submit" name="submit" style="color:#d50000;font-weight: bold;width:100%;" class="btn btn-light">Submit</button>
</form>

</div>


</body>

</html>