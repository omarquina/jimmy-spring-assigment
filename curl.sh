curl -X POST 'http://localhost:8081/api/cars' \
    --header 'Content-Type: application/json'  \
    -d '{"make":"Toyota", "model":"Corolla"}' -i

curl -X POST 'http://localhost:8081/api/cars' \
    --header 'Content-Type: application/json'  \
    -d '{"make":"Tesla", "model":"M3"}' -i

curl -X POST 'http://localhost:8081/api/reservations' \
    --header 'Content-Type: application/json'  \
    -d '{"startDate":"05-31-2024 05:00", "duration": 1}' -i

curl -X POST 'http://localhost:8081/api/reservations' \
    --header 'Content-Type: application/json'  \
    -d '{"startDate":"05-31-2024 05:00", "duration": 1}' -i

curl -X POST 'http://localhost:8081/api/reservations' \
    --header 'Content-Type: application/json'  \
    -d '{"startDate":"05-30-2024 13:16", "duration": 1}' -i

curl -X GET 'http://localhost:8081/api/reservations' \
        --header 'Content-Type: application/json'  -i

curl -X PATCH 'http://localhost:8081/api/cars/C3107' \
    --header 'Content-Type: application/json'  \
    -d '{"make":"TESLA", "model":"RRRRR"}' -i

curl -X PATCH 'http://localhost:8081/api/cars/C3107' \
        --header 'Content-Type: application/json'  \
        -d '{"model":"M40"}' -i

curl -X DELETE 'http://localhost:8081/api/cars/C3107' \
        --header 'Content-Type: application/json' -i


