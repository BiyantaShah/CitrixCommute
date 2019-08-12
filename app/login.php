<?php
    $con = mysqli_connect("citrixcommute.000webhostapp.com", "id10458033_admin", "carpool2019", "id10458033_citrixcommute");

    $emailid = $_POST["emailid"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE emailid = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $emailid, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $password, $emailid, $homeaddress, $type, $countofpassenger, $picked);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["name"] = $name;
        $response["password"] = $password;
        $response["emailid"]=$emailid;
        $response["homeaddress"] = $homeaddress;
        $response["type"] = $type;
        $response["countofpassenger"] = $countofpassenger;
        $response["picked"] = $picked;
    }

    echo json_encode($response);
?>