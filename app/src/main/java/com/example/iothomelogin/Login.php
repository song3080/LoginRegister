<?php
    $con = mysqli_connect("localhost", "dbid231", "dbpass231", "60002");
    mysqli_query($con,'SET NAMES utf8');

    $id = isset($_POST["userid"]) ? $_POST["userid"] : "";
    $password = isset($_POST["password"]) ? $_POST["password"] : "";

    $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE id = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $id, $password);
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userid, $password, $phone, $email);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["userid"] = $id
        $response["password"] = $password;
        $response["phone"] = $phone;
        $response["email"] = $email;
    }

    echo json_encode($response);



?>