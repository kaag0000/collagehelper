<?php

include_once '../Helpers/Helper.php';

    class Registration
    {
       private $helper;

    public function __construct(){
        $this->helper = new Helper();
    }

       public function setuserRegistration($rollno,$name,$colname,$branch,$group,$type,$emailid,$phoneno,$pass)
       {
           $response = array();
           if($this->helper->isUserExist($rollno,$emailid,$phoneno))
           {
            $response['success'] = 0;
            $response['message'] = "Error in registering. Probably the username/email already exists";
            return $response;
           }
           else{
               
               if($this->helper->isValidEmail($emailid))
               {
                   $uid = $this->helper->userIdGenreator();
                   $response=$this->helper->createNewUserIntoDb($uid,$rollno,$name,$colname,$branch,$group,$type,$emailid,$phoneno,$pass);
                   return $response;
               }
               else
               {
                $response['success'] = 0;
                $response['message'] = "Error in registering. Email Address is not valid";
                return $response;
               }
           }
                $response['success'] = 0;
                $response['message'] = "Something Went wrong";
                return $response;
       }
    }
    
?>