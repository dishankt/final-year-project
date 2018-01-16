
<?php
	$conn = mysqli_connect('localhost','root','','project');
	if(isset($_POST['delete'])){
		$id = $_POST['id'];
		$query = "DELETE FROM hospital WHERE id=$id;";
		mysqli_query($conn,$query);	
	}
	else if(isset($_POST['edit'])){
		$id = $_POST['id'];
		$username=strip_tags($_POST['username']);
		$username=stripslashes($username);
		$username=mysqli_real_escape_string($conn,$username);

		$password=strip_tags($_POST['password']);
		$password=stripslashes($password);
		$password=mysqli_real_escape_string($conn,$password);
		$password=hash('sha512',$password);
		
		$query = "UPDATE hospital SET username='$username',password='$password' WHERE id=$id";
		if(!mysqli_query($conn,$query)){
			die("Username already taken");
		}
	}
	header("location:modify-hospital.php");

?>