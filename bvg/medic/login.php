<?php
$conn = mysqli_connect('localhost','root','','project');
$username = $_POST['username'];
$password = hash("sha512",$_POST['password']);
$query = "SELECT * FROM medic where username='$username' and password = '$password'";
mysqli_query($conn,$query);
if(mysqli_affected_rows($conn)!=0){
	$success = true;
	$username = $username;
}else{
	$success = false;
	$username = "";
}
$array = array("success"=>$success,"username"=>$username);
$result = json_encode($array);
echo $result;
?>