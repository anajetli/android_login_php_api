<?php
define('HOST', 'localhost');
define('USER', 'root');
define('PASSWORD', '');
define('DB', 'mad');

$con = mysqli_connect(HOST, USER, PASSWORD, DB) or die("Unable to Connect");
$query = "SELECT * FROM users";
$result = mysqli_query($con, $query);
while($row = mysqli_fetch_array($result)) {
 
	$flag[] = $row;
}
 
print(json_encode($flag));
mysqli_close($con);
?>