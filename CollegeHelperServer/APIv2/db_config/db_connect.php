<?php
include_once 'db_config.php';

class DbConnect{
    private $connect;

    public function __construct(){
    
        $this->connect = mysqli_connect(DB_HOST,DB_USERNAME,DB_PASSWORD,DB_NAME);
        if(mysqli_connect_errno($this->connect))
        {
            echo "unable to connect database".mysqli_connect_error();
        }
        else
        {
        echo "database connected";
        }
    }
    

    public function getDb()
    {
        
        return $this->connect;

    }
}


$test = new DbConnect();






?>
