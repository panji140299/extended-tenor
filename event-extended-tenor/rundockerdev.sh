mvn clean package -DskipTests && docker build . -t solace-notification-debezium -f Dockerfile.dev && docker run solace-notification-debezium