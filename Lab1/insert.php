<?php
$server = "localhost";
$user = "id18739966_huy17";
$pass = "Huy@17122003";
$db = "id18739966_myguests";
$con = new mysqli($server, $user, $pass, $db);

// taoj cau lenh

$sql = "INSERT INTO MyGuests(firstname, lastname, email)
VALUES ('huy', 'Nguyễn', 'huynvph20687@fpt.edu.vn')";

for($i = 1; $i <10; $i++)
{
    if ($con->query($sql) === true)
    if ($i == 9) {
        $con -> close();
    }
}
?>
