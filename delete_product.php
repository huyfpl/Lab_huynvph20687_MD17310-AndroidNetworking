<?php

/*
 * Following code will delete a product from table
 * A product is identified by product id (pid)
 */

// array for JSON response
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
if (isset($_POST['pid'])) {
    $pid = $_POST['pid'];

    // sql to delete a record
    $sql = "DELETE FROM products WHERE pid = $pid";

    if ($conn->query($sql) === TRUE) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully deleted";

        // echoing JSON response
        echo json_encode($response);
      //echo "Record deleted successfully";
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";

        // echo no users JSON
        echo json_encode($response);
      //echo "Error deleting record: " . $conn->error;
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
