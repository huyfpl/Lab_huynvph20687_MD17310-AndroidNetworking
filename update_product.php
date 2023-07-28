<?php


$response = array();

$servername = "localhost";
$username = "id18739966_huynvph20687";
$password = "Huy@17122003";
$dbname = "id18739966_quanlyduan";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

// check for required fields
if (isset($_POST['pid']) && isset($_POST['name']) && isset($_POST['price']) && isset($_POST['description'])) {

    $pid = $_POST['pid'];
    $name = $_POST['name'];
    $price = $_POST['price'];
    $description = $_POST['description'];

    
    $sql = "UPDATE products SET name = '$name', price = '$price', description = '$description' WHERE pid = $pid";

    if ($conn->query($sql) === TRUE) {
      //echo "Record updated successfully";
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";

        // echoing JSON response
        echo json_encode($response);
    } else {
      echo "Error updating record: " . $conn->error;
    }

    $conn->close();

} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
