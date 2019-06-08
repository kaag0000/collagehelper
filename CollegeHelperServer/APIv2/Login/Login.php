<?php

include_once 'LoginProcess.php';

$json = array();
if($_SERVER['REQUEST_METHOD'] == 'POST')
{

   $rollno="";
   $password="";

      
    if(isset($_POST['rollno']))
    {
        $rollno = $_POST['rollno'];
    }
    if(isset($_POST['pass']))
    {
        $pass = $_POST['pass'];
    }

    if(!empty($rollno) || !empty($pass))
    {
        $user_data = new Login();
        $json= $user_data->loginUser($rollno,$pass);
        echo json_encode($json);
    }
    else{
        $json['success'] = 0;
        $json['message'] = "Invalid Credentials";
        echo json_encode($json);
    }
    
} 
else
{
    $json['success'] = 0;
    $json['message'] = "Bad Request";
    echo json_encode($json);
}
?>