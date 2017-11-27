<?php
session_start();
echo "Welcome ".$_SESSION['username'].", this is home page for Hospital";
if(isset($_POST['logout'])){
	unset($_SESSION['id']);
	unset($_SESSION['username']);
	unset($_SESSION['type']);
	session_destroy();
	header("location:../index.php");
}
?>
<form action="home.php" method="post">
<input type="submit" name="logout" value="Logout">
</form>