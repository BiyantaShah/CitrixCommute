<?php
    $con = mysqli_connect("citrixcommute.000webhostapp.com", "id10458033_admin", "carpool2019", "id10458033_citrixcommute");

    $name = $_POST["name"];
    $password = $_POST["password"];
    $emailid = $_POST["emailid"];
    $homeaddress = $_POST["homeaddress"];

    $statement = mysqli_prepare($con, "INSERT INTO user (name, password, emailid, homeaddress) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name, $password, $emailid, $homeaddress);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>