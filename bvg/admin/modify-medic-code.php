<?php
	include '../include/db.php';
	if(isset($_POST['delete'])){
		$id = $_POST['id'];
		$query = "DELETE FROM medic WHERE id=$id;";
		mysqli_query($conn,$query);
	}
	else if(isset($_POST['edit'])){
		$id = $_POST['id'];
		$fname=strip_tags($_POST['fname']);
		$fname=stripslashes($fname);
		$fname=mysqli_real_escape_string($conn,$fname);
			
		$lname=strip_tags($_POST['lname']);
		$lname=stripslashes($lname);
		$lname=mysqli_real_escape_string($conn,$lname);
											
		$address=strip_tags($_POST['address']);
		$address=stripslashes($address);
		$address=mysqli_real_escape_string($conn,$address);

		$email=strip_tags($_POST['email']);
		$email=stripslashes($email);
		$email=mysqli_real_escape_string($conn,$email);
			
		$username=strip_tags($_POST['username']);
		$username=stripslashes($username);
		$username=mysqli_real_escape_string($conn,$username);

		$password=strip_tags($_POST['password']);
		$password=stripslashes($password);
		$password=mysqli_real_escape_string($conn,$password);
		$password=hash('sha512',$password);
											
		$phone = $_POST['phone'];
			
		$dob = $_POST['dob'];
		$msg = "";					
		
		if(!preg_match("/^[a-zA-Z]*$/",$fname)){
			$msg = "First name contains characters other than alphabets";
		}else if(!preg_match("/^[a-zA-Z]*$/",$lname)){
			$msg = "Last name contains characters other than alphabets";
		}else if(strlen($phone)!=10){
			$msg = "Not a valid phone number ";
		}else{
			
			$query="UPDATE medic SET username='$username',password='$password',fname='$fname',lname='$lname',address='$address',dob='$dob',phone=$phone,email = '$email' WHERE id=$id;"; 

			if(mysqli_query($conn,$query)){		
				$msg = "Changes made successfully";
			}else{
				$msg = "Username already taken";
			}											
		}
	}
	header("location:modify-medic.php?msg=$msg");

?>