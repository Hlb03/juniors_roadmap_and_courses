# Веб-застосунок для новачків

>В даном застосунку, користувачі зможуть переглядати roadmap'и для всіх напрямів, а також 
отримувати список актуальних курсів з Udemy по конкретній темі.




*Даний репозиторій створений для back-end частини.*


## Docker instructions
    docker-compose up 
Запускаються всі образи з файлу *docker-compose*

    docker ps -a
Переглянути список всіх існуючих контейнерів (в тому числі з тих, що не працюють)

    docker start/stop *container_name
Зупинити або запустити (після зупинки) контейнер

    docker rm *container_name
Видалити контейнер за назвою

    docker rmi *image_name
Видалити образ за його назвою