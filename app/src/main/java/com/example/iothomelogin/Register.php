    $con = mysqli_connect("localhost", "dbid231", "dbpass231", "db23102");
    mysqli_query($con,'SET NAMES utf8');

    $id = isset($_POST["id"]) ? $_POST["id"] : "";
    $password = isset($_POST["password"]) ? $_POST["password"] : "";
    $email = isset($_POST["email"]) ? $_POST["email"] : "";
    $phone = isset($_POST["phone"]) ? $_POST["phone"] : "";

    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssi", $id, $password, $email, $phone);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;


    echo json_encode($response);



?>