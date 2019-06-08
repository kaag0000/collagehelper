<?php

include_once '../Helpers/Helper.php';

    class Login
    {
       private $helper;

    public function __construct(){
        $this->helper = new Helper();
    }

       public function loginUser($rollno,$pass)
       {
           $response = array();

           $response=$this->helper->checkUser($rollno,$pass);

           return $response;
       }
           
    }
    
?>