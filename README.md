[Agenda-SENA.postman_collection.json](https://github.com/user-attachments/files/29273692/Agenda-SENA.postman_collection.json)
{
  "info": {
    "_postman_id": "4330850b-9db6-432a-ad10-8c0d91b67a4e",
    "name": "Agenda-SENA",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
    "_exporter_id": "56098083",
    "_collection_link": "https://go.postman.co/collection/56098083-4330850b-9db6-432a-ad10-8c0d91b67a4e?source=collection_link"
  },
  "item": [
    {
      "name": "PostAmbientes",
      "request": {
        "method": "POST",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\r\n  \"nombre\": \"Laboratorio de Software 4\",\r\n  \"capacidad\": 30,\r\n  \"tipo\": \"LABORATORIO\",\r\n  \"activo\": true\r\n}",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "http://localhost:8080/reservas",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "reservas"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Crear un Ambiente",
      "request": {
        "method": "POST",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\r\n  \"nombreInstructor\": \"Ana Gómez\",\r\n  \"fechaInicio\": \"2026-06-25T14:00:00\",\r\n  \"fechaFin\": \"2026-06-25T18:00:00\",\r\n  \"numeroAprendices\": 28,\r\n  \"estado\": \"ACTIVA\",\r\n  \"ambiente\": {\r\n    \"id\": 6\r\n  }\r\n}",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "http://localhost:8080/api/reservas",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "reservas"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Obtener todos los Ambientes",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/ambientes",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "ambientes"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Obtener un Ambiente específico",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/ambientes/1",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "ambientes",
            "1"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Actualizar un Ambiente",
      "request": {
        "method": "PUT",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\r\n  \"nombreInstructor\": \"Ana Gómez\",\r\n  \"fechaInicio\": \"2026-06-25T14:00:00\",\r\n  \"fechaFin\": \"2026-06-25T18:00:00\",\r\n  \"numeroAprendices\": 30,\r\n  \"estado\": \"CANCELADA\",\r\n  \"ambiente\": {\r\n    \"id\": 1\r\n  }\r\n}",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "http://localhost:8080/api/reservas/1",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "reservas",
            "1"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Eliminar un Ambiente",
      "request": {
        "method": "DELETE",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/reservas/1",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "reservas",
            "1"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Crear una Reserva",
      "request": {
        "method": "POST",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\r\n  \"nombreInstructor\": \"Ana Gómez\",\r\n  \"fechaInicio\": \"2026-06-25T14:00:00\",\r\n  \"fechaFin\": \"2026-06-25T18:00:00\",\r\n  \"numeroAprendices\": 28,\r\n  \"estado\": \"ACTIVA\",\r\n  \"ambiente\": {\r\n    \"id\": 1 \r\n  }\r\n}",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "http://localhost:8080/api/reservas",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "reservas"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Obtener todas las Reservas",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/reservas",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "reservas"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Obtener una Reserva específica",
      "request": {
        "method": "GET",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/reservas/6",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "reservas",
            "6"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Actualizar una Reserva",
      "request": {
        "method": "PUT",
        "header": [],
        "body": {
          "mode": "raw",
          "raw": "{\r\n  \"nombreInstructor\": \"Ana Gómez\",\r\n  \"fechaInicio\": \"2026-06-25T14:00:00\",\r\n  \"fechaFin\": \"2026-06-25T18:00:00\",\r\n  \"numeroAprendices\": 30,\r\n  \"estado\": \"CANCELADA\",\r\n  \"ambiente\": {\r\n    \"id\": 1\r\n  }\r\n}",
          "options": {
            "raw": {
              "language": "json"
            }
          }
        },
        "url": {
          "raw": "http://localhost:8080/api/reservas/2",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "reservas",
            "2"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Eliminar una Reserva",
      "request": {
        "method": "DELETE",
        "header": [],
        "url": {
          "raw": "http://localhost:8080/api/reservas/2",
          "protocol": "http",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "api",
            "reservas",
            "2"
          ]
        }
      },
      "response": []
    }
  ]
}
