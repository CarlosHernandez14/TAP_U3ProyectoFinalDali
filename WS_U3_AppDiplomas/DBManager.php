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

    public function createUser($username, $password, $tipo_usuario)
    {
        $link = $this->open();

        $sql = "INSERT INTO usuario (username, password, tipo_usuario) VALUES (?, SHA1(?), ?)";

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

    public function showUsers()
    {
        $link = $this->open();

        $sql = "SELECT * FROM usuario";
        $result = mysqli_query($link, $sql);

        if ($result) {
            $users = [];
            while ($row = mysqli_fetch_assoc($result)) {
                $users[] = $row;
            }
            $this->close($link);
            return $users;
        } else {
            $this->close($link);
            die("Error al mostrar los usuarios");
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


    public function userExist($username, $password)
    {
        $link = $this->open();
        $sql = "SELECT * FROM usuario WHERE username = ? AND password = SHA1(?)";

        $stmt = $link->prepare($sql);
        $stmt->bind_param("ss", $username, $password);
        $stmt->execute();

        $result = $stmt->get_result();

        $this->close($link);

        if ($result->num_rows > 0) {
            $user = $result->fetch_assoc();
            return $user;
        } else {
            return false;
        }
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

    // OPERATIONS FOR PARTICPANTS TABLE
    public function createParticipant($username, $password, $tipo_usuario, $nombre, $foto = null, $numeroControl, $carrera)
    {
        $idUsuario = $this->createUser($username, $password, $tipo_usuario);

        if ($idUsuario) {
            $link = $this->open();

            $sql = "";

            if ($foto) {
                $sql = "INSERT INTO Participante (Usuario_idUsuario, nombre, foto, numero_control, carrera) 
                    VALUES (?, ?, ?, ?, ?)";
                $stmt = $link->prepare($sql);
                $stmt->bind_param("issss", $idUsuario, $nombre, $foto, $numeroControl, $carrera);
            } else {
                $sql = "INSERT INTO Participante (Usuario_idUsuario, nombre, numero_control, carrera) 
                    VALUES (?, ?, ?, ?)";
                $stmt = $link->prepare($sql);
                $stmt->bind_param("isss", $idUsuario, $nombre, $numeroControl, $carrera);
            }


            if ($stmt->execute()) {
                $stmt->close();
                $this->close($link);
                return true; // Éxito al dar de alta el participante
            } else {
                $stmt->close();
                $this->close($link);
                die("Error al dar de alta el participante");
            }
        }
    }

    public function showParticipants($nombre = null)
    {
        $link = $this->open();

        $result = null;

        if ($nombre) {
            $sql = "SELECT * FROM Participante as p WHERE p.nombre LIKE ?";
            $stmt = $link->prepare($sql);
            $nombre = "%$nombre%";
            $stmt->bind_param("s", $nombre);
            $stmt->execute();
            $result = $stmt->get_result();
        } else {
            $sql = "SELECT * FROM Participante";
            $result = mysqli_query($link, $sql);
        }

        if ($result) {
            $participants = [];
            while ($row = mysqli_fetch_assoc($result)) {
                 // Convertir la imagen BLOB a Base64
                $imagenBlob = $row['foto'];
                $imagenBase64 = base64_encode($imagenBlob);
                $row['foto'] = $imagenBase64;
                $participants[] = $row;
            }
            return $participants;
        } else {
            $this->close($link);
            die("Error al mostrar los participantes");
        }
        
    }


    public function updateParticipant($id, $nombre, $foto, $numeroControl, $carrera)
    {
        $link = $this->open();

        $sql = "UPDATE Participante SET nombre = ?, foto = ?, numero_control = ?, carrera = ? WHERE idParticipante = ?";
        $stmt = $link->prepare($sql);
        $stmt->bind_param("ssssi", $nombre, $foto, $numeroControl, $carrera, $id);

        // Envía los datos de la foto como un blob
        $fotoStream = fopen("php://memory", "r+");
        fwrite($fotoStream, $foto);
        rewind($fotoStream);
        $stmt->send_long_data(1, stream_get_contents($fotoStream));
        fclose($fotoStream);

        if ($stmt->execute()) {
            $stmt->close();
            $this->close($link);
            return true; // Éxito al actualizar el participante
        } else {
            $stmt->close();
            $this->close($link);
            die("Error al actualizar el participante");
        }
    }

    public function deleteParticipant($id)
    {
        $link = $this->open();

        $sql = "DELETE FROM Participante WHERE idParticipante = ?";
        $stmt = $link->prepare($sql);
        $stmt->bind_param("i", $id);

        if ($stmt->execute()) {
            $stmt->close();
            $this->close($link);
            return true; // Éxito al eliminar el participante
        } else {
            $stmt->close();
            $this->close($link);
            die("Error al eliminar el participante");
        }
    }



    // OPERATION FOR DIRECTORS TABLE
    // Return true or false according with the result
    public function createDirector($username, $password, $tipo_usuario, $grado, $firma, $nombre)
    {
        $idUsuario = $this->createUser($username, $password, $tipo_usuario);

        if ($idUsuario) {
            $link = $this->open();
            $sql = "INSERT INTO Director (grado, firma, nombre, Usuario_idUsuario) VALUES (?, ?, ?, ?)";

            $stmt = $link->prepare($sql);
            $stmt->bind_param("sssi", $grado, $firma, $nombre, $idUsuario);

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
    public function showDirectors($nombre = null)
    {
        $link = $this->open();

        $sql = null;

        $result = null;
    
        if ($nombre) {
            $sql = "SELECT * FROM Director as d WHERE d.nombre LIKE ?";
            $stmt = $link->prepare($sql);
            $nombre = "%$nombre%"; 
            $stmt->bind_param("s", $nombre);
            $stmt->execute();
            $result = $stmt->get_result();
        } else {
            $sql = "SELECT * FROM Participante";
            $result = mysqli_query($link, $sql);
        }

        if ($result) {
            $directors = [];
            while ($row = mysqli_fetch_assoc($result)) {
                $directors[] = $row;
            }
            $this->close($link);
            return $directors;
        } else {
            $this->close($link);
            die("Error al mostrar los directores");
        }
    }

    public function getActiveDirector()
    {
        $link = $this->open();

        $sql = "SELECT * FROM director WHERE isActivo = 1";

        $result = mysqli_query($link, $sql);

        if ($result && mysqli_num_rows($result) > 0) {
            $row = mysqli_fetch_assoc($result);
        
            // Convertir la imagen BLOB a Base64
            $imagenBlob = $row['firma'];
            $imagenBase64 = base64_encode($imagenBlob);
            $row['firma'] = $imagenBase64;

            $this->close($link);
        
            return $row;
        } else {
            $this->close($link);
            return null;
        }
    }


    public function getDirectorById($directorId)
    {
        $link = $this->open();

        $sql = "SELECT * FROM Director WHERE idDirector = ?";
        $stmt = $link->prepare($sql);
        $stmt->bind_param("i", $directorId);
        $stmt->execute();

        $result = $stmt->get_result();

        if ($result) {
            $director = mysqli_fetch_assoc($result);

            $stmt->close();
            $this->close($link);
            return $director;
        } else die ("Error. No se encontro director activo o no se ejecuto el query");
        
    }

    // SOLO EDITA EL DIRECTOR NO EL USUARIO RELACIONADO
    public function updateDirector($directorId, $grado, $firma, $nombre)
    {
        $link = $this->open();

        $sql = "UPDATE Director SET grado = ?, firma = ?, nombre = ? WHERE idDirector = ?";
        $stmt = $link->prepare($sql);
        $stmt->bind_param("sssi", $grado, $firma, $nombre, $directorId);

        // Envía los datos de la firma como un blob
        $firmaStream = fopen("php://memory", "r+");
        fwrite($firmaStream, $firma);
        rewind($firmaStream);
        $stmt->send_long_data(1, stream_get_contents($firmaStream));
        fclose($firmaStream);

        $result = $stmt->execute();

        $stmt->close();
        $this->close($link);

        return $result;
    }

    // Falta eliminar el usuario desdpues de eliminar el directo
    public function deleteDirector($directorId)
    {
        $link = $this->open();

        // Primero eliminamos el usuario asociado al director

        $sql = "DELETE FROM Director WHERE idDirector = ?";
        $stmt = $link->prepare($sql);
        $stmt->bind_param("i", $directorId);
        $result = $stmt->execute();

        $stmt->close();
        $this->close($link);

        return $result;
    }

    // METODOS DE OPERACIONES PARA LA TABLA DE EVENTOS
    public function showEvents()
    {
        $link = $this->open();

        $sql = "SELECT * FROM Evento";

        $result = mysqli_query($link, $sql);

        if ($result) {
            $events = array();
            while ($row = mysqli_fetch_assoc($result)) {
                $events[] = $row;
            }
            return $events;
        } else {
            $this->close($link);
            die("Error al mostrar los eventos");
        }
    }

    public function createEvent($director_id, $usuario_creador_id, $nombre, $fecha, $hora, $tipo_formato)
    {
        $link = $this->open();

        $sql = "INSERT INTO Evento (Director_idDirector, Usuario_idCreador, nombre, fecha, hora, tipo_formato)
                VALUES (?, ?, ?, ?, ?, ?)";


        $stmt = $link->prepare($sql);
        $stmt->bind_param("iissss", $director_id, $usuario_creador_id, $nombre, $fecha, $hora, $tipo_formato);

        $result = $stmt->execute();

        if ($result) {
            $stmt->close();
            $this->close($link);
            return true;
        } else {
            $stmt->close();
            $this->close($link);
            return false;
        }
    }

    public function updateEvent($event_id, $director_id, $usuario_creador_id, $nombre, $fecha, $hora, $tipo_formato)
    {
        $link = $this->open();
    
        $sql = "UPDATE Evento
                SET Director_idDirector = ?, Usuario_idCreador = ?, nombre = ?, fecha = ?, hora = ?, tipo_formato = ?
                WHERE idEvento = ?";
    
        $stmt = $link->prepare($sql);
        $stmt->bind_param("iissssi", $director_id, $usuario_creador_id, $nombre, $fecha, $hora, $tipo_formato, $event_id);
    
        $result = $stmt->execute();
    
        if ($result) {
            $stmt->close();
            $this->close($link);
            return true;
        } else {
            $stmt->close();
            $this->close($link);
            return false;
        }
    }
    
    public function deleteEvent($event_id)
    {
        $link = $this->open();

        // Eliminamos los registros de la tabla Item_Evento asociados con el evento
        $sqlDeleteItems = "DELETE FROM Item_Evento WHERE Evento_idEvento = ?";
        $stmtDeleteItems = $link->prepare($sqlDeleteItems);
        $stmtDeleteItems->bind_param("i", $event_id);
        $resultDeleteItems = $stmtDeleteItems->execute();

        if (!$resultDeleteItems) {
            $stmtDeleteItems->close();
            $this->close($link);
            die("Error al eliminar los registros de Item_Evento asociados al evento");
        }

        // Eliminamos el evento
        $sqlDeleteEvent = "DELETE FROM Evento WHERE idEvento = ?";
        $stmtDeleteEvent = $link->prepare($sqlDeleteEvent);
        $stmtDeleteEvent->bind_param("i", $event_id);
        $resultDeleteEvent = $stmtDeleteEvent->execute();

        if ($resultDeleteEvent) {
            $stmtDeleteItems->close();
            $stmtDeleteEvent->close();
            $this->close($link);
            return true;
        } else {
            $stmtDeleteItems->close();
            $stmtDeleteEvent->close();
            $this->close($link);
            return false;
        }
    }


    // METODOS PARA LAS OPRACIONES DE LA TABLA DE ITEM EVENTOS
    public function createItemEvento($participante_id, $evento_id)
    {
        $link = $this->open();

        $sql = "INSERT INTO Item_Evento (Participante_idParticipante, Evento_idEvento)
                VALUES (?, ?)";

        $stmt = $link->prepare($sql);
        $stmt->bind_param("ii", $participante_id, $evento_id);

        $result = $stmt->execute();

        if ($result) {
            $stmt->close();
            $this->close($link);
            return true;
        } else {
            $stmt->close();
            $this->close($link);
            return false;
        }
    }


    public function showParticipantsEvento($evento_id)
    {
        $link = $this->open();

        $sql = "SELECT p.idParticipante, p.nombre, e.nombre 
                FROM (Participante AS p INNER JOIN Item_Evento AS ie ON p.idParticipante = ie.Participante_idParticipante) 
                INNER JOIN Evento AS e ON ie.Evento_idEvento = e.idEvento WHERE e.idEvento = 4";

        $stmt = $link->prepare($sql);
        $stmt->bind_param("i", $evento_id);

        $stmt->execute();
        $result = $stmt->get_result();

        $items_evento = array();

        while ($row = $result->fetch_assoc()) {
            $items_evento[] = $row;
        }

        $stmt->close();
        $this->close($link);

        return $items_evento;
    }

    public function updateItemEvento($item_evento_id, $participante_id, $evento_id)
    {
        $link = $this->open();

        $sql = "UPDATE Item_Evento
                SET Participante_idParticipante = ?, Evento_idEvento = ?
                WHERE idItem_Evento = ?";

        $stmt = $link->prepare($sql);
        $stmt->bind_param("iii", $participante_id, $evento_id, $item_evento_id);

        $result = $stmt->execute();

        if ($result) {
            $stmt->close();
            $this->close($link);
            return true;
        } else {
            $stmt->close();
            $this->close($link);
            die("Error al actualizar el registro en Item_Evento");
        }
    }

    public function deleteItemEvento($item_evento_id)
    {
        $link = $this->open();

        $sql = "DELETE FROM Item_Evento WHERE idItem_Evento = ?";

        $stmt = $link->prepare($sql);
        $stmt->bind_param("i", $item_evento_id);

        $result = $stmt->execute();

        if ($result) {
            $stmt->close();
            $this->close($link);
            return true;
        } else {
            $stmt->close();
            $this->close($link);
            die("Error al eliminar el registro de Item_Evento");
        }
    }


}


?>