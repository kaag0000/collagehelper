<?php
include_once '../db_config/db_connect.php';
include_once 'Encryption.php';

class Helper{
        private $db;
        private $bcrypt;
        private $db_table;
       public function __construct(){
        $this->db = new DbConnect();
        $this->bcrypt = new Bcrypt(15);
        $this->db_table = "user";
       }




       public function checkUser($rollno,$pass){
        //$hashpass = $this->bcrypt->hash($pass);
        $hashpass=md5($pass);

      /*  $resp = $this->bcrypt->verify($pass,$hashpass);
        if($resp)
        {
        echo "true";
        } */
        $query = "SELECT * FROM ".$this->db_table." WHERE ROLL_NO = '$rollno' AND PASS = '$hashpass' LIMIT 1";
        $result = mysqli_query($this->db->getDb(), $query);
        $response=array();  
        $row = $result->fetch_assoc();
        if(mysqli_num_rows($result) > 0){
            
          
            $row['success'] = 1;
            $row['message'] = "Successfully Login";
            mysqli_close($this->db->getDb());
            return $row;
            
        }
           
        $response['success'] = 0;
        $response['message'] = "Error occured while Login user Please try again";
        mysqli_close($this->db->getDb());
        return $response;

       }

       public function isUIDExist($uid){
            
        $query = "SELECT * FROM ".$this->db_table." WHERE UID = '$uid'";
        
        $result = mysqli_query($this->db->getDb(), $query);
        
        if(mysqli_num_rows($result) > 0){
            
            mysqli_close($this->db->getDb());
            
            return true;
            
        }
           
        return false;
        
    }

        public function isUserExist($rollno,$emailid,$phoneno)
        {
            $query = "SELECT * FROM ".$this->db_table." WHERE EMAIL = '$emailid' AND ROLL_NO = '$rollno' AND PHONE_NO = '$phoneno'";
        
            $result = mysqli_query($this->db->getDb(), $query);
            
            if(mysqli_num_rows($result) > 0){
                
                mysqli_close($this->db->getDb());
                
                return true;
                
            }
               
            return false; 
        }

        public function isValidEmail($email){
        
            $email = filter_var($email, FILTER_SANITIZE_EMAIL);
                if (filter_var($email, FILTER_VALIDATE_EMAIL)) {
                    return true;
                } else {
                return false;
                }
       }

       public function userIdGenreator() 
       {
           $size = 10;
           $alpha_key = '';
           $keys = range('A', 'Z');

           for ($i = 0; $i < 2; $i++) {
               $alpha_key .= $keys[array_rand($keys)];
           }

           $length = $size - 2;

           $key = '';
           $keys = range(0, 9);

           for ($i = 0; $i < $length; $i++) {
               $key .= $keys[array_rand($keys)];
           }
           $id = $alpha_key . $key;
           if($this->isUIDExist($id))
           {
               $this->userIdGenreator();
           }
           else
           {
           return $id;
           }
       }

       public function createNewUserIntoDb($uid,$rollno,$name,$colname,$branch,$group,$type,$emailid,$phoneno,$pass)
       {
        $response = array();
  //$hashpass = $this->bcrypt->hash($pass);
        $hashpass=md5($pass);
        $query = "INSERT INTO ".$this->db_table." (UID, ROLL_NO, NAME, COLLEGE_NAME,BRANCH, GROUP_,TYPE,EMAIL,PHONE_NO,PASS) values ('$uid', '$rollno', '$name', '$colname', '$branch', '$group', '$type','$emailid','$phoneno','$hashpass')";
        $hash = $this->bcrypt->hash($pass);
        $inserted = mysqli_query($this->db->getDb(), $query);
        if($inserted==1)
        {
            $response['success'] = 1;
            $response['message'] = "Successfully registered the user";
            mysqli_close($this->db->getDb());
            return $response;
        }
        else{
            $response['success'] = 0;
            $response['message'] = "Error occured while Registering user Please try again";
            mysqli_close($this->db->getDb());
            return $response;
        }

       }
}

?>