<?php
if(isset($_POST['submit'])){
$conn= mysqli_connect('localhost','root','','project');

$username=strip_tags($_POST['username']);
$username=stripslashes($username);
$username=mysqli_real_escape_string($conn,$username);

$password=strip_tags($_POST['password']);
$password=stripslashes($password);
$password=mysqli_real_escape_string($conn,$password);
$password=hash('sha512',$password);

$type = $_POST['type'];
 
$query="SELECT * from $type where username='$username' and password='$password'"; 

$r=mysqli_query($conn,$query);
$array = mysqli_fetch_array($r);
$count=mysqli_affected_rows($conn);
if($count>0){
	session_start();
	$_SESSION['id']=$array['id'];
	$_SESSION['username'] = $array['username'];
	$_SESSION['type'] = $type;
	$header = "location:".$type."/home.php";
	header($header);
}else{
	header("location:index.php?login=failed");
}
}
else{
	header("location:index.php");
}
?>