# WebService Prueba 2 - API REST de Discografía

API REST desarrollada con Spring Boot y MongoDB Atlas para gestionar artistas y discos.

## 🚀 Tecnologías

- **Java 21**
- **Spring Boot 3.4.0**
- **MongoDB Atlas**
- **Gradle 8.11**
- **Docker**

## 📦 Endpoints

### Artistas

- `POST /api/artista` - Crear artista
- `GET /api/artistas` - Listar todos los artistas
- `GET /api/artista/{id}` - Obtener artista por ID
- `PUT /api/artista/{id}` - Actualizar artista
- `DELETE /api/artista/{id}` - Eliminar artista

### Discos

- `POST /api/disco` - Crear disco
- `GET /api/discos` - Listar todos los discos
- `GET /api/disco/{id}` - Obtener disco por ID
- `GET /api/artista/{id}/discos` - Obtener discos de un artista
- `DELETE /api/disco/{id}` - Eliminar disco

## 🔧 Configuración Local

1. Clonar el repositorio:
```bash
git clone https://github.com/Coshinote/webserviceprueba2.git
cd webserviceprueba2
```

2. Ejecutar la aplicación:
```bash
./gradlew bootRun
```

3. La API estará disponible en: `http://localhost:8081`

## 🐳 Docker

### Construir la imagen:
```bash
docker build -t discografia-api .
```

### Ejecutar el contenedor:
```bash
docker run -p 8081:8081 discografia-api
```

## 🌐 Despliegue en Render

1. Conectar repositorio de GitHub
2. Seleccionar **Docker** como runtime
3. Configurar variables de entorno:
   - `SPRING_DATA_MONGODB_URI`
   - `SPRING_DATA_MONGODB_DATABASE`
   - `SERVER_PORT=8081`

## 📝 Ejemplo de uso

### Crear un artista:
```bash
curl -X POST http://localhost:8081/api/artista \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Queen",
    "nacionalidad": "Reino Unido",
    "anioDebut": 1970
  }'
```

### Crear un disco:
```bash
curl -X POST http://localhost:8081/api/disco \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "A Night at the Opera",
    "anioLanzamiento": 1975,
    "genero": "Rock",
    "idArtista": "ID_DEL_ARTISTA"
  }'
```

## 👨‍💻 Autor

Desarrollado por Coshinote
