<?php
header('Access-Control-Allow-Origin: *');
$server = "localhost";
$user = "id18739966_huy17";
$pass = "Huy@17122003";
$db = "id18739966_myguests";
$con = new mysqli($server, $user, $pass, $db);
if($con->connect_error){
     die("Loi ket noi: ".$con->connect_error);
}
$sql = "SELECT * FROM MyGuests";
$result = $con->query($sql);
$rows = array();
while($row = $result->fetch_assoc()){
     $rows[] = $row;
}
$json = json_encode($rows);
echo '{"MyGuests":' . $json . '}';
$con->close();
?>
