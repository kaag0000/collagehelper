<?php

include_once 'RegistrationProcess.php';

$json = array();
if($_SERVER['REQUEST_METHOD'] == 'POST')
{

    $rollno = "";
    $name = "";
    $colname = "";
    $branch = "";
    $group = "";
    $type = "";
    $emailid = "";
    $phoneno = "";
    $pass = "";


    if(issete($_POST['rollno']))
    {
        $rollno = $_POST['rollno'];
    }
    if(isset($_POST['name']))
    {
        $name = $_POST['name'];
    }
    if(isset($_POST['colname']))
    {
        $colname = $_POST['colname'];
    }
    if(isset($_POST['branch']))
    {
        $branch = $_POST['branch'];
    }
    if(isset($_POST['group']))
    {
        $group = $_POST['group'];
    }
    if(isset($_POST['type']))
    {
        $type = $_POST['type'];
    }
    if(isset($_POST['email']))
    {
        $emailid = $_POST['email'];
    }
    if(isset($_POST['phoneno']))
    {
        $phoneno = $_POST['phoneno'];
    }
    if(isset($_POST['pass']))
    {
        $pass = $_POST['pass'];
    }

    if(!empty($rollno) || !empty($name) || !empty($colname) || !empty($branch) || !empty($group) || !empty($type) || !empty($phoneno) || !empty($emailid) || !empty($pass))
    {
        $user_data = new Registration();
        $json= $user_data->setuserRegistration($rollno,$name,$colname,$branch,$group,$type,$emailid,$phoneno,$pass);
        echo json_encode($json);
    }
    else{
        $json['success'] = 0;
        $json['message'] = "Invalid Request";
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