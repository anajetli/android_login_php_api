<?php
define('HOST', 'localhost');
define('USER', 'root');
define('PASSWORD', '');
define('DB', 'mad');


if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$userID = $_POST['user_id'];
	$userPassword = $_POST['user_password'];
	if($userID == '' || $userPassword == ''){
		echo "fail";
		exit;
	}
	
	$con = mysqli_connect(HOST, USER, PASSWORD, DB) or die("Unable to Connect");
	
	$query = "SELECT * FROM users WHERE email = '$userID' AND password = '$userPassword'";
	$result = mysqli_query($con, $query);
	$data = mysqli_fetch_array($result);
	if(isset($data)){
		echo "eyJpdiI6IjVGb1JNN1E3YlFDakpjUG5leTQwMnc9PSIsInZhbHVlIjoiaU1sWjlZN0RBbHpKZ05hSnlTOXBWdXVGYTVaN1wvV1pLNkFGSmhWOGlJSkhldUE1MjdLMTlycWgyb25WK1FuMGUiLCJtYWMiOiJkNzRlNTQ5OWI5N2IwYTZkNTIyNmM5M2IyNTNjNjA4ZmMwNDFlNGYxZTdhNjY2ZjIxNzgzM2Y0OTE5NmY1ZjA1In0%3D; eims_session=eyJpdiI6IjVqOUdhMDFLcHJnZjh4VkVnYk9MTHc9PSIsInZhbHVlIjoib1BxMExmZmQzTERWaTVhalNvOE0xalZHOEZXS0VRZ1lqTVwveXUrZWpKZzZZc0lhZm9VVThUSlZUVkV4elVFaWEiLCJtYWMiOiIzN2M4MGJjMmIyNTE0ZGU1MjU2YTc1ZDk3MDY4MmZkZGNlNGIzYzFjYzFlMjMzZDc0OWVhY2E1YjhkYjNkMGMyIn0%3D";
	}else{
		echo "fail";
	}
	mysqli_close($con);
}else{
	echo "invalid method";
}
?>