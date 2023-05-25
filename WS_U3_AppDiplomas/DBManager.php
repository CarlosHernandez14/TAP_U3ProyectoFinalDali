<?php

class DBManager {
    private $db;
	private $host;
	private $user;
	private $pass;
    private $port;


    public function __construct() {
        $this->db = "app_diplomas";
        $this->host = "localhost";
        $this->user = "root";
        $this->pass = null;
        $this->port = 3306;
    }

    public function open()
    {
        $link = mysqli_connect(
            $this->host, $this->user, $this->pass, $this->db, $this->port
        ) or die("Error al abrir la conexion a la DB");
        return $link;
    }

    public function close($link)
    {
        mysqli_close($link);
    }

    public function addUser($username, $password, $tipo_usuario)
    {
        $link = $this->open();

        $sql = "INSERT INTO usuario (username, password, tipo_usuario) VALUES (?, ?, ?)";

        $stmt = $link->prepare($sql);
        $stmt->bind_param("sss", $username, $password, $tipo_usuario);

        $result = $stmt->execute();
        $idLastAddedUser = $link->insert_id;

        $stmt->close();
        $this->close($link);

        if ($result) {
            return $idLastAddedUser;
        } else {
            return false;
        }

    }

    public function getUserById($idUsuario)
    {
        $link = $this->open();

        $sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        
        $stmt = $link->prepare($sql);
        $stmt->bind_param("i", $idUsuario);
        $stmt->execute();
        
        $result = $stmt->get_result();
        $user = $result->fetch_assoc();

        $stmt->close();
        $this->close($link);
        
        return $user;
    }

    public function login($username, $password)
    {
        $link = $this->open();

        $sql = "SELECT * FROM usuario WHERE username = ? AND password = SHA1(?)";
        
        $stmt = $link->prepare($sql);
        $stmt->bind_param("ss", $username, $password);

        $stmt->execute();
        $result = $stmt->get_result();
        $user = $result->fetch_assoc();

        $stmt->close();
        $this->close($link);

        if ($user) {
            return $user;
        }
        return false;
    }

    public function updateUser($idUsuario, $username, $password, $tipo_usuario)
    {
        $link = $this->open();

        $sql = "UPDATE usuarios SET username = ?, password = ?, tipo_usuario = ? WHERE idUsuario = ?";
        
        $stmt = $link->prepare($sql);
        $stmt->bind_param("sssi", $username, $password, $tipo_usuario, $idUsuario);
        
        if ($stmt->execute()) {
            $stmt->close();
            $this->close($link);
            return true;
        } else {
            $stmt->close();
            $this->close($link);
            return false;
        }
    }

    // OPERATION FOR DIRECTORS TABLE
    // Return true or false according with the result
    public function addDirector($username, $password, $tipo_usuario, $grado, $firma, $nombre)
    {
        $idUsuario = $this->addUser($username, $password, $tipo_usuario);

        if ($idUsuario) {
            $link = $this->open();
            $sql = "INSERT INTO Director (grado, firma, nombre, Usuario_idUsuario) VALUES (?, ?, ?, ?)";

            $stmt = $link->prepare($sql);
            $stmt->bind_param("sssi", $grado, $firma, $nombre, $idUsuario);

            // Envía los datos de la firma como un blob
            $firmaStream = fopen("php://memory", "r+");
            fwrite($firmaStream, $firma);
            rewind($firmaStream);
            $stmt->send_long_data(1, stream_get_contents($firmaStream));
            fclose($firmaStream);

            if ($stmt->execute()) {
                $stmt->close();
                $this->close($link);
                return true;
            } else {
                $stmt->close();
                $this->close($link);
                return false;
            }
        }
    }


}


?>