<?php
$server = "localhost";
$user = "id18739966_huy17";
$pass = "Huy@17122003";
$db = "id18739966_myguests";
$con = new mysqli($server, $user, $pass, $db);
if($con->connect_error){
     die("Loi ket noi: ".$con->connect_error);
}
$sql = "UPDATE MyGuests SET lastname='huy 17' WHERE id=184";
if($con->query($sql) === TRUE){
     echo "Update thành công";
}
else{
     echo "Lỗi: ".$con->error;
}
$con->close();
?>
