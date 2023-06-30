<?php
$server = "localhost";
$user = "id18739966_huy17";
$pass = "Huy@17122003";
$db = "id18739966_myguests";
$con = new mysqli($server, $user, $pass, $db);
if($con->connect_error){
     die("Loi ket noi: ".$con->connect_error);
}

$sql = "DELETE FROM MyGuests WHERE id = 163";
if($con->query($sql) === TRUE){
     echo "Delete thành công";
}
else{
     echo "Lỗi: ".$con->error;
}
?>
