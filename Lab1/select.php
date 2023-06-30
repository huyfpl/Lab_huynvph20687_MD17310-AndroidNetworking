<?php
$server = "localhost";
$user = "id18739966_huy17";
$pass = "Huy@17122003";
$db = "id18739966_myguests";
$con = new mysqli($server, $user, $pass, $db);
if ($con->connect_error) {
    die("Lỗi kết nối: " . $con->connect_error);
}
$sql = "SELECT id, firstname, lastname, email FROM MyGuests";
$result = $con->query($sql);
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        echo "id: " . $row["id"] . " - First: " . $row["firstname"] . " - Last: " . $row["lastname"] . " - Email: " . $row["email"] . "<br>";
    }
} else {
    echo "Không có dữ liệu";
}
$con->close();
?>
